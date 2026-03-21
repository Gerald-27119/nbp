package pl.adamlangmesser.nbpapi.adapters.exchange.rate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class NBPClient{//TODO: pozmeiniaj levele widocznosci
//    TODO: zrobic ladne porzadne hexagonal!

    //    TODO:dodac cachwoanie, zmienic nazwy
    public BigDecimal getRateUSDtoPLN(LocalDate localDate) {
        String table = "A";
        String code = "USD";
        String startDate = localDate != null ? localDate.minusDays(2).toString() : LocalDate.now().minusDays(2).toString();//TODO:opsiac moje obejscie
        String endDate = localDate != null ? localDate.toString() : LocalDate.now().toString();

        RestClient restClient = RestClient.create("https://api.nbp.pl/api/exchangerates/rates");//przeniesc do properties

        NbpRateResponse response = restClient.get()
                .uri("/{table}/{code}/{startDate}/{endDate}", table, code, startDate, endDate)//to tez pewnie
                .retrieve()
                .body(NbpRateResponse.class);//sprawdz czy porpawna budwoa obiektu an epwno

        if (response == null || response.rates() == null || response.rates().isEmpty()) {
            throw new IllegalStateException("Brak kursu USD z API NBP");//obsluga
        }

        return response.rates().getLast().mid();
    }

    public record NbpRateResponse(
            String table,
            String currency,
            String code,
            List<Rate> rates
    ) {
    }

    public record Rate(
            String no,
            String effectiveDate,
            BigDecimal mid
    ) {
    }

}

//https://api.nbp.pl/api/exchangerates/rates/A/USD/today/
//{
//        "table": "A",
//        "currency": "dolar amerykański",
//        "code": "USD",
//        "rates": [
//        {
//        "no": "053/A/NBP/2026",
//        "effectiveDate": "2026-03-18",
//        "mid": 3.6930
//        }
//        ]
//}

//https://api.nbp.pl/api/exchangerates/rates/A/USD/2025-02-10
//{
//        "table": "A",
//        "currency": "dolar amerykański",
//        "code": "USD",
//        "rates": [
//        {
//        "no": "027/A/NBP/2025",
//        "effectiveDate": "2025-02-10",
//        "mid": 4.0548
//        }
//        ]
//}