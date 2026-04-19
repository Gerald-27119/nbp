package pl.adamlangmesser.nbpapi.adapters.out.xml.contract;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;

public record Product(
        @JacksonXmlProperty(localName = "nazwa")
        String name,

        @JacksonXmlProperty(localName = "data_ksiegowania")
        String bookingDate,

        @JacksonXmlProperty(localName = "koszt_USD")
        BigDecimal priceUSD,

        @JacksonXmlProperty(localName = "koszt_PLN")
        BigDecimal pricePLN
) {
}