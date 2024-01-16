# Build stage
FROM gradle:latest AS build
WORKDIR /usr/app/
COPY . .
RUN gradle build

# Package stage
FROM openjdk:latest
ENV JAR_NAME=Project-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=build $APP_HOME .
EXPOSE 8080
ENTRYPOINT exec java -jar $APP_HOME/build/libs/$JAR_NAME
