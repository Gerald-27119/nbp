package pl.adamlangmesser.nbpapi.adapters.xml;

import java.math.BigDecimal;

public record WriteProductDto(String name, String bookingDate, BigDecimal priceUSD, BigDecimal pricePLN) {
}
