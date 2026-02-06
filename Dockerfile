FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app

ARG JAR_FILE=target/technical_work-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]