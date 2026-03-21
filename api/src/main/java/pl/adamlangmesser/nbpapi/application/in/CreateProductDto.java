package pl.adamlangmesser.nbpapi.application.in;


import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateProductDto(String name, LocalDate bookingDate, BigDecimal priceUSD) {
}
