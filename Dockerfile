FROM openjdk:17-jdk-slim

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests

EXPOSE 5500

ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "target/money-transfer-0.0.1-SNAPSHOT.jar"]