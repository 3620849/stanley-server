# Stanley Book Store application

## Install

1) To install aplication you need have mysql database

-please create database with name **stanley** and create user with name **root** password not required or change file application.properties properly to connect your database

2) Run application as common spring boot app 

3) Using browser open page **localhost:8080**

## Explanation of project:

I used here Spring Boot, Spring Data, Angular 8, Angular material

To prevent loseing data during simultaneous work i used **Optimistic Locking**

Front-end doesn't alow inserting wrong data, dispite that  server side also does validation

Angular source code already compiled and present at folder main/resources/public

**That mean thre is no need to compile/build frontend part**

To check front-end sourcecode you can visit https://github.com/3620849/stanley-client