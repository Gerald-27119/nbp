package pl.adamlangmesser.nbpapi.adapters.out.xml;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.adapters.out.xml.contract.WriteProductDto;
import pl.adamlangmesser.nbpapi.application.ports.out.ProductXmlWriterPort;
import pl.adamlangmesser.nbpapi.domain.model.Product;

import java.io.IOException;

@Component
@AllArgsConstructor
public class XMLWriterAdapter implements ProductXmlWriterPort {

    private final XMLWriter xmlWriter;

    @Override
    public void writeProduct(Product product) throws IOException {
        xmlWriter.writeProduct(new WriteProductDto(product.name(), product.bookingDate().toString(), product.priceUSD(), product.pricePLN()));
    }
}
