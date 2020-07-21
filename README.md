# Time Tracker
Application for tracking time spent on tasks.

## Table of Contents
0. [Table of Contents](#table-of-contents)
0. [Aim of the Project](#aim-of-the-project)
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

## Manual Testing
For manual testing see `.postman` catalog and import collections to Postman Application.
