package pl.adamlangmesser.nbpapi.adapters.out.exchange.rate.contract;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NbpRateDto(
        String no,
        LocalDate effectiveDate,
        BigDecimal mid
) {
}