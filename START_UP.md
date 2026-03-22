
---

# `RUNNING.md`

```md
# Uruchomienie projektu

Ten dokument opisuje krok po kroku, jak uruchomić projekt lokalnie.

---

## Wymagania

Przed uruchomieniem projektu upewnij się, że masz zainstalowane:

- **Java 21** lub wersję zgodną z projektem
- **Maven**
- **Node.js**
- **npm**
- **Docker**
- **Docker Compose**

---

## 1. Uruchomienie PostgreSQL w Dockerze

Projekt wykorzystuje bazę danych PostgreSQL uruchamianą przez Docker Compose.

Plik Compose znajduje się w katalogu:

```text
docker/compose.yaml