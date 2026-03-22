package pl.adamlangmesser.nbpapi.application.ports.out;

import pl.adamlangmesser.nbpapi.domain.model.Product;

import java.io.IOException;

public interface ProductXmlWriterPort {
    void writeProduct(Product product) throws IOException;
}
