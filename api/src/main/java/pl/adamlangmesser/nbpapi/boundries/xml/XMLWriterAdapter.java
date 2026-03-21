package pl.adamlangmesser.nbpapi.boundries.xml;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.boundries.xml.dtos.WriteProductDto;

import java.io.IOException;

@Component
@AllArgsConstructor
public class XMLWriterAdapter {

    private final XMLWriter xmlWriter;

    public void writeProduct(WriteProductDto dto) throws IOException {
        xmlWriter.writeProduct(new WriteProduct(dto.name(), dto.bookingDate(), dto.priceUSD(), dto.pricePLN()));
    }
}
