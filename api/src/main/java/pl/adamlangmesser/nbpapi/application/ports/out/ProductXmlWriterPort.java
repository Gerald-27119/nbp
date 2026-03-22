package pl.adamlangmesser.nbpapi.application.ports.out;

import pl.adamlangmesser.nbpapi.domain.model.Product;

public interface ProductXmlWriterPort {
    void writeProduct(Product product);
}
