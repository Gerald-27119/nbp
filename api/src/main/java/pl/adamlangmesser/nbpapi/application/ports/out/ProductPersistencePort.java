package pl.adamlangmesser.nbpapi.application.ports.out;

import pl.adamlangmesser.nbpapi.application.model.ProductsPage;
import pl.adamlangmesser.nbpapi.application.model.ProductsQuery;
import pl.adamlangmesser.nbpapi.domain.model.Product;

import java.util.List;

public interface ProductPersistencePort {

    void saveAll(List<Product> products);

    ProductsPage query(ProductsQuery query);

    void clear();
}
