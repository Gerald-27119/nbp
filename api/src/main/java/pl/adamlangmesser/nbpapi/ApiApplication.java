package pl.adamlangmesser.nbpapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
//TODO:porpawa volumenu, testy, opis, frontend porpawki, przetestwoanie isntrukcji odpalenia, screenshot?
//TODO: popraw itnefejsy

//10. Upewnij się, że adaptery korzystają tylko z portów in/out
//
//To nie jest kwestia struktury folderów, tylko zależności w kodzie.
//
//Powinno być tak:
//
//ProductWebAdapter
//
//korzysta z:
//
//AddProduct
//        QueryProducts
//ProductCommandService
//
//korzysta z:
//
//ExchangeRateProviderPort
//        ProductPersistencePort
//ProductXMLWriterPort
//        ProductQueryService
//
//korzysta z:
//
//ProductPersistencePort
//        ProductPersistenceAdapter
//
//implementuje:
//
//ProductPersistencePort
//        ExchangeRateProviderAdapter
//
//implementuje:
//
//ExchangeRateProviderPort
//        XMLWriterAdapter
//
//implementuje:
//
//ProductXMLWriterPort
//
//Jeśli gdziekolwiek application service wstrzykuje konkretny adapter zamiast portu — to jeszcze trzeba poprawić.