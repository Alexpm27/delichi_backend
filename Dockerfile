FROM eclipse-temurin:17-jdk-alpine
RUN apk add curl
VOLUME /tmp
EXPOSE 8080
ADD target/delichi-api.jar delichi-api.jar
ENTRYPOINT ["java","-jar","/delichi-api.jar"]