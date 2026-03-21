package pl.adamlangmesser.nbpapi.adapters.out.persistence;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import pl.adamlangmesser.nbpapi.adapters.out.persistence.model.ProductEntity;
import pl.adamlangmesser.nbpapi.application.model.ProductsQuery;

import java.util.ArrayList;
import java.util.List;

final class ProductSpecification {

    private ProductSpecification() {
    }

    public static Specification<ProductEntity> byCriteria(ProductsQuery productsQuery) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (productsQuery.nameFragment() != null && !productsQuery.nameFragment().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("name")),
                                "%" + productsQuery.nameFragment().toLowerCase() + "%"
                        )
                );
            }

            if (productsQuery.dateFrom() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get("bookingDate"), productsQuery.dateFrom())
                );
            }

            if (productsQuery.dateTo() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(root.get("bookingDate"), productsQuery.dateTo())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
