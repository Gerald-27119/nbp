package pl.adamlangmesser.nbpapi.boundries.web.requests;


import java.math.BigDecimal;
import java.time.LocalDate;

public record Request(String productName,
                      LocalDate exchangeDate,
                      BigDecimal price) {
}
