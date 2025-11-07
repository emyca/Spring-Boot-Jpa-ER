
<h2>One-to-One bidirectional mapping. Foreign key with @JoinColumn</h3>

* [Context](#context)
* [Database](#database)
* [Example](#example)
* [REST API](#rest-api)
* [UML](#uml)

### Context

This provides REST APIs to manipulate data in DB.
It uses One-to-One bidirectional mapping through foreign key with `@JoinColumn`

![](./one-to-one-bidir-fk-jc.png)

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
This console already has tables `BUYERS`, `ADDRESSES`.

In SQL statement section run

```sql
SELECT * FROM BUYERS;
SELECT * FROM ADDRESSES;
```

Click `Run`. Beneath of the SQL statement section appears
tables `BUYERS`, `ADDRESSES`.
It's empty for now.

Data of the tables will be changed after each REST-request fulfilled.
You can check it to perform above-mentioned `SELECT` query.

### Example

Code example can be found [here](../../src/main/java/com/example/Spring_Boot_Jpa_ER/_02_1_to_1_bidir_fk_jc).


### REST API

To test REST API you can use [Postman](https://www.postman.com/) as a testing tool.

This REST API allows to manipulate with data in DB:

| Method    | URL                        | Action                    |
|-----------|----------------------------|---------------------------|
| GET       | `/api/v1/buyers`           | Get all Buyers            | 
| GET       | `/api/v1/addresses/buyers` | Get all Buyers by Address | 
| GET       | `/api/v1/buyers/{id}`      | Get a Buyer by id         |
| POST      | `/api/v1/buyers`           | Add new Buyer             |
| PUT       | `/api/v1/buyers/{id}`      | Update a Buyer by id      |
| DELETE    | `/api/v1/buyers/{id}`      | Delete a Buyer by id      |


Run the app (if it's not running). Test REST API URLs in Postman.

Get all Buyers
```txt
GET http://localhost:8080/api/v1/buyers
```
The REST-request can be run in following ways:
* Data do not exist.
* Data exist.

Get all Buyers by Address
```txt
GET http://localhost:8080/api/v1/addresses/buyers
```
The REST-request can be run in following ways:
* Data do not exist.
* Data exist.

Get Buyer by `id` (e.g., `id` is `1`)
```text
GET http://localhost:8080/api/v1/buyers/1
```
The REST-request can be run in following ways:
* `id` does not exist (no data with such `id`).
* `id` exists.

Add new Buyer
```text
POST http://localhost:8080/api/v1/buyers
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

Update a Buyer by `id` (e.g., `id` is `2`)
```text
PUT http://localhost:8080/api/v1/buyers/2
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

Delete a Buyer by `id` (e.g., `id` is `3`)
```text
DELETE http://localhost:8080/api/v1/buyers/3
```
The REST-request can be run in following ways:
* `id` does not exist (no data with such `id`).
* `id` exists.
* Run the REST-request with the same `id` once more.

Postman collection can be found [here](./REST_API_One-to-One_BiDir_FK_JC.postman_collection.json).


### UML

UML class-diagram image can be found [here](./1-to-1-bidir-fk-jc-uml.png).
