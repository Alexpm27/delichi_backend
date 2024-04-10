FROM adoptopenjdk/openjdk17:alpine-jre
VOLUME /tmp
EXPOSE 8080
ADD target/delichi-api.jar delichi-api.jar
ENTRYPOINT ["java","-jar","/delichi-api.jar"]
