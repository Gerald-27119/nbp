package pl.adamlangmesser.nbpapi.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.application.domain.model.Product;
import pl.adamlangmesser.nbpapi.application.domain.model.ProductsPage;
import pl.adamlangmesser.nbpapi.application.domain.CreateProductCommand;
import pl.adamlangmesser.nbpapi.boundries.db.ComputerSearchCriteria;
import pl.adamlangmesser.nbpapi.boundries.db.ProductEntityRepositoryAdapter;
import pl.adamlangmesser.nbpapi.boundries.nbp.ExchangeRateProviderAdapter;
import pl.adamlangmesser.nbpapi.boundries.xml.XMLWriterAdapter;
import pl.adamlangmesser.nbpapi.boundries.xml.dtos.WriteProductDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Component
@AllArgsConstructor
class ProductService {

    private final ExchangeRateProviderAdapter exchangeRateProviderAdapter;
    private final CurrencyConverter currencyConverter;
    private final ProductEntityRepositoryAdapter productEntityRepositoryAdapter;
    private final XMLWriterAdapter xmlWriterAdapter;

    public void addAll(List<CreateProductCommand> commands) {
        List<Product> products = commands.stream().map(command -> {
            BigDecimal usdToPlnRate = exchangeRateProviderAdapter.getUsdToPlnRate(command.bookingDate());
            BigDecimal pricePLN = currencyConverter.convertUsdToPln(usdToPlnRate, command.priceUSD());
            try {
                xmlWriterAdapter.writeProduct(new WriteProductDto(command.name(), command.bookingDate().toString(), command.priceUSD(), pricePLN));
            } catch (IOException e) {
                throw new RuntimeException(e);//TODO:enhance
            }
            return new Product(command.name(), command.bookingDate(), command.priceUSD(), pricePLN);
        }).toList();

        productEntityRepositoryAdapter.saveAll(products);
    }

    public ProductsPage query(ComputerSearchCriteria criteria) {
        return productEntityRepositoryAdapter.query(criteria);
    }

}
