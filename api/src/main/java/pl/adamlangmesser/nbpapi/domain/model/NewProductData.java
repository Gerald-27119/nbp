package pl.adamlangmesser.nbpapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NewProductData(String name, LocalDate bookingDate, BigDecimal priceUSD) {
}
