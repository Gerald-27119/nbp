package pl.adamlangmesser.nbpapi.adapters.out.exchange.rate.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "integration.nbp")
public record NbpApiProperties(
        @NotBlank String baseUrl,
        @NotBlank String ratesPath,
        @NotBlank String table,
        @NotBlank String usdCode,
        @Positive int lookbackDays
) {
}
