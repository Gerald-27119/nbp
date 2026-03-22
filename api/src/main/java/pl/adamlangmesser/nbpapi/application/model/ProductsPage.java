package pl.adamlangmesser.nbpapi.application.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import pl.adamlangmesser.nbpapi.domain.model.Product;

import java.util.List;

@Builder
public record ProductsPage(

        @NotNull(message = "Products list must not be null")
        List<@NotNull(message = "Product element must not be null") @Valid Product> products,

        @NotNull(message = "Total elements must not be null")
        @Min(value = 0, message = "Total elements must be greater than or equal to 0")
        Long totalElements,

        @NotNull(message = "hasNext must not be null")
        Boolean hasNext,

        @NotNull(message = "hasPrevious must not be null")
        Boolean hasPrevious,

        @NotNull(message = "Page number must not be null")
        @Min(value = 0, message = "Page number must be greater than or equal to 0")
        Integer number

) {
}