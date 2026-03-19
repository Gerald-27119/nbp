package pl.adamlangmesser.nbpapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    private String name;
    private LocalDate bookingDate;
    private BigDecimal priceUSD;
    private BigDecimal pricePLN;

    public Product(String name, LocalDate bookingDate, BigDecimal priceUSD) {
        this.name = name;
        this.bookingDate = bookingDate;
        this.priceUSD = priceUSD;
    }
}
