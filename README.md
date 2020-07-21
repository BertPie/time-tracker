# Time Tracker
Application for tracking time spent on tasks.

## Table of Contents
0. [Table of Contents](#table-of-contents)
0. [Aim of the Project](#aim-of-the-project)
0. [Start up](#start-up)
   * [Configure DB](#configure-db)
   * [Run Application](#run-application)
0. [Manual Testing](#manual-testing)

## Aim of the Project

### Clean Architecture
The aim of the project is to test in practice the style of Clean Architecture (aka Onion, Hexagonal Architecture).
This way the `domain` module (not necessarily in Java sense of the word) is at the center of the application.
`infrastructure` and `view` modules are dependent on it, if needed only providing implementation of interfaces from
`domain` via Dependency Inversion.
```
view --> domain <-- infra
```

### Layered Architecture
This is opposed to Layered Architecture where usually `view` depends on `domain` which in turn depends on `infra` and
all of them tend to depend on `common` modules that contain shared classes like exceptions and dto.
```
view --> domain --> infra
```

### Modules Description
`domain` contains everything that can be considered "business logic".

`infra` can include gateways to other services, repositories and everything not strictly connected to business logic
or `domain`. Changing `infra` should not require any changes in `domain`. Changing whether data is stored in memory,
relational database, noSQL database or an outside service should only require injecting different implementation.

`view` includes all communication with clients, that is translating requests and responses. Whether the communication 
happens via REST controllers, console or any other means should not require any changes in `domain`.

### Risks and True Aim of the Project 
Some concepts of Clean Architecture may cause some repetition that maybe considered unnecessary. The aim of this project
is to test which parts of the concept have to be taken as is, what is actually necessary and what is just
"art for the art's sake". One of the ways to accomplish that is changing data sources.

## Start up

### Configure DB
To run the application, first you need to have a running MySql database. You can either start it in a docker container
or set it up manually. 

#### Run Docker script
There is a ready bash script to run a docker container under following path:
```
{project dir}/.start-up-scripts/database/start-my-sql.sh
``` 
Script can also be run manually:  
```
docker run -d -p 3306:3306 --name mysql-tasker-db \
   	-e MYSQL_ROOT_PASSWORD=pass1234 \
   	-e MYSQL_DATABASE=tasker \
   	mysql
```

#### Setup Database Manually
You can also setup MySql database yourself with following credentials:

|||
|----------|----------|
| username | root     |
| password | pass1234 |
| schema   | tasker   |
| port     | 3306     |

### Run Application
To run application go to project directory and run following script:
```
./mvnw spring-boot:run
``` 
**NOTE:** You need to have database running.

## Manual Testing
For manual testing see `.postman` catalog and import collections to Postman Application.
