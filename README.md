# NHLApp_v3

Spring MVC app displaying data loaded from api (https://api.nhle.com) using [NHLDataLoader2](https://github.com/Tomas-Mas/NHLDataLoader_v2/) stored in Oracle.
	<br>
Apart from some small tweaks biggest change from v2 is the use of Spring boot, Spring Security and Spring Session.

# Technologies
- Maven
- Spring Boot (3.3.2)
- Java 17
- Oracle 19c
- Spring Web MVC
- Spring Security
- Spring Session
- JPA/Hibernate (6.5.2)
- JPA Criteria API
- Tomcat
- JSTL
- JSP, CSS, Javascript

# How to run
TODO

# General Description
Request are authorized by Spring security filters. <br>
Non logged-in users are automatically logged-in as Guest users. <br>
Data are retrieved from db using JPA criteria API. <br>
Controllers then return ModelAndView object containing both jsp page and model with data. <br>
JSP pages have some interactive features set by javascript. <br>
Pages are broken down to components, each component is loaded by javascript separately. This allows for some fancy functionality and in future should make shift to Rest controllers/React smoother. <br>

# Current progress
Only some basic infrastructure and couple of testing views for handling users (register, login). <br>
Main menu ported, only views for user parts are missing(register/login/logout)
Main page fully ported. <br>

# Others
Sql scripts (schema, inserts, views) are in SQL_scripts folder
	<br> <br>
DB model:
![database model](readme-imgs/db_model.png)
