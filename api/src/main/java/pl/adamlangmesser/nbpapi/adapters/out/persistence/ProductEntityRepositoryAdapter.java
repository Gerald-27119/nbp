package pl.adamlangmesser.nbpapi.adapters.out.persistence;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.adapters.out.persistence.model.ProductEntity;
import pl.adamlangmesser.nbpapi.adapters.out.persistence.model.ProductEntityRepository;
import pl.adamlangmesser.nbpapi.application.model.ProductsPage;
import pl.adamlangmesser.nbpapi.application.model.ProductsQuery;
import pl.adamlangmesser.nbpapi.application.ports.out.ProductPersistencePort;
import pl.adamlangmesser.nbpapi.domain.model.Product;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductEntityRepositoryAdapter implements ProductPersistencePort {

    private final ProductEntityRepository productEntityRepository;

    @Transactional//TODO czym sie rozni od wersji springowej, jakas obluga bledow?
    @Override
    public void saveAll(List<Product> products) {//TODO: daj valdiatio nna product, tam gdzie nie uzywasz pełnego zmien na buidler
        List<ProductEntity> productEntities = products.stream()
                .map(this::map)
                .toList();
        productEntityRepository.saveAll(productEntities);
    }

    @Override
    public ProductsPage query(ProductsQuery query) {
        Sort sort = Sort.by(
                Sort.Direction.fromString(query.sortDirection()),
                query.sortBy()
        );

        Pageable pageable = PageRequest.of(query.page(), query.pageSize(), sort);

        Page<ProductEntity> page = productEntityRepository.findAll(
                ProductSpecification.byCriteria(new ProductsQuery(
                        query.nameFragment(),
                        query.dateFrom(),
                        query.dateTo(),
                        query.sortBy(),
                        query.sortDirection(),
                        query.page(),
                        query.pageSize()
                )),
                pageable);

        return ProductsPage.builder()
                .products(page.get().map(this::map).toList())
                .totalElements(page.getTotalElements())
                .hasPrevious(page.hasPrevious())
                .hasNext(page.hasNext())
                .number(page.getNumber())
                .build();
    }

    @Override
    public void clear() {
        productEntityRepository.deleteAll();
    }

    private ProductEntity map(Product product) {
        return ProductEntity.builder()
                .name(product.name())
                .bookingDate(product.bookingDate())
                .pricePLN(product.pricePLN())
                .priceUSD(product.priceUSD())
                .build();
    }

    private Product map(ProductEntity product) {
        return Product.builder()
                .bookingDate(product.getBookingDate())
                .name(product.getName())
                .pricePLN(product.getPricePLN())
                .priceUSD(product.getPriceUSD())
                .build();
    }

}
