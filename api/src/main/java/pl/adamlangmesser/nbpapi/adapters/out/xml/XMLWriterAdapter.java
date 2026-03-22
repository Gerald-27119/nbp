package pl.adamlangmesser.nbpapi.adapters.out.xml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.adapters.out.xml.contract.WriteProductDto;
import pl.adamlangmesser.nbpapi.adapters.out.xml.exceptions.ProductXmlWriteException;
import pl.adamlangmesser.nbpapi.application.ports.out.ProductXmlWriterPort;
import pl.adamlangmesser.nbpapi.domain.model.Product;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class XMLWriterAdapter implements ProductXmlWriterPort {

    private final XMLWriter xmlWriter;

    @Override
    public void writeProduct(Product product) {
        try {
            xmlWriter.writeProduct(
                    new WriteProductDto(
                            product.name(),
                            product.bookingDate().toString(),
                            product.priceUSD(),
                            product.pricePLN()
                    )
            );
        } catch (IOException e) {
            throw new ProductXmlWriteException(
                    "Could not write product '%s' to XML file.".formatted(product.name()),
                    e
            );
        }
    }
}