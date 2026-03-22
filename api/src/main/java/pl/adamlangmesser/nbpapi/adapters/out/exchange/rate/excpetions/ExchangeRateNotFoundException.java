package pl.adamlangmesser.nbpapi.adapters.out.exchange.rate.excpetions;

import java.time.LocalDate;

public class ExchangeRateNotFoundException extends RuntimeException {

    public ExchangeRateNotFoundException(String baseCurrency,
                                         String quoteCurrency,
                                         LocalDate startDate,
                                         LocalDate endDate) {
        super("Exchange rate %s/%s was not found in the NBP API for the date range from %s to %s."
                .formatted(baseCurrency, quoteCurrency, startDate, endDate));
    }
}