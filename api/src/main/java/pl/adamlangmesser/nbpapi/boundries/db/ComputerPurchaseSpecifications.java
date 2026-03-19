package pl.adamlangmesser.nbpapi.boundries.db;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import pl.adamlangmesser.nbpapi.model.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public final class ComputerPurchaseSpecifications {

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

            if (criteria.bookingDate() != null) {
                predicates.add(
                        cb.equal(root.get("bookingDate"), criteria.bookingDate())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
