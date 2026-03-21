package pl.adamlangmesser.nbpapi.adapters.web.contract;

import java.time.LocalDate;

public record ComputerSearchQueryDto(
        String nameFragment,
                                     LocalDate dateFrom,
                                     LocalDate dateTo,
                                     String sortBy,//TODO:enum
                                     String sortDirection,//TODO:enum
                                     Integer page,
                                     Integer pageSize
) {
}
