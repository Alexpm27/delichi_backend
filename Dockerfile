FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/delichi-api.jar delichi-api.jar
ENTRYPOINT ["java","-jar","/delichi-api.jar"]