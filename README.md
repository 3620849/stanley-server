# Stanley Book Store application

## Install

1) To install aplication you need have mysql database

-please create database with name **stanley** and create user with name **root** 
if you need to specify password uncomment #spring.datasource.password=
at application.properties field and change it properly to connect your database

2) Run application as common spring boot app 

3) Using browser open page **localhost:8080**

## Explanation of project:

I used here Spring Boot, Spring Data, Angular 8, Angular material

Front-end doesn't alow inserting wrong data, dispite that  server side also does validation

Angular source code already compiled and present at folder main/resources/public

**That mean thre is no need to compile/build frontend part**

To check front-end sourcecode you can visit https://github.com/3620849/stanley-client

To prevent losing data during simultaneous work i used **Optimistic Locking**

For changing qty of book responsible method **incrementDecrementStoredBook** which reside at StorageRepository.class
This method called asynchronously inside BuyingExecutor.class if another user will change StoredBook simultaneously incrementDecrementStoredBook
 will throw exception  ObjectOptimisticLockingFailureException and operation will retry again. There is 5 attempts  to execute order. After status of order will be REJECTED.