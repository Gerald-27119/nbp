Oto treść przygotowana w formacie Markdown (`.md`), z zachowaniem odpowiedniej hierarchii, list i bloków kodu dla lepszej czytelności.

***

# URUCHOMIENIE PROJEKTU NBP

Projekt składa się z trzech części:
* **Docker / baza danych PostgreSQL** — katalog `/docker`
* **Backend (Spring Boot + Maven)** — katalog `/api`
* **Frontend (Angular)** — katalog `/web`

---

## WYMAGANIA

Przed uruchomieniem projektu upewnij się, że masz zainstalowane:
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