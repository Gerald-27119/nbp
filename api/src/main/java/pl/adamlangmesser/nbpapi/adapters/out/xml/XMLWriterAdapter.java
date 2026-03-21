package pl.adamlangmesser.nbpapi.adapters.out.xml;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.adapters.out.xml.contract.WriteProductDto;
import pl.adamlangmesser.nbpapi.domain.model.Product;

import java.io.IOException;

@Component
@AllArgsConstructor
public class XMLWriterAdapter {

    private final XMLWriter xmlWriter;

    public void writeProduct(Product product) throws IOException {
        xmlWriter.writeProduct(new WriteProductDto(product.name(), product.bookingDate().toString(), product.priceUSD(), product.pricePLN()));
    }
}
