package pl.adamlangmesser.nbpapi.adapters.in.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.adapters.in.web.contract.ProductSearchQueryDto;
import pl.adamlangmesser.nbpapi.adapters.in.web.contract.ProductDto;
import pl.adamlangmesser.nbpapi.adapters.in.web.contract.ProductsPageDto;
import pl.adamlangmesser.nbpapi.application.ProductQueryService;
import pl.adamlangmesser.nbpapi.application.model.ProductsPage;
import pl.adamlangmesser.nbpapi.application.model.ProductsQuery;

@Component
@AllArgsConstructor
class ProductWebMapper {

    private final ProductQueryService productQueryService;

    public ProductsPageDto query(ProductSearchQueryDto productSearchQueryDto) {
        ProductsPage productsPage = productQueryService.query(
                new ProductsQuery(
                        productSearchQueryDto.nameFragment(),
                        productSearchQueryDto.dateFrom(),
                        productSearchQueryDto.dateTo(),
                        productSearchQueryDto.sortBy(),
                        productSearchQueryDto.sortDirection(),
                        productSearchQueryDto.page(),
                        productSearchQueryDto.pageSize()
                ));


        return new ProductsPageDto(productsPage.products().stream()
                .map(product -> new ProductDto(product.name(), product.bookingDate(), product.priceUSD(), product.pricePLN()))
                .toList(),
                productsPage.totalElements(),
                productsPage.hasNext(),
                productsPage.hasPrevious(),
                productsPage.number()
        );
    }
}
