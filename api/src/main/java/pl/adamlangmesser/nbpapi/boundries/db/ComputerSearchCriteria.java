package pl.adamlangmesser.nbpapi.boundries.db;

import java.time.LocalDate;

public record ComputerSearchCriteria(//TODO:add valdiation
        String nameFragment,
        LocalDate dateFrom,
        LocalDate dateTo,
        String sortBy,       // "name" albo "bookingDate"
        String sortDirection // "asc" albo "desc"
) {}
//public enum ComputerSortField {
//    NAME,
//    BOOKING_DATE
//}
//
//public enum SortDirection {
//    ASC,
//    DESC
//}TODO