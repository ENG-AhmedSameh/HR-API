# HR-API
This project provides an API for a HR database. The API is available in two styles: Representational State Transfer (REST) and Simple Object Access Protocol (SOAP).



## REST API (JAX-RS)

[REST-API(JAX-RS) Documentation](https://documenter.getpostman.com/view/33815988/2sA3BhctSg)

## SOAP API (JAX-WS)

[SOAP-API(JAX-WS) Documentation](https://documenter.getpostman.com/view/33815988/2sA3BhctSi)

## REST API Reference

- The REST API provides endpoints for CRUD operation at all the resources in addintion to enpoints for the bussines logic.

- CRUD operations on the resources follow this pattern.

#### Get all entites of table paginated

```http
  GET /HR-API/webapi/{resource}?page=1&size=10
```

| Parameter | Type     | Description                            |
|:----------| :------- |:---------------------------------------|
| `page`    | `int` | **Required**. Number of the page       |
| `size`    | `int` | **Required**. size of entites returned |

#### Get signle entity with id

```http
  GET /HR-API/webapi/{resource}/entity/${id}
```

| Parameter | Type     | Description                           |
| :-------- | :------- |:--------------------------------------|
| `id`      | `int` | **Required**. Id of resource to fetch |

#### Add an entity to table

```http
  POST GET /HR-API/webapi/resource
```

| Body | Location                           | Description                       |
| :-------- |:-----------------------------------| :-------------------------------- |
| `EntityRequest`      | `HR-API\src\main\java\com\HR\dto\` | **Required**. Body of the entity that will be added|

#### Update an entity

```http
  POST /HR-API/webapi/resource/${id}
```
| Parameter | Type     | Description                                           |
| :-------- | :------- |:------------------------------------------------------|
| `id`      | `int` | **Required**. Id of the resource that will be updated |

| Body | Location     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `EntityRequest`      | `HR-API\src\main\java\com\HR\dto\` | **Optional**. Body of attributes to be updated|

#### Delete an entity

```http
  DELETE /HR-API/webapi/resource/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of entity to delete |

## SOAP API Reference

- The SOAP API provides a Web Service for all the entities which contaning CRUD operations on the entity and the bussiness logic of the entity if any.
- The services follow this pattern.

#### Endpoint

| Service Name  | Port Name     | Description                |
| :-------- | :------- | :------------------------- |
| `EntityService` | `EntityPort` | The endpoint of the entity|

#### Information

| Address                   | WSDL                       | Implementation class |
|:--------------------------|:---------------------------|:---------------------|
| `/HR-API/soap/webService` | `/HR-API/soap/entity?wsdl` | `	com.HR.dto.Entity` |


## Technologies Used
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![XML](https://img.shields.io/badge/XML-F44717?style=for-the-badge&logo=xml&logoColor=white)
![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-007396?style=for-the-badge&logo=jakartaee&logoColor=white)
![JAX-RS](https://img.shields.io/badge/JAX--RS-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JAX-WS](https://img.shields.io/badge/JAX--WS-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Jersey](https://img.shields.io/badge/Jersey-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Metro](https://img.shields.io/badge/Metro-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![MapStruct](https://img.shields.io/badge/MapStruct-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![HikariCP](https://img.shields.io/badge/HikariCP-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JAXB](https://img.shields.io/badge/JAXB-ED8B00?style=for-the-badge&logo=java&logoColor=white)