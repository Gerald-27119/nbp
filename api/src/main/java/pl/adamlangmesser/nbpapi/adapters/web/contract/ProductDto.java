package pl.adamlangmesser.nbpapi.adapters.web.contract;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductDto(String name, LocalDate bookingDate, BigDecimal priceUSD, BigDecimal pricePLN) {
}
