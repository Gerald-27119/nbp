package pl.adamlangmesser.nbpapi.application.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ProductsQuery(

        @Size(max = 255, message = "Name fragment must have at most 255 characters")
        String nameFragment,

        LocalDate dateFrom,

        LocalDate dateTo,

        @Pattern(
                regexp = "name|bookingDate",
                message = "sortBy must be either 'name' or 'bookingDate'"
        )
        String sortBy,

        @Pattern(
                regexp = "asc|desc",
                message = "sortDirection must be either 'asc' or 'desc'"
        )
        String sortDirection,

        @Min(value = 0, message = "Page must be greater than or equal to 0")
        Integer page,

        @Positive(message = "Page size must be greater than 0")
        Integer pageSize

) {

    @AssertTrue(message = "dateFrom must be before or equal to dateTo")
    public boolean isDateRangeValid() {
        return dateFrom == null || dateTo == null || !dateFrom.isAfter(dateTo);
    }
}