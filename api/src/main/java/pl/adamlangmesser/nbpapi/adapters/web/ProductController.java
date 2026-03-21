package pl.adamlangmesser.nbpapi.adapters.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.adamlangmesser.nbpapi.adapters.web.contract.ComputerSearchQueryDto;
import pl.adamlangmesser.nbpapi.adapters.web.contract.ProductsPageDto;


import java.time.LocalDate;


@CrossOrigin(origins = "*")//TODO; write config
@RestController
@RequestMapping("controller")
@AllArgsConstructor
class ProductController {

    private final ProductControllerAdapter productControllerAdapter;

    @GetMapping
    ResponseEntity<ProductsPageDto> query(@RequestParam(required = false) String nameFragment,
                                          @RequestParam(required = false) LocalDate dateFrom,
                                          @RequestParam(required = false) LocalDate dateTo,
                                          @RequestParam(defaultValue = "name") String sortBy,//TODO; tak nie moze byc, defaultowo nie powinno byc zadnychs ortowan
                                          @RequestParam(defaultValue = "asc") String sortDirection,//TODO:zrobic zarkes dat
                                          @RequestParam(defaultValue = "0") Integer page) {//TODO:zrobic zarkes dat) {//TODO:zrobic zarkes dat
        return ResponseEntity.ok(productControllerAdapter.query(
                        new ComputerSearchQueryDto(
                                nameFragment,
                                dateFrom,
                                dateTo,
                                sortBy,
                                sortDirection,
                                page,
                                12
                        )
                )
        );//TODO: fix?/ enhance
    }
}
