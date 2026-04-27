# Heritage Explorer Backend

Spring Boot REST API for the Heritage Explorer React client.

## Requirements

- Java 17
- Maven 3.9+
- MySQL 8+

## Database

Create a MySQL user/database or use the default settings in `src/main/resources/application.properties`.

```sql
CREATE DATABASE heritage_explorer;
```

Default local credentials are:

- Database: `heritage_explorer`
- User: `root`
- Password: `root`

## Run

```bash
mvn spring-boot:run
```

The API starts on `http://localhost:8080` and allows the React client at `http://localhost:3000`.

Seed accounts:

- Saranya: `admin@heritage.in` / `admin123`
- Paranthi: `user@heritage.in` / `user123`
- Sweety: `creator@heritage.in` / `creator123`
- Sarayu: `guide@heritage.in` / `guide123`

## Main Endpoints

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/tours`
- `POST /api/tours`
- `GET /api/guides`
- `GET /api/bookings`
- `POST /api/bookings`
- `PUT /api/bookings/{id}`
- `GET /api/users`
