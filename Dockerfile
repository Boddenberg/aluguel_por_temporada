FROM openjdk:17-alpine

MAINTAINER Bruno, Filipe

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]