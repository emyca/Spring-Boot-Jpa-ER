
# Spring Boot Jpa ER

UNDER DEVELOPMENT

This is Spring Boot demo app. It demonstrates data manipulation (CRUD operations).
The app focuses mostly on the Entity Relationships (ER) in a database. 
This demo app is only for demonstration and/or educational purposes.


## Short Intro

Entity relationships, in a database, provide a representation of how data 
is related to each other in a system (application).

Tables in a relational database are the physical representation of entities.
Rows in a particular table are instances of that particular entity.

Associations are a fundamental concept in ORM that allows you to define 
relationships between entities.

There are some technical ORM terms for entity relationships. 
The owning side initiates the creation of the relationship. 
Typically, this is the side where the foreign key resides. 
The other side is the inverse/referencing side.

#### Types of entity relationships through JPA.

**One-to-One.** An instance of one entity is related to only one instance of another 
entity. Each row of one table is related to only one row of another table.

Methods of One-to-One mapping in JPA:
* Through a foreign key.
* Through a shared primary key.
* Through a join table.

**One-to-Many.** An entity is related to one or many instances of another entity. 
One row of a table is related to several rows in another table.

**Many-to-One.** Many instances of one entity are related to one instance of 
another entity. Several rows of one table are related to one row of another table.

**Many-to-Many.** Many instances of one entity are related to many instances of 
another entity. Several rows in one table are related to several rows in another table.


#### JPA relationship direction.

**Unidirectional Mapping.** A relationship between entities where only one side 
of the relationship is aware of the other. That is, one entity references another entity,
but the referenced entity is unaware of the relationship.

**Bidirectional Mapping.** Establishing relationships between entities in both 
directions, allowing navigation from both sides of the relationship.


## Tech Stack

* [Spring Framework.](https://spring.io/)
* [Spring Data JPA.](https://spring.io/projects/spring-data-jpa)
* [H2 Database.](https://www.h2database.com/html/main.html)
* [Lombok.](https://projectlombok.org/)


## Examples

* [One-to-One unidirectional mapping through foreign key.](docs/_01_1_to_1_unidir_fk_jc/README.md)


## Resources

* [Understanding JPA/Hibernate Associations.](https://www.baeldung.com/jpa-hibernate-associations)
* [One-to-One Relationship in JPA.](https://www.baeldung.com/jpa-one-to-one)
* [Hibernate One to Many Annotation Tutorial.](https://www.baeldung.com/hibernate-one-to-many)
* [Many-To-Many Relationship in JPA.](https://www.baeldung.com/jpa-many-to-many)
* [@JoinColumn Annotation Explained.](https://www.baeldung.com/jpa-join-column)
* [Difference Between @JoinColumn and mappedBy.](https://www.baeldung.com/jpa-joincolumn-vs-mappedby)
* [Difference Between @JoinColumn and @PrimaryKeyJoinColumn in JPA.](https://www.baeldung.com/java-jpa-join-vs-primarykeyjoin)
* [@MapsId Annotation in Hibernate.](https://www.baeldung.com/hibernate-mapsid-annotation)
* [Overview of JPA/Hibernate Cascade Types.](https://www.baeldung.com/jpa-cascade-types)
* [Best way to map the JPA and Hibernate ManyToMany relationship.](https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/)
* [A beginner’s guide to JPA and Hibernate Cascade Types.](https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/)
* [How to synchronize bidirectional entity associations with JPA and Hibernate.](https://vladmihalcea.com/jpa-hibernate-synchronize-bidirectional-entity-associations/)