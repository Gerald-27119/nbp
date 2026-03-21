package pl.adamlangmesser.nbpapi.adapters.persistence;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import pl.adamlangmesser.nbpapi.adapters.persistence.model.ProductEntity;

import java.util.ArrayList;
import java.util.List;

final class ComputerPurchaseSpecifications {

    private ComputerPurchaseSpecifications() {
    }

    public static Specification<ProductEntity> byCriteria(ComputerSearchCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.nameFragment() != null && !criteria.nameFragment().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("name")),
                                "%" + criteria.nameFragment().toLowerCase() + "%"
                        )
                );
            }

            if (criteria.dateFrom() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get("bookingDate"), criteria.dateFrom())
                );
            }

            if (criteria.dateTo() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(root.get("bookingDate"), criteria.dateTo())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
