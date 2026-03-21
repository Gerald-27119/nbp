package pl.adamlangmesser.nbpapi.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CurrencyConverter {

    public BigDecimal convertUsdToPln(BigDecimal rate, BigDecimal amount){
        return rate.multiply(amount);
    }
}
