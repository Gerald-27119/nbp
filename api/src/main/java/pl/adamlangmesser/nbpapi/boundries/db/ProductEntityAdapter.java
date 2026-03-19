package pl.adamlangmesser.nbpapi.boundries.db;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.domain.model.Product;
import pl.adamlangmesser.nbpapi.model.ComputerPurchaseSpecifications;
import pl.adamlangmesser.nbpapi.model.ComputerSearchCriteria;
import pl.adamlangmesser.nbpapi.model.ProductEntity;
import pl.adamlangmesser.nbpapi.model.ProductEntityRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductEntityAdapter {

    private final ProductEntityRepository productEntityRepository;

    @Transactional//TODO czym sie rozni od wersji springowej, jakas obluga bledow?
    public void saveAll(List<Product> products) {//TODO: daj valdiatio nna product, tam gdzie nie uzywasz pełnego zmien na buidler
        List<ProductEntity> productEntities = products.stream()
                .map(this::map)
                .toList();
        productEntityRepository.saveAll(productEntities);
    }

    public List<Product> query(ComputerSearchCriteria criteria) {
        Sort sort = Sort.by(
                Sort.Direction.fromString(criteria.sortDirection()),
                criteria.sortBy()
        );

        return productEntityRepository.findAll(
                ComputerPurchaseSpecifications.byCriteria(criteria),
                sort
        ).stream().map(this::map).toList();
    }

    public void clear() {
        productEntityRepository.deleteAll();
    }

    private ProductEntity map(Product product) {
        return ProductEntity.builder()
                .bookingDate(product.getBookingDate())
                .name(product.getName())
                .pricePLN(product.getPricePLN())
                .priceUSD(product.getPriceUSD())
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
