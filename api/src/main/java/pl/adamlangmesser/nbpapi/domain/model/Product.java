package pl.adamlangmesser.nbpapi.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record Product(

        @NotBlank(message = "Product name must not be blank")
        String name,

        @NotNull(message = "Booking date must not be null")
        @PastOrPresent(message = "Booking date cannot be in the future")
        LocalDate bookingDate,

        @NotNull(message = "Price in USD must not be null")
        @Positive(message = "Price in USD must be greater than 0")
        BigDecimal priceUSD,

        @NotNull(message = "Price in PLN must not be null")
        @Positive(message = "Price in PLN must be greater than 0")
        BigDecimal pricePLN

) {
}