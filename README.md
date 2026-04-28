# Heritage Explorer Backend

Spring Boot REST API for the Heritage Explorer React client.

## Requirements

- Java 17
- Maven 3.9+
- MySQL 8+

## Database

Create a MySQL database named `fsad`, or set `DB_URL` to the database you want to use.

```sql
CREATE DATABASE IF NOT EXISTS fsad;
```

Default local settings are:

- Database: `fsad`
- User: `root`
- Password: empty

If you want to use the `admin` user locally, run this in the **Local instance MySQL80** connection as `root`:

```sql
CREATE DATABASE IF NOT EXISTS fsad;
CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'YOUR_MYSQL_PASSWORD';
ALTER USER 'admin'@'localhost' IDENTIFIED BY 'YOUR_MYSQL_PASSWORD';
GRANT ALL PRIVILEGES ON fsad.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;
```

Then start the backend with these environment variables:

```bash
DB_USERNAME=admin
DB_PASSWORD=YOUR_MYSQL_PASSWORD
```

For the remote `fsad` database shown in MySQL Workbench, use its full host in `DB_URL` instead of `localhost`.

```bash
DB_URL=jdbc:mysql://YOUR_REMOTE_HOST:3306/fsad?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
DB_USERNAME=admin
DB_PASSWORD=YOUR_MYSQL_PASSWORD
```

## Run

```bash
mvn spring-boot:run
```

The API starts on `http://localhost:8081` and allows the React client at `http://localhost:3000`.

## Render Deployment

Create a Render Web Service from the `backend` folder.

- Runtime: Docker
- Root directory: `backend`
- Health check path: `/api/tours`

Set these environment variables in Render:

```bash
DB_URL=jdbc:mysql://YOUR_REMOTE_HOST:3306/fsad?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
DB_USERNAME=admin
DB_PASSWORD=YOUR_MYSQL_PASSWORD
CORS_ALLOWED_ORIGIN=https://YOUR_FRONTEND_DOMAIN
```

Do not set `PORT`; Render provides it automatically.

If you deploy the React app in `mytour`, set:

```bash
REACT_APP_API_BASE_URL=https://YOUR_BACKEND_ON_RENDER.onrender.com/api
```

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
