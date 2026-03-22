package pl.adamlangmesser.nbpapi.adapters.out.exchange.rate.excpetions;

import java.time.LocalDate;

public class ExchangeRateNotFoundException extends RuntimeException {

    public ExchangeRateNotFoundException(String baseCurrency,
                                         String quoteCurrency,
                                         LocalDate startDate,
                                         LocalDate endDate) {
        super("Nie znaleziono kursu %s/%s w API NBP dla zakresu od %s do %s."
                .formatted(baseCurrency, quoteCurrency, startDate, endDate));
    }
}