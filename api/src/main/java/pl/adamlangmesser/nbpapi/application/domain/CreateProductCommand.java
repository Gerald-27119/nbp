package pl.adamlangmesser.nbpapi.application.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateProductCommand(String name, LocalDate bookingDate, BigDecimal priceUSD) {
}
