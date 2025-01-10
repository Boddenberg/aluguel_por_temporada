# Build stage
FROM amazoncorretto:17 AS build
WORKDIR /app

# Instalar dependências
RUN yum install -y bash unzip dos2unix

# Copiar arquivos necessários do projeto
COPY ./gradlew ./gradlew
COPY ./gradle /app/gradle
COPY ./build.gradle.kts ./settings.gradle.kts /app/
COPY ./src /app/src

# Converter para formato UNIX e garantir permissões
RUN dos2unix gradlew && chmod +x gradlew

# Construir aplicação Kotlin com Spring Boot
RUN ./gradlew build -x test

# Package stage
FROM amazoncorretto:17
WORKDIR /app

# Definir variáveis de ambiente
ENV JAR_NAME=app.jar

# Copiar JAR gerado
COPY --from=build /app/build/libs/*.jar $JAR_NAME

# Expor a porta usada pela aplicação
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
