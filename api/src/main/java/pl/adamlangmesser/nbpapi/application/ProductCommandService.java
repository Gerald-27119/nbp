package pl.adamlangmesser.nbpapi.application;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import pl.adamlangmesser.nbpapi.application.currency.conversion.CurrencyConverter;
import pl.adamlangmesser.nbpapi.application.ports.in.command.AddProductsUseCase;
import pl.adamlangmesser.nbpapi.application.ports.out.ExchangeRateProviderPort;
import pl.adamlangmesser.nbpapi.application.ports.out.ProductPersistencePort;
import pl.adamlangmesser.nbpapi.application.ports.out.ProductXmlWriterPort;
import pl.adamlangmesser.nbpapi.domain.model.NewProductData;
import pl.adamlangmesser.nbpapi.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Component
@AllArgsConstructor
@Validated
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

            Product product = new Product(
                    draft.name(),
                    draft.bookingDate(),
                    draft.priceUSD(),
                    pricePLN
            );

            xmlWriter.writeProduct(product);
            return product;
        }).toList();

        productRepository.saveAll(products);
    }
}
