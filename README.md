# Logistics API (NearMade)

## Overview
The Logistics API (NearMade) is a backend service built with Spring Boot, designed to handle delivery order management and route optimization. The core feature of this application is its ability to take a starting location and a list of unsorted delivery orders, and calculate the most efficient delivery route.

## Installation Instructions

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Git (to clone the repository)

### Steps to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/shopnearmade/logistics-api.git
   cd logistics-api
   ```

2. **Run the application:**
   Use the provided Maven wrapper to start the Spring Boot server.
   
   *On Windows:*
   ```cmd
   mvn spring-boot:run  
   ```
   
   *On macOS/Linux:*
   ```bash
   ./mvnw spring-boot:run
   ```

3. The application will start and run locally on `http://localhost:8080`.

4. **Access the Database (Optional):**
   You can access the H2 database console while the app is running by navigating to `http://localhost:8080/h2-console` in your web browser. 

## Usage Instructions

You can interact with the API using tools like Postman, or `curl` commands.

### 1. Order Management Endpoints

- **Add a New Order**
  - **Method:** `POST`
  - **URL:** `/api/orders/add`
  - **Body (JSON):**
    ```json
    {
      "dropoffLocation": {
        "latitude": 44.9778,
        "longitude": -93.2650
      }
    }
    ```

- **Retrieve All Orders**
  - **Method:** `GET`
  - **URL:** `/api/orders/all`

- **Mark an Order as Completed (Delivered)**
  - **Method:** `PUT`
  - **URL:** `/api/orders/{id}/complete`

### 2. Route Optimization Endpoints

  - **Method:** `GET`
  - **URL:** `/api/routes/ping`
  - **Response:** "NearMade Routing Engine is LIVE and ready for requests!"

- **Generate Route from Pending Database Orders**
  Calculates the optimal route from a starting coordinate, checking all orders in the database where `isDelivered` is false.
  - **Method:** `POST`
  - **URL:** `/api/routes/generate-from-db`
  - **Body (JSON):**
    ```json
    {
      "startLocation": {
        "latitude": 44.8848,
        "longitude": -93.2223
      }
    }
    ```
  - **Response:** A sorted JSON array of `DeliveryOrder` objects representing the optimized route.
