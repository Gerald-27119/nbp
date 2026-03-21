package pl.adamlangmesser.nbpapi.adapters.web.contract;


import java.util.List;

public record ProductsPageDto(
        List<ProductDto> products,
        Long totalElements,
        Boolean hasNext, Boolean
        hasPrevious,
        Integer number
) {
}
