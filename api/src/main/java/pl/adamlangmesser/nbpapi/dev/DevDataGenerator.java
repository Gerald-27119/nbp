package pl.adamlangmesser.nbpapi.dev;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.adapters.out.persistence.ProductEntityRepositoryAdapter;
import pl.adamlangmesser.nbpapi.application.ProductCommandService;
import pl.adamlangmesser.nbpapi.domain.model.NewProductData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Profile("dev")
@Component
@AllArgsConstructor
class DevDataGenerator implements ApplicationRunner {

    private final ProductCommandService productCommandService;
    private final ProductEntityRepositoryAdapter productEntityRepositoryAdapter;

    //  - komputer ACER Aspire – kwota 345 USD
    //  - komputer DELL Latitude – kwota 543 USD
    //  - komputer HP Victus – kwota 346 USD

    //    Komputer ACER należy zapisać z datą przewalutowania 5 stycznia 2026.
    //    Komputer DELL z datą przewalutowania 11 stycznia 2026 a
    //    komputer HP z datą 19 stycznia 2026.

    @Override
    public void run(ApplicationArguments args) {
        productEntityRepositoryAdapter.clear();

        this.populateDB();
        this.extraPopulateDB();
    }

    private void populateDB() {
        var ACER = new NewProductData("ACER Aspire", LocalDate.of(2026, Month.JANUARY, 5), BigDecimal.valueOf(345));
        var DELL = new NewProductData("DELL Latitude", LocalDate.of(2026, Month.JANUARY, 11), BigDecimal.valueOf(543));
        var HP = new NewProductData("HP Victus", LocalDate.of(2026, Month.JANUARY, 19), BigDecimal.valueOf(346));

        productCommandService.addAll(List.of(ACER, DELL, HP));
    }

    private void extraPopulateDB() {
        var LENOVO_1 = new NewProductData("LENOVO ThinkPad E14", LocalDate.of(2026, Month.JANUARY, 3), BigDecimal.valueOf(410));
        var ASUS_1 = new NewProductData("ASUS ZenBook 14", LocalDate.of(2026, Month.JANUARY, 7), BigDecimal.valueOf(615));
        var MSI_1 = new NewProductData("MSI Katana", LocalDate.of(2026, Month.JANUARY, 9), BigDecimal.valueOf(720));
        var APPLE_1 = new NewProductData("APPLE MacBook Air M2", LocalDate.of(2026, Month.JANUARY, 13), BigDecimal.valueOf(999));
        var SAMSUNG_1 = new NewProductData("SAMSUNG Galaxy Book", LocalDate.of(2026, Month.JANUARY, 15), BigDecimal.valueOf(680));
        var HUAWEI_1 = new NewProductData("HUAWEI MateBook D16", LocalDate.of(2026, Month.JANUARY, 18), BigDecimal.valueOf(570));
        var MICROSOFT_1 = new NewProductData("MICROSOFT Surface Laptop 5", LocalDate.of(2026, Month.JANUARY, 21), BigDecimal.valueOf(1040));
        var RAZER_1 = new NewProductData("RAZER Blade 15", LocalDate.of(2026, Month.JANUARY, 24), BigDecimal.valueOf(1450));
        var TOSHIBA_1 = new NewProductData("TOSHIBA Dynabook", LocalDate.of(2026, Month.JANUARY, 27), BigDecimal.valueOf(430));
        var FUJITSU_1 = new NewProductData("FUJITSU Lifebook U931", LocalDate.of(2026, Month.JANUARY, 30), BigDecimal.valueOf(760));

        var LENOVO_2 = new NewProductData("LENOVO IdeaPad 5", LocalDate.of(2026, Month.FEBRUARY, 2), BigDecimal.valueOf(520));
        var ASUS_2 = new NewProductData("ASUS TUF Gaming A15", LocalDate.of(2026, Month.FEBRUARY, 5), BigDecimal.valueOf(810));
        var MSI_2 = new NewProductData("MSI Modern 15", LocalDate.of(2026, Month.FEBRUARY, 8), BigDecimal.valueOf(590));
        var APPLE_2 = new NewProductData("APPLE MacBook Pro 14", LocalDate.of(2026, Month.FEBRUARY, 10), BigDecimal.valueOf(1899));
        var SAMSUNG_2 = new NewProductData("SAMSUNG Book3 Pro", LocalDate.of(2026, Month.FEBRUARY, 12), BigDecimal.valueOf(1240));
        var HUAWEI_2 = new NewProductData("HUAWEI MateBook X Pro", LocalDate.of(2026, Month.FEBRUARY, 14), BigDecimal.valueOf(1390));
        var MICROSOFT_2 = new NewProductData("MICROSOFT Surface Go Laptop", LocalDate.of(2026, Month.FEBRUARY, 17), BigDecimal.valueOf(730));
        var RAZER_2 = new NewProductData("RAZER Book 13", LocalDate.of(2026, Month.FEBRUARY, 20), BigDecimal.valueOf(1180));
        var TOSHIBA_2 = new NewProductData("TOSHIBA Tecra A40", LocalDate.of(2026, Month.FEBRUARY, 22), BigDecimal.valueOf(650));
        var FUJITSU_2 = new NewProductData("FUJITSU Lifebook A3511", LocalDate.of(2026, Month.FEBRUARY, 25), BigDecimal.valueOf(545));

        var LENOVO_3 = new NewProductData("LENOVO Legion 5", LocalDate.of(2026, Month.FEBRUARY, 1), BigDecimal.valueOf(960));
        var ASUS_3 = new NewProductData("ASUS VivoBook 15", LocalDate.of(2026, Month.MARCH, 4), BigDecimal.valueOf(480));
        var MSI_3 = new NewProductData("MSI Stealth 14", LocalDate.of(2026, Month.JANUARY, 7), BigDecimal.valueOf(1320));
        var APPLE_3 = new NewProductData("APPLE MacBook Air M3", LocalDate.of(2025, Month.MARCH, 10), BigDecimal.valueOf(1120));
        var SAMSUNG_3 = new NewProductData("SAMSUNG Galaxy Book Flex", LocalDate.of(2026, Month.JANUARY, 13), BigDecimal.valueOf(910));
        var HUAWEI_3 = new NewProductData("HUAWEI MateBook 14", LocalDate.of(2026, Month.JANUARY, 16), BigDecimal.valueOf(640));
        var MICROSOFT_3 = new NewProductData("MICROSOFT Surface Laptop Studio", LocalDate.of(2026, Month.JANUARY, 19), BigDecimal.valueOf(1740));
        var RAZER_3 = new NewProductData("RAZER Blade 14", LocalDate.of(2026, Month.JANUARY, 22), BigDecimal.valueOf(1580));
        var TOSHIBA_3 = new NewProductData("TOSHIBA Portege X30", LocalDate.of(2025, Month.DECEMBER, 25), BigDecimal.valueOf(870));
        var FUJITSU_3 = new NewProductData("FUJITSU Stylistic Q7312", LocalDate.of(2026, Month.FEBRUARY, 28), BigDecimal.valueOf(980));

        productCommandService.addAll(List.of(
                LENOVO_1, ASUS_1, MSI_1, APPLE_1, SAMSUNG_1,
                HUAWEI_1, MICROSOFT_1, RAZER_1, TOSHIBA_1, FUJITSU_1,
                LENOVO_2, ASUS_2, MSI_2, APPLE_2, SAMSUNG_2,
                HUAWEI_2, MICROSOFT_2, RAZER_2, TOSHIBA_2, FUJITSU_2,
                LENOVO_3, ASUS_3, MSI_3, APPLE_3, SAMSUNG_3,
                HUAWEI_3, MICROSOFT_3, RAZER_3, TOSHIBA_3, FUJITSU_3)
        );
    }

}
