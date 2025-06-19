# 🐱 Cat Facts Collector – Backend Project Requirements

---

## 1. Overview

Cat Facts Collector is a Spring Boot application that collects random cat facts from a public API and stores them in a local database. It exposes REST endpoints to retrieve, delete, and manage these facts. The application also includes a background scheduler that fetches and stores new facts every 5 minutes.

---

## 2. Tech Stack

* **Framework:** Spring Boot (3.x)
* **Scheduling:** Spring `@Scheduled`
* **Database:** H2 (in-memory, default) or PostgreSQL (optional)
* **ORM:** Spring Data JPA
* **JSON Handling:** Jackson (default with Spring Boot)
* **Build Tool:** Maven or Gradle
* **Java Version:** Java 22+

---

## 3. API Integration

**External API:** Cat Fact API
**Endpoint:** `GET https://catfact.ninja/fact`
**Sample Response:**

```json
{
  "fact": "Cats sleep 70% of their lives.",
  "length": 32
}
```

---

## 4. Data Model

### Entity: `CatFact`

| Field         | Type         | Description                       |
| ------------- | ------------ | --------------------------------- |
| `id`          | UUID or Long | Auto-generated unique identifier  |
| `text`        | String       | The actual cat fact               |
| `createdTime` | Timestamp    | Time the fact was saved in the DB |

---

## 5. Scheduler

* **Purpose:** Every 5 minutes, fetch a random cat fact and store it if it doesn't already exist (based on `text`).
* **Implementation Details:**

  * Use `@Scheduled(fixedRate = 300000)` (every 5 minutes)
  * Call the Cat Fact API
  * Save to DB only if the fact doesn't already exist

---

## 6. REST API Endpoints

### `GET /facts`

* **Description:** Returns all stored cat facts with pagination
* **Query Parameters:**

  * `page` (optional, default: 0)
  * `size` (optional, default: 10)
* **Response Example:**

```json
{
  "content": [
    {
      "id": "1",
      "text": "Cats sleep 70% of their lives.",
      "createdTime": "2025-06-17T10:30:00"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 50
}
```

---

### `GET /facts/{id}`

* **Description:** Returns a single cat fact by its ID
* **Success Response:** `200 OK`
* **Error Response:** `404 Not Found` if the ID does not exist

---

### `DELETE /facts/{id}`

* **Description:** Deletes a cat fact by ID
* **Success Response:** `204 No Content`
* **Error Response:** `404 Not Found` if the ID does not exist

---

## 7. Validation Rules

* `text` must be unique in the database (deduplication).
* `text` must not be null or blank.

---

## 8. Error Handling

Use global exception handling with `@RestControllerAdvice` to handle:

* `404 Not Found` for invalid resource IDs
* `500 Internal Server Error` for unexpected exceptions
* JSON parsing issues when calling the external API

---

## 9. Suggested Project Structure

```
src/
└── main/
    └── java/
        └── com/example/catfacts/
            ├── controller/
            │   └── CatFactController.java
            ├── service/
            │   └── CatFactService.java
            ├── repository/
            │   └── CatFactRepository.java
            ├── model/
            │   └── CatFact.java
            ├── scheduler/
            │   └── CatFactFetcher.java
            ├── exception/
            │   ├── GlobalExceptionHandler.java
            │   └── ResourceNotFoundException.java
            ├── dto/
            │   └── CatFactResponse.java (optional)
            └── CatFactsCollectorApplication.java
    └── resources/
        └── application.properties
```

---

## 10. Testing Guidelines

| Layer         | Test Type         | Purpose                             |
| ------------- | ----------------- | ----------------------------------- |
| Service Layer | Unit Tests        | Scheduler logic and deduplication   |
| Controller    | Integration Tests | API endpoint behavior (via MockMvc) |
| Repository    | JPA Tests         | Save and retrieve functionality     |

---

## 11. Future Enhancements (Out of Scope for MVP)

* Mark facts as “favorite”
* Search facts by keyword
* Manual trigger to fetch a new fact
* Export facts to CSV or JSON
* Basic authentication for DELETE operations

---

## 12. MVP Deliverables Summary

| Deliverable                      | Required |
| -------------------------------- | -------- |
| Spring Boot REST API             | ✅        |
| Cat Fact Entity + DB Integration | ✅        |
| External API Integration         | ✅        |
| Scheduler (every 5 mins)         | ✅        |
| Endpoints: GET all/by ID, DELETE | ✅        |
| Pagination Support               | ✅        |
| Global Error Handling            | ✅        |

---




