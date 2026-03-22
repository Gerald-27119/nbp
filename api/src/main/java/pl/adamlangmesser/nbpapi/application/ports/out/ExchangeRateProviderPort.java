package pl.adamlangmesser.nbpapi.application.ports.out;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeRateProviderPort {
    BigDecimal getUsdToPlnRate(LocalDate localDate);
}
