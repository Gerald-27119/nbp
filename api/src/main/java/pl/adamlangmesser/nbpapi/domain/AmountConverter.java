package pl.adamlangmesser.nbpapi.domain;

import java.math.BigDecimal;

public interface AmountConverter {
    BigDecimal convertFromUSDtoPLN(BigDecimal amount);
}
