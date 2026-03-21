package pl.adamlangmesser.nbpapi.adapters.out.xml.contract;

import java.math.BigDecimal;

public record WriteProductDto(String name, String bookingDate, BigDecimal priceUSD, BigDecimal pricePLN) {
}
