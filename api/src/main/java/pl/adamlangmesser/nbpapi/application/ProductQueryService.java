package pl.adamlangmesser.nbpapi.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.adapters.out.persistence.ProductEntityRepositoryAdapter;
import pl.adamlangmesser.nbpapi.application.model.ProductsPage;
import pl.adamlangmesser.nbpapi.application.model.ProductsQuery;

@Component
@AllArgsConstructor
public class ProductQueryService {

    private final ProductEntityRepositoryAdapter adapter;

    public ProductsPage query(ProductsQuery query) {
        return adapter.query(query);
    }

}
