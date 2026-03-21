package pl.adamlangmesser.nbpapi.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import pl.adamlangmesser.nbpapi.adapters.exchange.rate.ExchangeRateProviderAdapter;
import pl.adamlangmesser.nbpapi.adapters.persistence.ProductEntityRepositoryAdapter;
import pl.adamlangmesser.nbpapi.adapters.xml.WriteProductDto;
import pl.adamlangmesser.nbpapi.adapters.xml.XMLWriterAdapter;
import pl.adamlangmesser.nbpapi.domain.CurrencyConverter;
import pl.adamlangmesser.nbpapi.domain.model.Product;
import pl.adamlangmesser.nbpapi.domain.model.ProductDraft;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductCommandService {

    private final ExchangeRateProviderAdapter exchangeRateProviderAdapter;
    private final CurrencyConverter currencyConverter;
    private final ProductEntityRepositoryAdapter productEntityRepositoryAdapter;
    private final XMLWriterAdapter xmlWriterAdapter;

    public void addAll(List<ProductDraft> productDrafts) {
        List<Product> products = productDrafts.stream().map(draft -> {
            BigDecimal usdToPlnRate = exchangeRateProviderAdapter.getUsdToPlnRate(draft.bookingDate());
            BigDecimal pricePLN = currencyConverter.convertUsdToPln(usdToPlnRate, draft.priceUSD());
            try {
                xmlWriterAdapter.writeProduct(new WriteProductDto(draft.name(), draft.bookingDate().toString(), draft.priceUSD(), pricePLN));
            } catch (IOException e) {
                throw new RuntimeException(e);//TODO:enhance
            }
            return new Product(draft.name(), draft.bookingDate(), draft.priceUSD(), pricePLN);
        }).toList();

        productEntityRepositoryAdapter.saveAll(products);
    }


}
