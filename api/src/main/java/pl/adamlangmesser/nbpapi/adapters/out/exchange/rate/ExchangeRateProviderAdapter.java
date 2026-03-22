package pl.adamlangmesser.nbpapi.adapters.out.exchange.rate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.application.ports.out.ExchangeRateProviderPort;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class ExchangeRateProviderAdapter implements ExchangeRateProviderPort {

    private final NBPClient nbpClient;

//  TODO  dodac cachwoanie,!!
    @Override
    public BigDecimal getUsdToPlnRate(LocalDate localDate){
        return nbpClient.getRateUSDtoPLN(localDate);
    }
}
//Interfejsy dorob i zrob aby byl uzywane//