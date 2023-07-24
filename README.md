# ChromaVerse
The primary objective of our online shopping application is to provide a seamless, user-friendly, and secure platform for customers to browse, select, and purchase a wide range of products.
## logo:
![logo](https://github.com/Rohit-Ramawat/medical-bells-7477/assets/119414002/ac36806e-2bf8-4fe7-a7a9-0209287f7139)

# Online Shopping Application:

* We have developed REST API for this Online Shopping Application. This API performs all the fundamental CRUD operations of any Online Shopping Application platform with user validation at every step. We also created frontend to use this API.
* This project is developed by team of 5 Java Back-end Developers during project week in Masai School. 

## Tech Stack:
This Application utilizes the following technologies:

- Backend Technologies: Java, Spring Boot
- Frontend Technologies: HTML, CSS, JavaScript
- Database: MySQL
- Additional Libraries: Spring Security, Hibernate, Maven

## Modules:

* Login Module
* Customer Module
* Product Module
* Cart Module
* Order Module

## Features

* Admin, Customer authentication JWT Token.
* Admin Features:
    * Administrator Role of the entire application
* Customer Features:
    * Registering themselves with application, and logging in to get the valid JWT token
    * View products and add them into the cart to order. 

## ER Daigram:
![ER_ChromaVerse](https://github.com/Rohit-Ramawat/medical-bells-7477/assets/119414002/1ab48565-290e-42e3-aa78-23612d7e189b)


## Contributors

* [@Rohit Ramawat](https://github.com/Rohit-Ramawat)
* [@Ashish Kumar](https://github.com/Ashish0076)
* [@Ajit Kumar Roy](https://github.com/Ajitroy01)
* [@Ravi Sharma](https://github.com/RaviSharma7877)
* [@Shubham Sahu](https://github.com/sshubham26)

## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](https://github.com/Rohit-Ramawat/medical-bells-7477/blob/main/Online_Shopping_Application_Backend/src/main/resources/application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8888

    spring.datasource.url=jdbc:mysql://localhost:3306/rideeasy
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root

```

## API Root Endpoint

`https://localhost:8088/`

`http://localhost:8088/swagger-ui.html/`







 












