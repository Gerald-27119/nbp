package pl.adamlangmesser.nbpapi.application.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.application.domain.model.ProductsPage;
import pl.adamlangmesser.nbpapi.boundries.db.ProductEntityRepositoryAdapter;
import pl.adamlangmesser.nbpapi.boundries.nbp.RateClient;
import pl.adamlangmesser.nbpapi.boundries.db.ComputerSearchCriteria;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class Service {

    private final RateClient rateClient;
    private final ProductEntityRepositoryAdapter productEntityRepositoryAdapter;

//    @Override
//    public BigDecimal convertFromUSDtoPLN(BigDecimal amount) {
//        System.out.println("java.home = " + System.getProperty("java.home"));
//        System.out.println("trustStore = " + System.getProperty("javax.net.ssl.trustStore"));
//        System.out.println("trustStorePassword set = " + (System.getProperty("javax.net.ssl.trustStorePassword") != null));
//        BigDecimal rate = rateClient.getRateUSDtoPLN();
//        BigDecimal amountInPLN = amount.multiply(rate);
//        return amountInPLN;
//    }



}
