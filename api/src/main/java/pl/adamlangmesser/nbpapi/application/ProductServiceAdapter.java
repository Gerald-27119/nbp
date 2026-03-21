package pl.adamlangmesser.nbpapi.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.adamlangmesser.nbpapi.application.domain.CreateProductCommand;
import pl.adamlangmesser.nbpapi.application.domain.model.ProductsPage;
import pl.adamlangmesser.nbpapi.application.in.ComputerSearchQueryDto;
import pl.adamlangmesser.nbpapi.application.in.CreateProductDto;
import pl.adamlangmesser.nbpapi.boundries.db.ComputerSearchCriteria;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductServiceAdapter {

    private final ProductService productService;

    public void addAll(List<CreateProductDto> createProductDtos) {
        List<CreateProductCommand> createProductCommands = createProductDtos.stream()
                .map(dto -> new CreateProductCommand(dto.name(), dto.bookingDate(), dto.priceUSD()))
                .toList();

        productService.addAll(createProductCommands);
    }

    public ProductsPage query(ComputerSearchQueryDto computerSearchQueryDto){
        return productService.query(new ComputerSearchCriteria(
                computerSearchQueryDto.nameFragment(),
                computerSearchQueryDto.dateFrom(),
                computerSearchQueryDto.dateTo(),
                computerSearchQueryDto.sortBy(),
                computerSearchQueryDto.sortDirection(),
                computerSearchQueryDto.page(),
                computerSearchQueryDto.pageSize()
        ));
    }

}
