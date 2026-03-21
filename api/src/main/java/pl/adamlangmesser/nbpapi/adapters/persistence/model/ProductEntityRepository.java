package pl.adamlangmesser.nbpapi.adapters.persistence.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

}
