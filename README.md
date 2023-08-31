
**Requirements**

The project must be written in Java 8 or above.

The project must use Maven OR Gradle to build, test and run.

The project must have unit and integration tests.

The project must be runnable in that the service should be hosted in a container e.g. Tomcat, Jetty, Spring Boot etc.

You may use any frameworks or libraries for support e.g. Spring MVC, Apache CXF etc.

The project must be accessible from Github.



Optional Extensions

Deploy the solution to a chosen platform that we can access.

Consider supporting varying return content types such as XML based, that should be configurable using the requested media type.

Consider ways to improve overall performance e.g. caching results, concurrent algorithm

Consider supporting multiple algorithms that can be switched based on optional parameters

**Basic requirement**

Java 11 or higher

Spring boot 3.1.3 Apache Maven 3.8.2 Postman or any other REST API Testing tool

To get repository download

git clone https://github.com/snbiju/primeNumberCheck.git


cd primeNumberCheck/prime-service

To run the application : mvn spring-boot:run

To run unit test and integration test  : mvn test

**Addressed below Algorithms we can extend other as well**

TR - TRADITIONAL

SE - SIEVE OF ERATOSTENES 

IR - ITERATIVE 

Generate Prime Numbers with the given range (GET REQUEST) 
http://localhost:8088/primes/10(GET REQUEST)

Generate Prime Numbers with the given range with TR (GET REQUEST) 
http://localhost:8088/primes/10/TR (GET REQUEST)

Generate Prime Numbers with the given range with SE (GET REQUEST)
http://localhost:8088/primes/10/SE (GET REQUEST)

* This API will support both XML and JSON Format response
*  Support Multiple Algorithms
* Enabled Caffeine Cache

