package pl.adamlangmesser.nbpapi.domain.model;

import java.time.LocalDate;

public record ProductsQuery(String nameFragment,
                            LocalDate dateFrom,
                            LocalDate dateTo,
                            String sortBy,       // "name" albo "bookingDate"
                            String sortDirection, // "asc" albo "desc"
                            Integer page,
                            Integer pageSize) {
}
