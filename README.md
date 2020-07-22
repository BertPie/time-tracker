# Time Tracker
Application for tracking time spent on tasks.

## Table of Contents
0. [Table of Contents](#table-of-contents)
0. [Aim of the Project](#aim-of-the-project)
0. [Start up](#start-up)
    * [Requirements](#requirements)
    * [Configure DB](#configure-db)
    * [Run Application](#run-application)
0. [RESTful API](#restful-api)
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

### Requirements
To start the application you need to have properly setup Java 14 and MySql database.

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

## RESTful API
The communication with sever is done via HTTP protocol. Some information is stored as path variables some in body
as JSON. More information can be found below and in `.postman` catalog the a repository, where example requests are
stored.

### Category
Category is used to group tasks together. Consists of following fields:

|Field      |Type      |
|-----------|----------|
|code       | String   |
|name       | String   |
|description| String   |

#### Creation

##### Request
| Request    |                           |
|------------|---------------------------|
| url        | localhost:8080/categories |
| method     | POST                      |

```json

{
  "code" : "CAT",
  "name" : "Category",
  "description" : "Description"
}
```

##### Response
```json
{
    "code": "CAT",
    "name": "Category",
    "description": "Description",
    "url": "/categories/CAT"
}
```

##### Status Codes
|Code|Name       |Type                                               |
|----|-----------|---------------------------------------------------|
|201 |CREATED    | When creation was successful                      |
|409 |CONFLICT   | When code already exists in repository            |
|400 |BAD_REQUEST| When category to be saved contains invalid fields |


#### Finding All

##### Request
| Request    |                           |
|------------|---------------------------|
| url        | localhost:8080/categories |
| method     | GET                       |


##### Response
```json
[
    {
        "code": "CAS",
        "name": "Casegory",
        "description": "Description",
        "url": "/categories/CAS"
    },
    {
        "code": "CAT",
        "name": "Category",
        "description": "Description",
        "url": "/categories/CAT"
    }
]
```

##### Status Codes
|Code|Name       |Type                                               |
|----|-----------|---------------------------------------------------|
|200 |OK         | When finding was successful                       |

#### Finding Single Category by Code

##### Request
| Request    |                                        |
|------------|----------------------------------------|
| url        | localhost:8080/categories/{code}       |
| method     | GET                                    |
| {code}     | path param for unique code of category |

##### Response
```json
{
  "code": "CAT",
  "name": "Category",
  "description": "Description",
  "url": "/categories/CAT"
}
```

##### Status Codes
|Code|Name       |Type                         |
|----|-----------|-----------------------------|
|200 |OK         | When category was found     |
|404 |NOT_FOUND  | When category was not found |
|400 |BAD_REQUEST| When code param is invalid  |

### Task
Task is distinguished by its category code together with its id. It consists of: 

|Field        |Type          |
|-------------|--------------|
|code         | String       |
|taskId       | int          |
|start        | LocalDateTime|
|end          | LocalDateTime|
|description  | String       |

#### Creation

##### Request
| Request    |                           |
|------------|---------------------------|
| url        | localhost:8080/tasks      |
| method     | POST                      |

```json
{
  "category" : "CAT",
  "start" : "2020-07-22 16:00",
  "end" : "2020-07-22 08:00",
  "description" : "Description"
}
```

##### Response
```json
{
    "id": "CAT-0000",
    "start": "2020-07-22 08:00",
    "end": "2020-07-22 16:00",
    "description": "Description",
    "url": "/tasks/CAT-0000"
}
```

##### Status Codes
|Code|Name       |Type                                               |
|----|-----------|---------------------------------------------------|
|201 |CREATED    | When creation was successful                      |
|404 |NOT_FOUND  | When provided category code does not exist        |
|400 |BAD_REQUEST| When task to be saved contains invalid fields     |


#### Finding All

##### Request
| Request    |                           |
|------------|---------------------------|
| url        | localhost:8080/tasks      |
| method     | GET                       |


##### Response
```json
[
    {
        "id": "CAT-0000",
        "start": "2020-07-22 08:00",
        "end": "2020-07-22 16:00",
        "description": "Description",
        "url": "/tasks/CAT-0000"
    },
    {
        "id": "CAT-0001",
        "start": "2020-07-22 08:00",
        "end": "2020-07-22 16:00",
        "description": "Description",
        "url": "/tasks/CAT-0001"
    }
]
```

##### Status Codes
|Code|Name       |Type                                               |
|----|-----------|---------------------------------------------------|
|200 |OK         | When finding was successful                       |

#### Finding Single Category by Code

##### Request
`taskViewId` must match format that currently consists of `{category code}-{task id}`, where task id is prefixed by
leading zeros to specification configurable in app properties.

| Request     |                                                                     |
|-------------|---------------------------------------------------------------------|
| url         | localhost:8080/tasks/{taskViewId}                                   |
| method      | GET                                                                 |
|{taskViewId} | path param for compound key consisting of category code and task id |

##### Response
```json
{
    "id": "CAT-0001",
    "start": "2020-07-22 08:00",
    "end": "2020-07-22 16:00",
    "description": "Description",
    "url": "/tasks/CAT-0001"
}
```

##### Status Codes
|Code|Name       |Type                                   |
|----|-----------|---------------------------------------|
|200 |OK         | When task was found                   |
|404 |NOT_FOUND  | When category was not found           |
|404 |NOT_FOUND  | When task was not found               |
|400 |BAD_REQUEST| When compound key in param is invalid |

## Manual Testing
For manual testing see `.postman` catalog and import collections to Postman Application.
