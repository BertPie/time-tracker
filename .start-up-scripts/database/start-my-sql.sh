#!/bin/bash

docker run -d -p 3306:3306 --name mysql-tasker-db \
	-e MYSQL_ROOT_PASSWORD=pass1234 \
	-e MYSQL_DATABASE=tasker \
	mysql
