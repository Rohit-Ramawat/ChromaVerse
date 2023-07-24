# ChromaVerse
## logo:
# Online Shopping Application

* We have developed REST API for this Online Shopping Application. This API performs all the fundamental CRUD operations of any Online Shopping Application platform with user validation at every step. We also created frontend to use this API.
* This project is developed by team of 5 Java Back-end Developers during project week in Masai School. 

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Spring Security
* Javascript
* HTML
* CSS

## Modules

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


## API Module Endpoints

### Login Module

* `POST //login/admin` : Admin can login with username and password provided at the time of registation


### Customer Module

* `POST /customers` : Register a new customer with proper data validation
  


### Admin Module

* `POST /admin` : Register a new admin with proper data validation

### Driver Module

* `POST /driver` : Register a new driver with proper data validation



### Sample API Response for Admin Register

`POST   localhost:8088/admin`

* Request Body

```
    {
        "userName": "xyz123",
        "password": "abcde",
        "name" : "dummy",
        "email" : "dummy@gmail.com",
        "mobileNumber" : "9555555555"
    }
```

* Response

```
   { adminId=1, userName=xyz123, name=dummy, email=dummy@gmail.com, mobileNumber=9555555555 }
   
```
 
### E-R Diagram



![ERDaigram](https://github.com/abhishekyadav0888/able-laborer-3546/assets/111178057/c9429409-ab13-4e85-b1f5-7f7045b037d8)




### Swagger UI

---



---

### Customer and Customer Login Controller

---



---

### Admin and Admin Login Controller

---



---

### Driver and Driver Login Controller

---



---


### Trip Controller

---



---
