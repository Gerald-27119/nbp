package pl.adamlangmesser.nbpapi.boundries.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface RateClient {
    BigDecimal getRateUSDtoPLN( LocalDate localDate);

    default BigDecimal getRateUSDtoPLN() {
        return getRateUSDtoPLN( null);
    }
}
