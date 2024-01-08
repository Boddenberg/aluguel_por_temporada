FROM openjdk:17-jdk-slim AS build

WORKDIR /usr/src/app

COPY . /usr/src/app

RUN ./gradlew build

FROM openjdk:17-jdk-slim

EXPOSE 8080

ARG JAR_FILE=Project-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY --from=build /usr/src/app/build/libs/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","Project-0.0.1-SNAPSHOT.jar"]
