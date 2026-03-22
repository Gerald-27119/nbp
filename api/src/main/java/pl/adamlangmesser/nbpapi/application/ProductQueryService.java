package pl.adamlangmesser.nbpapi.application;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import pl.adamlangmesser.nbpapi.application.model.ProductsPage;
import pl.adamlangmesser.nbpapi.application.model.ProductsQuery;
import pl.adamlangmesser.nbpapi.application.ports.in.query.QueryProductsUseCase;
import pl.adamlangmesser.nbpapi.application.ports.out.ProductPersistencePort;

@Component
@AllArgsConstructor
@Validated
public class ProductQueryService implements QueryProductsUseCase {

    private final ProductPersistencePort persistencePort;

    @Override
    public ProductsPage query(@NotNull @Valid ProductsQuery query) {
        return persistencePort.query(query);
    }
}