package pl.adamlangmesser.nbpapi.application.in;

import java.time.LocalDate;

public record ComputerSearchQueryDto(String nameFragment,
                                     LocalDate dateFrom,
                                     LocalDate dateTo,
                                     String sortBy,       // "name" albo "bookingDate"
                                     String sortDirection, // "asc" albo "desc"
                                     Integer page,
                                     Integer pageSize) {
}
