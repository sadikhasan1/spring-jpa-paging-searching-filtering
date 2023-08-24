# spring-jpa-paging-searching-filtering


```markdown
# Sorting, Searching, Filtering, and Pagination Example

This project demonstrates how to implement sorting, searching, filtering, and pagination using Spring Boot and JPA.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- - [Sorting](#sorting)
- - [Searching](#searching)
- - [Filtering](#filtering)
- - [Pagination](#pagination)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction

This project demonstrates how to implement sorting, searching, filtering, and pagination in a Spring Boot application. It uses JPA and CriteriaBuilder for building dynamic queries based on user inputs.

## Features

### Sorting

You can sort the results by specifying the sort field and sort direction using query parameters.

### Searching

You can search for customers using various criteria such as first name, last name, address, and a global search term.

### Filtering

Filtering can be done based on different attributes such as address, first name, and last name.

### Pagination

The results are paginated, allowing you to retrieve a specific page of customer data.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven

### Installation

1. Clone this repository.
2. Open the project in your preferred IDE.
3. Build the project using Maven.
4. Run the Spring Boot application.

## Usage

Access the following endpoint to test the functionality:

```
GET /customers/{pageNo}?sortField=id&sortDir=asc&pageSize=20&search=&address=&first_name=&last_name=
```

## Endpoints

- `GET /customers/{pageNo}`: Retrieve a page of customers based on sorting, searching, filtering, and pagination parameters.

## Contributing

Contributions are welcome! Feel free to open issues and submit pull requests.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

For any questions or feedback, please contact sadikhasan.official@gmail.com

```
