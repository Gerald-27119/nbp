# NBP Project

Projekt jest aplikacją webową składającą się z:

- **bazy danych** - **PostgreSQL**
- **backendu** zbudowanego w oparciu o **Spring Boot + Maven**
- **frontendu** zbudowanego w oparciu o **Angular**

Aplikacja służy do obsługi produktów, przeliczania ich cen z **USD na PLN** z wykorzystaniem kursu pobieranego z **API NBP**, zapisu danych do **PostgreSQL** oraz eksportu produktów do **plików XML**.

Pliki XML generowane przez aplikację są zapisywane w katalogu:

```text
data/xmls
```

## Architektura backendu

Backend został zaprojektowany w stylu inspirowanym **architekturą heksagonalną (Ports and Adapters)**.

### Główne pakiety

#### `application`
Warstwa aplikacyjna zawiera logikę przypadków użycia.  
Znajdują się tutaj między innymi:

- `ProductCommandService`
- `ProductQueryService`
- porty wejściowe i wyjściowe
- modele aplikacyjne, np. `ProductsQuery`, `ProductsPage`

#### `domain.model`
Warstwa domenowa zawiera podstawowe modele biznesowe, np.:

- `Product`
- `NewProductData`

#### `adapters.in.web`
Warstwa wejściowa odpowiedzialna za komunikację HTTP.  
Zawiera:

- kontrolery REST
- DTO webowe
- walidację i obsługę błędów wejścia

#### `adapters.out`
Warstwa wyjściowa odpowiedzialna za integracje z infrastrukturą.  
Zawiera adaptery odpowiedzialne za:

- integrację z API NBP
- zapis i odczyt danych z bazy
- zapis produktów do XML

#### `dev`
Pakiet pomocniczy używany w środowisku developerskim.  
Zawiera klasę ładującą przykłądowe dane do aplikacji.

---

## Architektura frontendu

Frontend został zbudowany w oparciu o **prostą architekturę komponentową**.

Zastosowany został osobny folder `components`, w którym wydzielono komponenty odpowiedzialne za konkretne fragmenty interfejsu.

Przykładowe komponenty:

- `product` – komponent pojedynczego produktu
- `products` – komponent listy produktów
- `products-wrapper` – komponent nadrzędny spinający widok

