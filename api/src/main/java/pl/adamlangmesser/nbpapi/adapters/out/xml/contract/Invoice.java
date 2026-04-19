package pl.adamlangmesser.nbpapi.adapters.out.xml.contract;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "faktura")
public record Invoice(
        @JacksonXmlProperty(localName = "komputer")
        @JacksonXmlElementWrapper(useWrapping = false)
        List<Product> products
) {
}