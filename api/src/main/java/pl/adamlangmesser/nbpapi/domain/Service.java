package pl.adamlangmesser.nbpapi.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.boundries.db.ProductEntityAdapter;
import pl.adamlangmesser.nbpapi.boundries.nbp.RateClient;
import pl.adamlangmesser.nbpapi.domain.model.Product;
import pl.adamlangmesser.nbpapi.boundries.db.ComputerSearchCriteria;

import java.math.BigDecimal;
import java.util.List;

@Component
@AllArgsConstructor
public class Service implements AmountConverter{

    private final RateClient rateClient;
    private final ProductEntityAdapter productEntityAdapter;

    @Override
    public BigDecimal convertFromUSDtoPLN(BigDecimal amount) {
        System.out.println("java.home = " + System.getProperty("java.home"));
        System.out.println("trustStore = " + System.getProperty("javax.net.ssl.trustStore"));
        System.out.println("trustStorePassword set = " + (System.getProperty("javax.net.ssl.trustStorePassword") != null));
        BigDecimal rate = rateClient.getRateUSDtoPLN();
        BigDecimal amountInPLN = amount.multiply(rate);
        return amountInPLN;
    }

    public List<Product> query(ComputerSearchCriteria criteria ){
        return productEntityAdapter.query(criteria);
    }

}
