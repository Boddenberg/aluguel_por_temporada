FROM openjdk:17-alpine

LABEL org.opencontainers.image.authors="Bruno && Filipe""

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]