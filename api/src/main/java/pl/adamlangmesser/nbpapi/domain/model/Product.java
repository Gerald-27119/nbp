package pl.adamlangmesser.nbpapi.domain.model;


import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record Product(String name,
         LocalDate bookingDate,
         BigDecimal priceUSD,
         BigDecimal pricePLN) {

}
