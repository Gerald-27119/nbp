package pl.adamlangmesser.nbpapi.application.ports.in.query;

import pl.adamlangmesser.nbpapi.application.model.ProductsPage;
import pl.adamlangmesser.nbpapi.application.model.ProductsQuery;

public interface QueryProductsUseCase {

    ProductsPage query(ProductsQuery productsQuery);
}
