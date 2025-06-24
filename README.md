
# Microservice Architecture with Spring Boot & React

![Microservice Architecture](https://img.shields.io/badge/architecture-microservice-blue)
![Spring Boot](https://img.shields.io/badge/backend-springboot-brightgreen)
![React](https://img.shields.io/badge/frontend-react-violet)

A **full-stack microservice application** built using **Spring Boot** for the backend and **React (with Vite)** for the frontend.

---

## 🧠 Architecture Overview

```mermaid
graph TD
    A[React/Vite Frontend] --> B[API Gateway]
    B --> C[Eureka Discovery]
    B --> D[Product Service]
    B --> E[Inventory Service]
    C --> F[MySQL Database]
    D --> G[MongoDB Database]
    C --> H[Eureka Server]
    D --> H
    B --> I[Keycloak (Auth)]
```

---

## 🧰 Tech Stack

| Layer               | Technology               |
|--------------------|--------------------------|
| Frontend           | React + Vite             |
| API Gateway        | Spring Cloud Gateway     |
| Service Discovery  | Eureka Server            |
| Authentication     | Keycloak (OAuth2/OIDC)   |
| Backend Services   | Spring Boot (Java 17+)   |
| Databases          | MySQL (Relational), MongoDB (NoSQL) |
| Configuration      | Spring Cloud Config Server *(optional)* |
| Build Tool         | Maven                    |
| Containerization   | Docker + Docker Compose  |

---

## 📦 Project Structure

```
microService/
├── backend/
│   ├── eureka-server/       # Service discovery
│   ├── api-gateway/         # Gateway routing and auth
│   ├── user-service/        # Handles user data (MySQL)
│   ├── product-service/     # Handles product data (MongoDB)
│   └── config-server/       # Centralized config (optional)
├── frontend/                # React + Vite UI
└── docker/
    └── docker-compose-infra.yml  # Keycloak, MySQL, MongoDB, etc.
```

---

## 🚀 Setup Instructions

### 1️⃣ Backend Setup

```bash
# Clone the repository
git clone https://github.com/sfar2001/microService.git
cd microService

# Start infrastructure (Keycloak, MySQL, MongoDB, Eureka, etc.)
docker-compose -f docker/docker-compose-infra.yml up -d

# Build all services
mvn clean install
```

#### 🧪 Run Backend Services in Order:

1. `eureka-server`
2. `config-server` *(if used)*
3. `user-service`, `product-service`, and any other microservices
4. `api-gateway`

> ⚠️ Ensure your database containers are running before starting microservices.

---

### 2️⃣ Frontend Setup

```bash
# Navigate to frontend directory
cd frontend

# Install dependencies
npm install

# Start the development server
npm run dev
```

---

## 🔐 Authentication

- Uses **Keycloak** for OAuth2 & OpenID Connect
- Admin Console (default):
  - URL: `http://localhost:8080`
  - Username: `admin`
  - Password: `admin`

---

## 📌 Notes

- Make sure Java 17+ is installed and set as default.
- The frontend communicates through the **API Gateway** to access microservices.
- Configuration properties can be centralized using `config-server`.

---

## 📫 Contact

Created by **Adam Sfar**  
IEEE Project Manager | Full Stack Engineer  
🔗 [GitHub Profile](https://github.com/sfar2001)
