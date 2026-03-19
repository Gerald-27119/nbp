package pl.adamlangmesser.nbpapi.boundries.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.adamlangmesser.nbpapi.domain.AmountConverter;
import pl.adamlangmesser.nbpapi.domain.Service;
import pl.adamlangmesser.nbpapi.domain.model.Product;
import pl.adamlangmesser.nbpapi.model.ComputerSearchCriteria;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("controller")
@AllArgsConstructor
class Controller {

    private final AmountConverter amountConverter;
    private final Service service;

    @GetMapping("convert/{amount}")
    ResponseEntity<BigDecimal> getExchangeRates(@PathVariable BigDecimal amount) {//TODO: change name to convert
        return ResponseEntity.ok(amountConverter.convertFromUSDtoPLN(amount));
    }

    @GetMapping
    ResponseEntity<List<Product>> query(@RequestBody ComputerSearchCriteria criteria) {//TODO:zrobic zarkes dat
        return ResponseEntity.ok(service.query(criteria));
    }
}
