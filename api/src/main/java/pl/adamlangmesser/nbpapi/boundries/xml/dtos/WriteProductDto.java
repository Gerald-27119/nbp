package pl.adamlangmesser.nbpapi.boundries.xml.dtos;

import java.math.BigDecimal;

public record WriteProductDto(String name, String bookingDate, BigDecimal priceUSD, BigDecimal pricePLN) {
}
