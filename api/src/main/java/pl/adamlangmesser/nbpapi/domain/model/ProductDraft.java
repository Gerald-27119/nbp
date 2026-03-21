package pl.adamlangmesser.nbpapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductDraft(String name, LocalDate bookingDate, BigDecimal priceUSD) {
}
