# REST API in Java with Spring Boot

This project is a simple REST API created with Spring Boot that allows managing the CUSTOMER domain, specifically for the DELETE microservice. The API offers the basic operation such as deleting a customer, displaying the SWAGGER documentation technology screen as the main page.
## Project Structure

- **`DeleteCustomerApplication.java`**: The main class that runs the Spring Boot application and defines the API controller.

- `DELETE /api/customers/delete/{id}`: Allows you to delete the user, under the required ID.

## Requirements

- **JDK 17** o superior.
- **Maven** (for dependency management and project construction).

## Installation

1. **Clone the repository**

    ```bash
    git clone <https://github.com/kevinseya/microservicio-logistic-delete-customer.git>
    ```

2. **Build and run the application** with Maven:

    ```bash
    mvn spring-boot:run
    ```

3. The application run on: `http://localhost:8080`.

## Use of Endpoint

### 1. DELETE /api/customers/delete/{id}

Delete a customer. The request body must contain the user ID details in JSON format.
DELETE request example:
```bash
DELETE /api/customers/delete/{id} Content-Type: application/json
    
    { 
    "id": "550e8400-e29b-41d4-a716-446655440000",
    }
```
**Response:**
```plaintext
    {
        Customer deleted successfully.
    }
```
**Response code:**

- **`200 Updated:`** Customer created successfully.
- **`404 Not Found:`** Customer not found.
- **`500 Internal Server Error:`** Server error.
