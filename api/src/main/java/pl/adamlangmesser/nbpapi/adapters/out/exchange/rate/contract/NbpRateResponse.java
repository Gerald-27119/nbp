package pl.adamlangmesser.nbpapi.adapters.out.exchange.rate.contract;

import java.util.List;

public record NbpRateResponse(
        String table,
        String currency,
        String code,
        List<NbpRateDto> rates
) {
}