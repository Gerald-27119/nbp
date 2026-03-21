package pl.adamlangmesser.nbpapi.boundries.nbp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class ExchangeRateProviderAdapter {

    private final NBPClient nbpClient;

    public BigDecimal getUsdToPlnRate(LocalDate localDate){
        return nbpClient.getRateUSDtoPLN(localDate);
    }
}
