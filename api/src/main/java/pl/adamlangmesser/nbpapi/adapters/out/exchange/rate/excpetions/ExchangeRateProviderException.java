package pl.adamlangmesser.nbpapi.adapters.out.exchange.rate.excpetions;

public class ExchangeRateProviderException extends RuntimeException {

    public ExchangeRateProviderException(String message, Throwable cause) {
        super(message, cause);
    }
}