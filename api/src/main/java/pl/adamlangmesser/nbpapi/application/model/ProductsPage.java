package pl.adamlangmesser.nbpapi.application.model;

import lombok.Builder;
import pl.adamlangmesser.nbpapi.domain.model.Product;

import java.util.List;

@Builder
public record ProductsPage(List<Product> products, Long totalElements, Boolean hasNext, Boolean hasPrevious, Integer number) {
}
