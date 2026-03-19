package pl.adamlangmesser.nbpapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "products")
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "nazwa")
    private String name;

    @Column(name = "data_ksiegowania")
    private LocalDate bookingDate;

    @Column(name = "koszt_USD")
    private BigDecimal priceUSD;

    @Column(name = "koszt_PLN")
    private BigDecimal pricePLN;
}
//Tabela w bazie danych:
//nazwa | data_ksiegowania | koszt_USD | koszt_PLN