
* Run from command line


        java -jar .\target\naming-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer-a
        java -jar .\target\naming-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer-b
        java -jar .\target\naming-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer-c


* DNS Entries required for "naming-server" if all 3 instances are running on the same host
  in ```C:\Windows\System32\drivers\etc\hosts.ics``` file


        127.0.0.1 eureka-server-peer-a
        127.0.0.1 eureka-server-peer-b
        127.0.0.1 eureka-server-peer-c

# Read Me First

* The original package name 'com.springcloud.ssadhuhanv2.naming-server' is invalid and this project uses 'com.springcloud.ssadhuhanv2.namingserver' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.1/maven-plugin/reference/html/#build-image)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#production-ready)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#using-boot-devtools)
* [Eureka Server](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#spring-cloud-eureka-server)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)

