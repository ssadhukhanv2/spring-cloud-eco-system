
### Reference Documentation
For further reference, please consider the following sections:
* Sleuth : https://spring.io/projects/spring-cloud-sleuth
* Run Spring Boot in specific port: https://www.baeldung.com/spring-boot-change-port


    java -jar .\target\date-utils-0.0.1-SNAPSHOT.jar --server.port=9002
    java -jar .\target\date-utils-0.0.1-SNAPSHOT.jar -Dserver.port=9002 

* Ran into below error while committing changes from local repo to github. Below commands saved the day:
    * https://stackoverflow.com/questions/37770467/why-do-i-have-to-git-push-set-upstream-origin-branch
    * https://stackoverflow.com/questions/47403358/fatal-in-unpopulated-submodule

          git pull origin main --allow-unrelated-histories
          git push -u origin main

* Build the docker image:


    mvn spring-boot:build-image -DskipTests

* Remove the docker image:


    docker rmi ssadhukhanv2/human-records

* Run docker image as container:


    docker run -d -p 8001:8001 ssadhukhanv2/human-records:0.0.1-SNAPSHOT


* Stop docker image(infallible_euler is the container name which is supplied by docker)


    docker stop infallible_euler


* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

