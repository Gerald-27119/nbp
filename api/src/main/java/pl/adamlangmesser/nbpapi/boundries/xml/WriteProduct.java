package pl.adamlangmesser.nbpapi.boundries.xml;

import java.math.BigDecimal;

record WriteProduct(String name, String bookingDate, BigDecimal priceUSD, BigDecimal pricePLN) {
}
