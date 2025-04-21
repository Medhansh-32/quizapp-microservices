FROM openjdk:17

ADD target/api-gateway.jar api-gateway.jar

EXPOSE 8765

ENTRYPOINT ["java", "-jar","api-gateway.jar"]