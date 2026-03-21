package pl.adamlangmesser.nbpapi.application;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class CurrencyConverter {

    public BigDecimal convertUsdToPln(BigDecimal rate, BigDecimal amount){
        return rate.multiply(amount);
    }
}
