package pl.adamlangmesser.nbpapi.dev;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.boundries.db.ProductEntityAdapter;
import pl.adamlangmesser.nbpapi.boundries.nbp.RateClient;
import pl.adamlangmesser.nbpapi.domain.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

@Profile("dev")
@Component
@AllArgsConstructor
class Dev implements ApplicationRunner {

    private final RateClient rateClient;
    private final ProductEntityAdapter productEntityAdapter;

    //  - komputer ACER Aspire – kwota 345 USD
    //  - komputer DELL Latitude – kwota 543 USD
    //  - komputer HP Victus – kwota 346 USD

    //    Komputer ACER należy zapisać z datą przewalutowania 5 stycznia 2026.
    //    Komputer DELL z datą przewalutowania 11 stycznia 2026 a
    //    komputer HP z datą 19 stycznia 2026.

    private void populateDB() {
        productEntityAdapter.clear();

        var ACER = new Product("ACER Aspire", LocalDate.of(2026, Month.JANUARY, 5), BigDecimal.valueOf(345));
        var DELL = new Product("DELL Latitude", LocalDate.of(2026, Month.JANUARY, 11), BigDecimal.valueOf(543));
        var HP = new Product("HP Victus", LocalDate.of(2026, Month.JANUARY, 19), BigDecimal.valueOf(346));

        productEntityAdapter.saveAll(Stream.of(ACER, DELL, HP).peek(laptop -> {
            var rate = rateClient.getRateUSDtoPLN(laptop.getBookingDate());
            var pricePLN = laptop.getPriceUSD().multiply(rate);
            laptop.setPricePLN(pricePLN);
        }).toList());
    }

    @Override
    public void run(ApplicationArguments args) {
        this.populateDB();
    }
}
