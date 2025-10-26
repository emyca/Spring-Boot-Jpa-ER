
<h2>One-to-One unidirectional mapping through foreign key with @JoinColumn</h3>

* [Context](#context)
* [Database](#database)
* [Example](#example)
* [REST API](#rest-api)
* [UML](#uml)

### Context

This provides REST APIs to manipulate data in DB. 
It uses One-to-One unidirectional mapping through foreign key with `@JoinColumn`

![](./one-to-one-unidir-fk-jc.png)

### Database

Run the app (if it's not running): in IDE (IntelliJ IDEA), run

`com.example.Spring_Boot_Jpa_ER.SpringBootJpaErApplication`.

In IDE console, among other information, should appear

`...: H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:test_db'`

The `test_db` is database for manual testing of the app. It's in in-memory mode.

Start in Web-browser `localhost:8080/h2-console`.

H2 console login page appears. The page has:

```text

JDBC URL: jdbc:h2:mem:test_db
User Name: root
Password: (empty field)

```

Click button `Connect`. H2 database console should appear.
This console already has tables `CUSTOMERS`, `LOCATIONS`.

In SQL statement section run

```sql
SELECT * FROM CUSTOMERS;
SELECT * FROM LOCATIONS;
```

Click `Run`. Beneath of the SQL statement section appears 
tables `CUSTOMERS`, `LOCATIONS`.
It's empty for now.

Data of the tables will be changed after each REST-request fulfilled.
You can check it to perform above-mentioned `SELECT` query.

### Example

Code example can be found [here](../../src/main/java/com/example/Spring_Boot_Jpa_ER/_01_1_to_1_unidir_fk_jc).


### REST API

To test REST API you can use [Postman](https://www.postman.com/) as a testing tool.

This REST API allows to manipulate with data in DB:

| Method    | URL                      | Action                       |
|-----------|--------------------------|------------------------------|
| GET       | `/api/v1/customers`      | Get all customers list       | 
| GET       | `/api/v1/customers/{id}` | Get a customer by id         |
| POST      | `/api/v1/customers`      | Add new customer             |
| PUT       | `/api/v1/customers/{id}` | Update a customer by id      |
| DELETE    | `/api/v1/customers/{id}` | Delete a customer by id      |


Run the app (if it's not running). Test REST API URLs in Postman.

Get all data
```txt
GET http://localhost:8080/api/v1/customers
```
The REST-request can be run in following ways:
* Data do not exist.
* Data exist.

Get data by `id` (e.g., `id` is `1`)
```text
GET http://localhost:8080/api/v1/customers/1
```
The REST-request can be run in following ways:
* `id` does not exist (no data with such `id`).
* `id` exists. 

Add data
```text
POST http://localhost:8080/api/v1/customers
```
The following JSON-objects are sample request bodies 
for separate REST-requests to add data:
```json
{
  "firstName": "Alice",
  "lastName": "Sunny",
  "email": "alice@mail.com",
  "city": "LightCity",
  "street": "Rainbow St.",
  "building": "15A",
  "apartment": "125C"
}
```
```json
{
  "firstName": "Bob",
  "lastName": "Green",
  "email": "bob@mail.com",
  "city": "LightCity",
  "street": "Moon St.",
  "building": "23",
  "apartment": "56"
}
```
```json
{
  "firstName": "Lucy",
  "lastName": "Moon",
  "email": "moon@mail.com",
  "city": "Freeburg",
  "street": "Broad Rd.",
  "building": "14",
  "apartment": "97"
}
```
```json
{
  "firstName": "Tom",
  "lastName": "Clark",
  "email": "tom@mail.com",
  "city": "Freeburg",
  "street": "Swift St.",
  "building": "17",
  "apartment": "188"
}
```

Update data by `id` (e.g., `id` is `2`)
```text
PUT http://localhost:8080/api/v1/customers/2
```
request body
```json
{
  "firstName": "Bob",
  "lastName": "Green",
  "email": "bob.green@mail.com",
  "city": "LightCity",
  "street": "Winter St.",
  "building": "21",
  "apartment": "84"
}
```
The REST-request can be run in following ways:
* `id` does not exist (no data with such `id`).
* `id` exists.

Delete data by `id` (e.g., `id` is `3`)
```text
DELETE http://localhost:8080/api/v1/customers/3
```
The REST-request can be run in following ways:
* `id` does not exist (no data with such `id`).
* `id` exists.
* Run the REST-request with the same `id` once more.

Postman collection can be found [here](./REST_API_One-to-One_UniDir_FK_JC.postman_collection.json).


### UML

UML class-diagram image can be found [here](./1-to-1-unidir-fk-jc-uml.png).
