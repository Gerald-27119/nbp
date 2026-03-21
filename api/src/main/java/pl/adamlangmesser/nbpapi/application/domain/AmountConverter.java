package pl.adamlangmesser.nbpapi.application.domain;

import java.math.BigDecimal;

public interface AmountConverter {
    BigDecimal convertFromUSDtoPLN(BigDecimal amount);
}
