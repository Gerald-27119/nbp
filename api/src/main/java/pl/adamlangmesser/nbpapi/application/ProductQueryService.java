package pl.adamlangmesser.nbpapi.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.application.model.ProductsPage;
import pl.adamlangmesser.nbpapi.application.model.ProductsQuery;
import pl.adamlangmesser.nbpapi.application.ports.in.query.QueryProductsUseCase;
import pl.adamlangmesser.nbpapi.application.ports.out.ProductPersistencePort;

@Component
@AllArgsConstructor
public class ProductQueryService implements QueryProductsUseCase {

    private final ProductPersistencePort persistencePort;

    @Override
    public ProductsPage query(ProductsQuery query) {
        return persistencePort.query(query);
    }

}
