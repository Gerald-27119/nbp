package pl.adamlangmesser.nbpapi.adapters.persistence;

import java.time.LocalDate;

record ComputerSearchCriteria(//TODO:add valdiation
                              String nameFragment,
                              LocalDate dateFrom,
                              LocalDate dateTo,
                              String sortBy,       // "name" albo "bookingDate"
                              String sortDirection, // "asc" albo "desc"
                              Integer page,
                              Integer pageSize
) {
}
//public enum ComputerSortField {
//    NAME,
//    BOOKING_DATE
//}
//
//public enum SortDirection {
//    ASC,
//    DESC
//}TODO

//TODO:nazwa na query?