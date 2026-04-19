package pl.adamlangmesser.nbpapi.adapters.out.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.adapters.out.xml.contract.Invoice;
import pl.adamlangmesser.nbpapi.adapters.out.xml.contract.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

@Component
class XMLWriter {

    private static final Path XML_DIR = Path.of("data");
    private static final Path XML_FILE = XML_DIR.resolve("faktura.xml");

    private final XmlMapper xmlMapper = XmlMapper.builder()
            .findAndAddModules()
            .build();

    public void writeProduct(Product product) throws IOException {
        Files.createDirectories(XML_DIR);

        Invoice invoice;

        if (Files.exists(XML_FILE) && Files.size(XML_FILE) > 0) {
            invoice = xmlMapper.readValue(XML_FILE.toFile(), Invoice.class);
        } else {
            invoice = new Invoice(new ArrayList<>());
        }

        invoice.products().add(product);

        xmlMapper.writeValue(XML_FILE.toFile(), invoice);
    }
}