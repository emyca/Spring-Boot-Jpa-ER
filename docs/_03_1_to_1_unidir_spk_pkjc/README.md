
<h2>One-to-One unidirectional mapping. Shared primary key with @PrimaryKeyJoinColumn</h3>

* [Context](#context)
* [Database](#database)
* [Example](#example)
* [REST API](#rest-api)
* [UML](#uml)

### Context

This provides REST APIs to manipulate data in DB.
It uses One-to-One unidirectional mapping through shared primary key with `@PimaryKeyJoinColumn`

![](./one-to-one-unidir-spk-pkjc.png)

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
This console already has tables `CLIENTS`, `RESIDENCES`.

In SQL statement section run

```sql
SELECT * FROM CLIENTS;
SELECT * FROM RESIDENCES;
```

Click `Run`. Beneath of the SQL statement section appears
tables `CLIENTS`, `RESIDENCES`.
It's empty for now.

Data of the tables will be changed after each REST-request fulfilled.
You can check it to perform above-mentioned `SELECT` query.

### Example

Code example can be found [here](../../src/main/java/com/example/Spring_Boot_Jpa_ER/_03_1_to_1_unidir_spk_pkjc).


### REST API

To test REST API you can use [Postman](https://www.postman.com/) as a testing tool.

This REST API allows to manipulate with data in DB:

| Method    | URL                        | Action                |
|-----------|----------------------------|-----------------------|
| GET       | `/api/v1/clients`          | Get all Clients       |
| GET       | `/api/v1/clients/{id}`     | Get a Client by id    |
| POST      | `/api/v1/clients`          | Add new Client        |
| PUT       | `/api/v1/clients/{id}`     | Update a Client by id |
| DELETE    | `/api/v1/clients/{id}`     | Delete a Client by id |


Run the app (if it's not running). Test REST API URLs in Postman.

Get all Clients
```txt
GET http://localhost:8080/api/v1/clients
```
The REST-request can be run in following ways:
* Data do not exist.
* Data exist.

Get a Client by `id` (e.g., `id` is `1`)
```text
GET http://localhost:8080/api/v1/clients/1
```
The REST-request can be run in following ways:
* `id` does not exist (no data with such `id`).
* `id` exists.

Add new Client
```text
POST http://localhost:8080/api/v1/clients
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

Update a Client by `id` (e.g., `id` is `2`)
```text
PUT http://localhost:8080/api/v1/clients/2
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

Delete a Client by `id` (e.g., `id` is `3`)
```text
DELETE http://localhost:8080/api/v1/clients/3
```
The REST-request can be run in following ways:
* `id` does not exist (no data with such `id`).
* `id` exists.
* Run the REST-request with the same `id` once more.

Postman collection can be found [here](./REST_API_One-to-One_UniDir_SPK_PKJC.postman_collection.json).


### UML

UML class-diagram can be found [here](./1-to-1-unidir-spk-pkjc-uml.puml).
To read and preview PlantUML (`.puml`) files in IntelliJ IDEA, you need
to install the `plantuml4idea` plugin.
