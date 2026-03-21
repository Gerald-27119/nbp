package pl.adamlangmesser.nbpapi.adapters.xml;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class XMLWriterAdapter {

    private final XMLWriter xmlWriter;

    public void writeProduct(WriteProductDto dto) throws IOException {
        xmlWriter.writeProduct(new WriteProduct(dto.name(), dto.bookingDate(), dto.priceUSD(), dto.pricePLN()));
    }
}
