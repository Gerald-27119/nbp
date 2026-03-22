package pl.adamlangmesser.nbpapi.adapters.out.exchange.rate;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.application.ports.out.ExchangeRateProviderPort;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@AllArgsConstructor
@CacheConfig(cacheNames = "usdToPlnRates")
public class ExchangeRateProviderAdapter implements ExchangeRateProviderPort {

    private final NbpExchangeRateClient nbpClient;

    @Override
    @Cacheable(key = "#date", condition = "#date != null")
    public BigDecimal getUsdToPlnRate(LocalDate date) {
        return date == null
                ? nbpClient.getCurrentUsdToPlnRate()
                : nbpClient.getUsdToPlnRate(date);
    }
}