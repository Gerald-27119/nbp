package pl.adamlangmesser.nbpapi.boundries.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
class XMLWriter {

    private static final Path XML_DIR = Paths.get("data", "xmls");

    public void writeProduct(WriteProduct writeProduct) throws IOException {
        Files.createDirectories(XML_DIR);

        String fileName = writeProduct.name() + "_" + UUID.randomUUID() + ".xml";
        Path target = XML_DIR.resolve(fileName);

        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(target.toFile(), writeProduct);
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