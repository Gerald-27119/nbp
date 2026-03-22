
# URUCHOMIENIE PROJEKTU

Projekt składa się z trzech części:
* **Docker / baza danych PostgreSQL** — katalog `/docker`
* **Backend (Spring Boot + Maven)** — katalog `/api`
* **Frontend (Angular)** — katalog `/web`

---

## WYMAGANIA

Aby uruchomić projekt potrzeba:
* **Docker** oraz **Docker Compose**
* **Java 21** (zalecana wersja: `21.0.10`)
* **Node.js** oraz **npm**

**Frontend korzysta z:**
* npm `11.5.1`
* Angular `21.2.x`

---

## 1. URUCHOMIENIE BAZY DANYCH

Przejdź do katalogu docker:
```bash
cd docker
```

Uruchom PostgreSQL:
```bash
docker compose up -d
```

---

## 2. URUCHOMIENIE BACKENDU

Przejdź do katalogu backendu:
```bash
cd api
```

Sprawdź wersję Javy:
```bash
java -version
```
> **Uwaga:** Backend wymaga Java 21, zalecana wersja to `21.0.10`.

Uruchom aplikację Spring Boot.

**Opcja A — jeśli projekt posiada Maven Wrapper:**
```bash
./mvnw spring-boot:run
```
*Na Windows:*
```powershell
mvnw.cmd spring-boot:run
```

**Opcja B — jeśli używasz globalnie zainstalowanego Mavena:**
```bash
mvn spring-boot:run
```

Backend powinien uruchomić się domyślnie pod adresem: `http://localhost:8080`

---

## 3. URUCHOMIENIE FRONTENDU

Przejdź do katalogu web:
```bash
cd web
```

Zainstaluj zależności:
```bash
npm install
```

Uruchom frontend:
```bash
npm start
```
*albo:*
```bash
ng serve
```

Frontend powinien być dostępny domyślnie pod adresem: `http://localhost:4200`

---

## ZALECANA KOLEJNOŚĆ URUCHAMIANIA

1.  **PostgreSQL** w Dockerze
2.  **Backend**
3.  **Frontend**

---

## SZYBKI START

### Baza danych:
```bash
cd docker && docker compose up -d
```

### Backend:
```bash
cd api && ./mvnw spring-boot:run
```

### Frontend:
```bash
cd web && npm install && npm start
```
***
# O Projekcie

![Widok aplikacji](nbp.png)

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
