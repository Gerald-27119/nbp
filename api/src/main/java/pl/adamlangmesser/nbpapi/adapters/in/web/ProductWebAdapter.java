package pl.adamlangmesser.nbpapi.adapters.in.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.adamlangmesser.nbpapi.adapters.in.web.contract.ProductDto;
import pl.adamlangmesser.nbpapi.adapters.in.web.contract.ProductsPageDto;
import pl.adamlangmesser.nbpapi.application.model.ProductsPage;
import pl.adamlangmesser.nbpapi.application.model.ProductsQuery;
import pl.adamlangmesser.nbpapi.application.ports.in.query.QueryProductsUseCase;

import java.time.LocalDate;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("controller")
@AllArgsConstructor
class ProductWebAdapter {

    private final QueryProductsUseCase productsQueryService;

    @GetMapping
    ResponseEntity<ProductsPageDto> query(@RequestParam(required = false) String nameFragment,
                                          @RequestParam(required = false) LocalDate dateFrom,
                                          @RequestParam(required = false) LocalDate dateTo,
                                          @RequestParam(defaultValue = "name") String sortBy,
                                          @RequestParam(defaultValue = "asc") String sortDirection,
                                          @RequestParam(defaultValue = "0") Integer page) {

        ProductsPage productsPage = productsQueryService.query(new ProductsQuery(
                nameFragment,
                dateFrom,
                dateTo,
                sortBy,
                sortDirection,
                page,
                12));

        return ResponseEntity.ok(
                new ProductsPageDto(productsPage.products().stream()
                        .map(product -> new ProductDto(product.name(), product.bookingDate(), product.priceUSD(), product.pricePLN()))
                        .toList(),
                        productsPage.totalElements(),
                        productsPage.hasNext(),
                        productsPage.hasPrevious(),
                        productsPage.number()
                ));

    }
}
