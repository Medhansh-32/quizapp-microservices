FROM openjdk:17

ADD target/service-registery.jar service-registery.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar","service-registery.jar"]