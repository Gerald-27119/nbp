package pl.adamlangmesser.nbpapi.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import pl.adamlangmesser.nbpapi.adapters.out.persistence.ProductEntityRepositoryAdapter;
import pl.adamlangmesser.nbpapi.adapters.out.xml.XMLWriterAdapter;
import pl.adamlangmesser.nbpapi.application.currency.conversion.CurrencyConverter;
import pl.adamlangmesser.nbpapi.application.ports.in.command.AddProductsUseCase;
import pl.adamlangmesser.nbpapi.application.ports.out.ExchangeRateProviderPort;
import pl.adamlangmesser.nbpapi.application.ports.out.ProductPersistencePort;
import pl.adamlangmesser.nbpapi.application.ports.out.ProductXmlWriterPort;
import pl.adamlangmesser.nbpapi.domain.model.Product;
import pl.adamlangmesser.nbpapi.domain.model.NewProductData;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductCommandService implements AddProductsUseCase {

    private final CurrencyConverter currencyConverter;
    private final ExchangeRateProviderPort exchangeRateProvider;
    private final ProductPersistencePort productRepository;
    private final ProductXmlWriterPort xmlWriter;

    @Override
    public void addAll(List<NewProductData> newProductData) {
        List<Product> products = newProductData.stream().map(draft -> {
            BigDecimal usdToPlnRate = exchangeRateProvider.getUsdToPlnRate(draft.bookingDate());
            BigDecimal pricePLN = currencyConverter.convertUsdToPln(usdToPlnRate, draft.priceUSD());
            Product product = new Product(draft.name(), draft.bookingDate(), draft.priceUSD(), pricePLN);
            try {
                xmlWriter.writeProduct(product);
            } catch (IOException e) {
                throw new RuntimeException(e);//TODO:enhance
            }
            return product;
        }).toList();

        productRepository.saveAll(products);
    }


}
