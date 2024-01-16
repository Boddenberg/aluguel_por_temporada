# Build stage
FROM gradle:latest AS build
WORKDIR /usr/app/
COPY . .
RUN gradle build

# Package stage
FROM openjdk:latest
ENV JAR_NAME=Project-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/

ARG REGION_ARG=sa-east-1
ARG ACCESS_ARG
ARG SECRET_ARG
ENV AWS_REGION=$REGION_ARG
ENV AWS_ACCESS_KEY=$ACCESS_ARG
ENV AWS_SECRET_KEY=$SECRET_ARG

WORKDIR $APP_HOME
COPY --from=build $APP_HOME .
EXPOSE 8080
ENTRYPOINT exec java -jar $APP_HOME/build/libs/$JAR_NAME
