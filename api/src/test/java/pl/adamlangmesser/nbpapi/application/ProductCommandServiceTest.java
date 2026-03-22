package pl.adamlangmesser.nbpapi.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.adamlangmesser.nbpapi.application.currency.conversion.CurrencyConverter;
import pl.adamlangmesser.nbpapi.application.ports.out.ExchangeRateProviderPort;
import pl.adamlangmesser.nbpapi.application.ports.out.ProductPersistencePort;
import pl.adamlangmesser.nbpapi.application.ports.out.ProductXmlWriterPort;
import pl.adamlangmesser.nbpapi.domain.model.NewProductData;
import pl.adamlangmesser.nbpapi.domain.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductCommandServiceTest {

    @Mock
    private CurrencyConverter currencyConverter;

    @Mock
    private ExchangeRateProviderPort exchangeRateProvider;

    @Mock
    private ProductPersistencePort productRepository;

    @Mock
    private ProductXmlWriterPort xmlWriter;

    private ProductCommandService productCommandService;

    @BeforeEach
    void setUp() {
        productCommandService = new ProductCommandService(
                currencyConverter,
                exchangeRateProvider,
                productRepository,
                xmlWriter
        );
    }

    @Test
    void shouldCreateProductWriteXmlAndSaveToRepository() {
        // given
        NewProductData input = new NewProductData(
                "Laptop",
                LocalDate.of(2026, 1, 5),
                new BigDecimal("100.00")
        );

        BigDecimal usdToPlnRate = new BigDecimal("4.00");
        BigDecimal pricePln = new BigDecimal("400.00");

        Product expectedProduct = new Product(
                "Laptop",
                LocalDate.of(2026, 1, 5),
                new BigDecimal("100.00"),
                new BigDecimal("400.00")
        );

        when(exchangeRateProvider.getUsdToPlnRate(input.bookingDate())).thenReturn(usdToPlnRate);
        when(currencyConverter.convertUsdToPln(usdToPlnRate, input.priceUSD())).thenReturn(pricePln);

        // when
        productCommandService.addAll(List.of(input));

        // then
        verify(exchangeRateProvider).getUsdToPlnRate(LocalDate.of(2026, 1, 5));
        verify(currencyConverter).convertUsdToPln(new BigDecimal("4.00"), new BigDecimal("100.00"));
        verify(xmlWriter).writeProduct(expectedProduct);
        verify(productRepository).saveAll(List.of(expectedProduct));
        verifyNoMoreInteractions(productRepository, xmlWriter, exchangeRateProvider, currencyConverter);
    }

    @Test
    void shouldProcessAllProducts() {
        // given
        NewProductData first = new NewProductData(
                "Laptop",
                LocalDate.of(2026, 1, 5),
                new BigDecimal("100.00")
        );
        NewProductData second = new NewProductData(
                "Mouse",
                LocalDate.of(2026, 1, 6),
                new BigDecimal("50.00")
        );

        when(exchangeRateProvider.getUsdToPlnRate(first.bookingDate()))
                .thenReturn(new BigDecimal("4.00"));
        when(exchangeRateProvider.getUsdToPlnRate(second.bookingDate()))
                .thenReturn(new BigDecimal("4.10"));

        when(currencyConverter.convertUsdToPln(new BigDecimal("4.00"), new BigDecimal("100.00")))
                .thenReturn(new BigDecimal("400.00"));
        when(currencyConverter.convertUsdToPln(new BigDecimal("4.10"), new BigDecimal("50.00")))
                .thenReturn(new BigDecimal("205.00"));

        Product expectedFirst = new Product(
                "Laptop",
                LocalDate.of(2026, 1, 5),
                new BigDecimal("100.00"),
                new BigDecimal("400.00")
        );

        Product expectedSecond = new Product(
                "Mouse",
                LocalDate.of(2026, 1, 6),
                new BigDecimal("50.00"),
                new BigDecimal("205.00")
        );

        // when
        productCommandService.addAll(List.of(first, second));

        // then
        verify(xmlWriter).writeProduct(expectedFirst);
        verify(xmlWriter).writeProduct(expectedSecond);
        verify(productRepository).saveAll(List.of(expectedFirst, expectedSecond));
    }

    @Test
    void shouldNotSaveAnythingWhenExchangeRateProviderFails() {
        // given
        NewProductData input = new NewProductData(
                "Laptop",
                LocalDate.of(2026, 1, 5),
                new BigDecimal("100.00")
        );

        RuntimeException exception = new RuntimeException("NBP failed");
        when(exchangeRateProvider.getUsdToPlnRate(input.bookingDate())).thenThrow(exception);

        // when
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> productCommandService.addAll(List.of(input))
        );

        // then
        verify(exchangeRateProvider).getUsdToPlnRate(input.bookingDate());
        verifyNoInteractions(currencyConverter);
        verifyNoInteractions(xmlWriter);
        verifyNoInteractions(productRepository);
        org.junit.jupiter.api.Assertions.assertSame(exception, thrown);
    }

    @Test
    void shouldNotSaveToRepositoryWhenXmlWriterFails() {
        // given
        NewProductData input = new NewProductData(
                "Laptop",
                LocalDate.of(2026, 1, 5),
                new BigDecimal("100.00")
        );

        BigDecimal usdToPlnRate = new BigDecimal("4.00");
        BigDecimal pricePln = new BigDecimal("400.00");

        Product expectedProduct = new Product(
                "Laptop",
                LocalDate.of(2026, 1, 5),
                new BigDecimal("100.00"),
                new BigDecimal("400.00")
        );

        when(exchangeRateProvider.getUsdToPlnRate(input.bookingDate())).thenReturn(usdToPlnRate);
        when(currencyConverter.convertUsdToPln(usdToPlnRate, input.priceUSD())).thenReturn(pricePln);
        doThrow(new RuntimeException("XML write failed")).when(xmlWriter).writeProduct(expectedProduct);

        // when
        assertThrows(
                RuntimeException.class,
                () -> productCommandService.addAll(List.of(input))
        );

        // then
        verify(xmlWriter).writeProduct(expectedProduct);
        verifyNoInteractions(productRepository);
    }

    @Test
    void shouldCallDependenciesInExpectedOrderForSingleProduct() {
        // given
        NewProductData input = new NewProductData(
                "Laptop",
                LocalDate.of(2026, 1, 5),
                new BigDecimal("100.00")
        );

        BigDecimal usdToPlnRate = new BigDecimal("4.00");
        BigDecimal pricePln = new BigDecimal("400.00");

        Product expectedProduct = new Product(
                "Laptop",
                LocalDate.of(2026, 1, 5),
                new BigDecimal("100.00"),
                new BigDecimal("400.00")
        );

        when(exchangeRateProvider.getUsdToPlnRate(input.bookingDate())).thenReturn(usdToPlnRate);
        when(currencyConverter.convertUsdToPln(usdToPlnRate, input.priceUSD())).thenReturn(pricePln);

        // when
        productCommandService.addAll(List.of(input));

        // then
        InOrder inOrder = inOrder(exchangeRateProvider, currencyConverter, xmlWriter, productRepository);
        inOrder.verify(exchangeRateProvider).getUsdToPlnRate(input.bookingDate());
        inOrder.verify(currencyConverter).convertUsdToPln(usdToPlnRate, input.priceUSD());
        inOrder.verify(xmlWriter).writeProduct(expectedProduct);
        inOrder.verify(productRepository).saveAll(List.of(expectedProduct));
    }
}