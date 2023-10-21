# GeoCachingXp

![Logo](https://infoportugal.pt/wp-content/uploads/2020/02/Geocaching_B-1024x535.jpg)

Welcome to my geocaching xp backend app, this is a replica of geocaching app but with a feature for levels, the game have levels between 1 and 5, and which user can only registry a cache if he have at least the cache level required.

## Architecture

![ArchitectureImg](https://github.com/gugafromMARS/GeoCachingXp/assets/116969206/674e3e2d-59eb-4d06-9787-6a17828e2180)


## Technology

Here are some technology's used on this project.

* Java version 17

## Services

Services used.

* Github

## Getting started

1 - Run all this commands for build your docker containers and get all connections for any db

 1.1 - Docker container for Ticket Hub db
```shell script
docker run -d -p 3307:3306 --name users-mcs-db -v $(pwd)/usersdata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=usersdb mysql:latest
```
 1.2 - Docker container for Tickets db
```shell script
docker run -d -p 3308:3306 --name registers-mcs-db -v $(pwd)/registersdata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=registersdb mysql:latest
```
 1.3 - Docker container for Events db
```shell script
docker run -d -p 3309:3306 --name caches-mcs-db -v $(pwd)/cachesdata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=cachesdb mysql:latest
```

2 - Now run your microservices

 2.1 - Run Service Registry first!

 2.2 - Run all other microservices

## App functionalitys

In this app you have several options :

* **Create**

  You can create an user, an user can create a register a cache if he had level for it, and a cache can be created too.

* **Read**
  
  Can get all users, user level, all cache registers from a user, all caches, cache by cache code.

* **Delete**

  Delete user and delete caches.

* **Update**

  Can update user email and update cache tip.

## Tests

I made some integration tests and service unit tests for all microservices, using :

* Mockito
* Junit5
  
 
## Authors

**gugafromMars**

[Github-gugafromMars](https://github.com/gugafromMARS)

Thanks to visiting and happy coding!
