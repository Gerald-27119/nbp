package pl.adamlangmesser.nbpapi.adapters.xml;

import java.math.BigDecimal;

record WriteProduct(String name, String bookingDate, BigDecimal priceUSD, BigDecimal pricePLN) {
}
