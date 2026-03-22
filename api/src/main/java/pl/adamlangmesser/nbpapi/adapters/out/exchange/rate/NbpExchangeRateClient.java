package pl.adamlangmesser.nbpapi.adapters.out.exchange.rate;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import pl.adamlangmesser.nbpapi.adapters.out.exchange.rate.config.NbpApiProperties;
import pl.adamlangmesser.nbpapi.adapters.out.exchange.rate.contract.NbpRateDto;
import pl.adamlangmesser.nbpapi.adapters.out.exchange.rate.contract.NbpRateResponse;
import pl.adamlangmesser.nbpapi.adapters.out.exchange.rate.excpetions.ExchangeRateNotFoundException;
import pl.adamlangmesser.nbpapi.adapters.out.exchange.rate.excpetions.ExchangeRateProviderException;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;

@Validated
@Component
@RequiredArgsConstructor
class NbpExchangeRateClient {

    private static final String OUTPUT_CURRENCY = "PLN";

    private final RestClient nbpRestClient;
    private final NbpApiProperties properties;
    private final Clock clock;

    public BigDecimal getCurrentUsdToPlnRate() {
        return getUsdToPlnRate(LocalDate.now(clock));
    }

    public BigDecimal getUsdToPlnRate(@NotNull LocalDate date) {
        LocalDate startDate = date.minusDays(properties.lookbackDays());

        try {
            NbpRateResponse response = nbpRestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(properties.ratesPath())
                            .build(Map.of(
                                    "table", properties.table(),
                                    "code", properties.usdCode(),
                                    "startDate", startDate.toString(),
                                    "endDate", date.toString()
                            )))
                    .retrieve()
                    .body(NbpRateResponse.class);

            if (response == null || response.rates() == null || response.rates().isEmpty()) {
                throw new ExchangeRateNotFoundException(
                        properties.usdCode(),
                        OUTPUT_CURRENCY,
                        startDate,
                        date
                );
            }

            return response.rates().stream()
                    .max(Comparator.comparing(NbpRateDto::effectiveDate))
                    .map(NbpRateDto::mid)
                    .orElseThrow(() -> new ExchangeRateNotFoundException(
                            properties.usdCode(),
                            OUTPUT_CURRENCY,
                            startDate,
                            date
                    ));

        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode() == HttpStatusCode.valueOf(404)) {
                throw new ExchangeRateNotFoundException(
                        properties.usdCode(),
                        OUTPUT_CURRENCY,
                        startDate,
                        date
                );
            }

            throw new ExchangeRateProviderException(
                    "NBP returned HTTP error %s while fetching the %s/%s exchange rate for date %s."
                            .formatted(ex.getStatusCode(), properties.usdCode(), OUTPUT_CURRENCY, date),
                    ex
            );
        } catch (RestClientException ex) {
            throw new ExchangeRateProviderException(
                    "Failed to connect to the NBP API while fetching the %s/%s exchange rate for date %s."
                            .formatted(properties.usdCode(), OUTPUT_CURRENCY, date),
                    ex
            );
        }
    }
}