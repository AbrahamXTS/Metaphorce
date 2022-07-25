<p align="center">
    <a href="https://spring.io/projects/spring-boot" target="blank"><img src="https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg" width="200" alt="Spring Logo" /></a>
</p>

# Metaphorce RRHH

<p align="center">
    <img src="https://i.imgur.com/uWtwBez.png" width="600" alt="Nest Logo" />
</p>

### 📖 Documentation

For schemas, responses and others, please visit the docs at /api/v1/docs

### 📂 Database config

For database config, visit the application.properties file at resources folder.

⚠️ Make sure there is an SQL DB with the name "**Metaphorce**"
```
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:${MYSQL_PORT:3306}/${MYSQL_NAME:metaphorce}?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC
spring.datasource.username = ${MYSQL_USER:root}
spring.datasource.password = ${MYSQL_PASS:admin}
```

### 📦 Dependencies

* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Lombok](https://projectlombok.org/)
* [OpenAPI](https://springdoc.org/)
