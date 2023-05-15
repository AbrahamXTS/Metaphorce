<p align="center">
    <a href="https://spring.io/projects/spring-boot" target="blank"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Spring_Framework_Logo_2018.svg/2560px-Spring_Framework_Logo_2018.svg.png" width="300" alt="Spring Logo" /></a>
</p>

# Metaphorce RRHH

<p align="center">
    <img src="https://i.imgur.com/uWtwBez.png" width="600" alt="Nest Logo" />
</p>

## 📖 Documentation

* All endpoints are prefixed with api/v1/

* For schemas, responses and others, please visit the docs at /api/v1/docs

## 📂 Database config

⚠️ Make sure there is an SQL DB with the name "**Metaphorce**"

For database config, visit the application.properties file at ***src/main/resources*** folder.

```
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:${MYSQL_PORT:3306}/${MYSQL_NAME:metaphorce}?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC
spring.datasource.username = ${MYSQL_USER:root}
spring.datasource.password = ${MYSQL_PASS:admin}
```

## 👮🏻 Auth

The 5 main routes are protected by a JWT, to access them it is necessary to add a bearer token that will be provided by the login route. Feel free to create a new user at signup route if you wish.

To add the token as an authorization header from swagger simply click on the authorize button in the header and paste the token you generated earlier.

```
User: metaphorce

Password: m3t4Ph0rc3
```

## 📦 Dependencies

* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Lombok](https://projectlombok.org/)
* [OpenAPI](https://springdoc.org/)
