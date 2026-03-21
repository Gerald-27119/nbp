package pl.adamlangmesser.nbpapi.adapters.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.adapters.web.contract.ComputerSearchQueryDto;
import pl.adamlangmesser.nbpapi.adapters.web.contract.ProductDto;
import pl.adamlangmesser.nbpapi.adapters.web.contract.ProductsPageDto;
import pl.adamlangmesser.nbpapi.application.ProductQueryService;
import pl.adamlangmesser.nbpapi.domain.model.ProductsPage;
import pl.adamlangmesser.nbpapi.domain.model.ProductsQuery;

@Component
@AllArgsConstructor
class ProductControllerAdapter {

    private final ProductQueryService productQueryService;

    public ProductsPageDto query(ComputerSearchQueryDto computerSearchQueryDto) {
        ProductsPage productsPage = productQueryService.query(new ProductsQuery(
                computerSearchQueryDto.nameFragment(),
                computerSearchQueryDto.dateFrom(),
                computerSearchQueryDto.dateTo(),
                computerSearchQueryDto.sortBy(),
                computerSearchQueryDto.sortDirection(),
                computerSearchQueryDto.page(),
                computerSearchQueryDto.pageSize()
        ));


        return new ProductsPageDto(productsPage.products().stream()
                .map(product -> new ProductDto(product.getName(), product.getBookingDate(), product.getPriceUSD(), product.getPricePLN()))
                .toList(),
                productsPage.totalElements(),
                productsPage.hasNext(),
                productsPage.hasPrevious(),
                productsPage.number()
        );
    }
}
