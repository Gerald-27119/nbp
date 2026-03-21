package pl.adamlangmesser.nbpapi.boundries.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class XMLWriter {

    private static final Path XML_DIR = Paths.get("data", "xmls");

    public void write(XMLProduct xmlProduct) throws IOException {
        Files.createDirectories(XML_DIR);

        String fileName = xmlProduct.name() + "_" + UUID.randomUUID() + ".xml";
        Path target = XML_DIR.resolve(fileName);

        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(target.toFile(), xmlProduct);
    }

    public record XMLProduct(String name, String bookingDate, BigDecimal priceUSD, BigDecimal pricePLN) {
    }
}

//Format pliku XML:
//<faktura>
// <komputer>
//     <nazwa></nazwa>
//     <data_ksiegowania></data_ksiegowania>
//     <koszt_USD></koszt_USD>
//     <koszt_PLN></koszt_PLN>
// </komputer>
// <komputer>
//     <nazwa></nazwa>
//     <data_ksiegowania></data_ksiegowania>
//     <koszt_USD></koszt_USD>
//     <koszt_PLN></koszt_PLN>
// </komputer>
//</faktura>