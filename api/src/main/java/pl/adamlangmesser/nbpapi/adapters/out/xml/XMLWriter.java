package pl.adamlangmesser.nbpapi.adapters.out.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.adapters.out.xml.contract.WriteProductDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Component
class XMLWriter {

    private static final Path XML_DIR = Path.of("data", "xmls");
    private final XmlMapper xmlMapper = XmlMapper.builder()
            .findAndAddModules()
            .build();

    public void writeProduct(WriteProductDto writeProductDto) throws IOException {
        Files.createDirectories(XML_DIR);

        String fileName = writeProductDto.name() + "_" + UUID.randomUUID() + ".xml";
        Path target = XML_DIR.resolve(fileName);

        xmlMapper.writeValue(target.toFile(), writeProductDto);
    }
}