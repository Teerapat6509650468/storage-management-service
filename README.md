# Storage Management Service

## Overview
The Storage Management Service is a Java-based application designed to manage storage areas and products efficiently. It provides APIs for managing warehouses, products, and their respective storage areas. The application leverages Spring Boot for rapid development and Kafka for messaging.

## Features
- Manage warehouses and their storage areas.
- CRUD operations for products.
- Kafka integration for asynchronous messaging.

## Project Structure
```
src/
  main/
    java/
      com.example.storagemanagement/
        controller/        # REST controllers
        kafka/             # Kafka producer and consumer
        model/             # Data models
        repository/        # Data access layer
        service/           # Business logic
    resources/
      application.properties  # Configuration file
      templates/              # HTML templates (if applicable)
  test/
    java/
      com.example.storagemanagement/  # Unit and integration tests
```

## Prerequisites
- Java 17 or higher
- Apache Maven 3.8+
- Kafka server (for messaging)

## Getting Started

### Clone the Repository
```bash
git clone <repository-url>
cd storagemanagement
```

### Build the Project
```bash
mvn clean package -DskipTests
```

### Run the Application
```bash
mvn spring-boot:run
```

### Access the Application
The application will be available at `http://localhost:8080`.

### Deploy with Docker Compose

1. Ensure Docker and Docker Compose are installed on your system.
2. Navigate to the project directory:
   ```bash
   cd storagemanagement
   ```
3. Build the Spring Boot application:
   ```bash
   mvn clean package -DskipTests
   ```
   The JAR file will be generated in the `target` directory (e.g., `target/storagemanagement-0.0.1-SNAPSHOT.jar`).

4. Copy the JAR file to the `spring-app` folder:
   ```bash
   copy target\storagemanagement-0.0.1-SNAPSHOT.jar spring-app\app.jar
   ```

5. Start the services using Docker Compose:
   ```bash
   docker-compose up
   ```

6. Access the application at `http://localhost:8080`.

### Additional Services
- **Kafka**: Kafka is available at `localhost:9092`.
- **Kafdrop**: Kafka UI is available at `http://localhost:9000`.
- **MSSQL Server**: The database is available at `localhost:1433`.
## Setting Up the Database

To set up the database for the Storage Management Service, follow these steps:

### Using Microsoft SQL Server Management Studio (SSMS)

1. **Open SSMS**:
   - Launch Microsoft SQL Server Management Studio and connect to your SQL Server instance.

2. **Create the Database**:
   - Open a new query window in SSMS.
   - Copy the contents of the `create-database.sql` file located in `src/main/resources/` into the query window.

3. **Execute the Script**:
   - Run the script by clicking the "Execute" button or pressing `F5`.
   - This will create the `storage_management` database along with the `warehouse` and `product` tables.

4. **Verify the Database**:
   - Expand the "Databases" node in the Object Explorer to confirm that the `storage_management` database has been created.
   - Check the "Tables" node under the database to ensure the `warehouse` and `product` tables exist.

Once the database is set up, you can configure the application to connect to it by updating the `application.properties` file with the appropriate connection details.

## Configuration
The application can be configured using the `application.properties` file located in `src/main/resources/`. Update the file to set database connections, Kafka configurations, and other properties.

## Testing REST API with Postman

To test the REST API endpoints of the Storage Management Service using Postman, follow these steps:

1. **Install Postman**: Download and install Postman from [https://www.postman.com/downloads/](https://www.postman.com/downloads/).

2. **Import API Collection**:
   - Create a new collection in Postman.
   - Add the following endpoints to the collection:
     - **Get All Products**:
       - Method: `GET`
       - URL: `http://localhost:8080/api/warehouse/products`
     - **Add Product**:
       - Method: `POST`
       - URL: `http://localhost:8080/api/warehouse/products`
       - Body (JSON):
         ```json
          {
              "id": "P001",
              "name": "Product Name",
              "size": 50,
              "warehouseId": "WH001"
          }
         ```
     - **Remove Product**:
       - Method: `DELETE`
       - URL: `http://localhost:8080/api/warehouse/products/{productId}`
     - **Get Warehouse Area**:
       - Method: `GET`
       - URL: `http://localhost:8080/api/warehouse/area`
     - **Initialize Warehouse Area**:
       - Method: `POST`
       - URL: `http://localhost:8080/api/warehouse/area`
       - Body (JSON):
         ```json
          {
            "id": "WH001",
            "totalArea": 2000,
            "availableArea": 2000
          }
         ```

3. **Set Headers**:
   - For POST and DELETE requests, set the `Content-Type` header to `application/json`.

4. **Send Requests**:
   - Use the Postman interface to send requests to the API and view the responses.

5. **Verify Responses**:
   - Ensure the API responses match the expected results for each endpoint.

This will help you test the functionality of the Storage Management Service effectively.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## Contact
For any inquiries or issues, please contact the project maintainer.
