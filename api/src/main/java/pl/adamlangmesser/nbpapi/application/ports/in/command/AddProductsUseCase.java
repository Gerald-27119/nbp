package pl.adamlangmesser.nbpapi.application.ports.in.command;

import pl.adamlangmesser.nbpapi.domain.model.NewProductData;

import java.util.List;

public interface AddProductsUseCase {
    void addAll(List<NewProductData> newProductData);
}
