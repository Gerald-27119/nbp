package pl.adamlangmesser.nbpapi.application.domain.model;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductsPage(List<Product> products, Long totalElements, Boolean hasNext, Boolean hasPrevious, Integer number) {
}
