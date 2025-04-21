FROM openjdk:17

ADD target/question-service.jar question-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar","question-service.jar"]