FROM public.ecr.aws/amazoncorretto/amazoncorretto:17-alpine
RUN apk add curl
VOLUME /tmp
EXPOSE 8080
ADD target/delichi-api.jar delichi-api.jar
ENTRYPOINT ["java","-jar","/delichi-api.jar"]
