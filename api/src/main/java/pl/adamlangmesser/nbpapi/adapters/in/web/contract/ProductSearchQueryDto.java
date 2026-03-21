package pl.adamlangmesser.nbpapi.adapters.in.web.contract;

import java.time.LocalDate;

public record ProductSearchQueryDto(
        String nameFragment,
        LocalDate dateFrom,
        LocalDate dateTo,
        String sortBy,//TODO:enum
        String sortDirection,//TODO:enum
        Integer page,
        Integer pageSize
) {
}
