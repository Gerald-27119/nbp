package pl.adamlangmesser.nbpapi.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import pl.adamlangmesser.nbpapi.adapters.out.exchange.rate.ExchangeRateProviderAdapter;
import pl.adamlangmesser.nbpapi.adapters.out.persistence.ProductEntityRepositoryAdapter;
import pl.adamlangmesser.nbpapi.adapters.out.xml.XMLWriterAdapter;
import pl.adamlangmesser.nbpapi.application.currency.conversion.CurrencyConverter;
import pl.adamlangmesser.nbpapi.domain.model.Product;
import pl.adamlangmesser.nbpapi.domain.model.NewProductData;

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

    public void addAll(List<NewProductData> newProductData) {
        List<Product> products = newProductData.stream().map(draft -> {
            BigDecimal usdToPlnRate = exchangeRateProviderAdapter.getUsdToPlnRate(draft.bookingDate());
            BigDecimal pricePLN = currencyConverter.convertUsdToPln(usdToPlnRate, draft.priceUSD());
            Product product = new Product(draft.name(), draft.bookingDate(), draft.priceUSD(), pricePLN);
            try {
                xmlWriterAdapter.writeProduct(product);
            } catch (IOException e) {
                throw new RuntimeException(e);//TODO:enhance
            }
            return product;
        }).toList();

        productEntityRepositoryAdapter.saveAll(products);
    }


}
