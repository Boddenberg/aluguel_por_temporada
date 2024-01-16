FROM openjdk:17-jdk-slim AS build

WORKDIR /usr/src/app

COPY . /usr/src/app

RUN ./gradlew build

FROM openjdk:17-jdk-slim

EXPOSE 8080
ARG REGION_ARG=sa-east-1
ARG ACCESS_ARG
ARG SECRET_ARG
ENV AWS_REGION=$REGION_ARG
ENV AWS_ACCESS_KEY=$ACCESS_ARG
ENV AWS_SECRET_KEY=$SECRET_ARG

ARG JAR_FILE=Project-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY --from=build /usr/src/app/build/libs/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","Project-0.0.1-SNAPSHOT.jar"]
