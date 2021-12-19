# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.springcloud.ssadhuhanv2.api-gateway' is invalid and this project uses 'com.springcloud.ssadhuhanv2.apigateway' instead.

# Getting Started
* Ran into below error while committing changes from local repo to github. Below commands saved the day:
    * https://stackoverflow.com/questions/37770467/why-do-i-have-to-git-push-set-upstream-origin-branch
    * https://stackoverflow.com/questions/47403358/fatal-in-unpopulated-submodule

          git pull origin main --allow-unrelated-histories
          git push -u origin main


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.1/maven-plugin/reference/html/#build-image)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#production-ready)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#using-boot-devtools)
* [Eureka Discovery Client](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#service-discovery-eureka-clients)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)
* [Using Spring Cloud Gateway](https://github.com/spring-cloud-samples/spring-cloud-gateway-sample)

