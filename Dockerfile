FROM maven:3.8.5-openjdk-17 As build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package

FROM openjdk:17-jdk-slim

WORKDIR /app

ENV JAR=pismo_techcase-1.0.0.jar

COPY --from=build /app/target/*.jar $JAR

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "$JAR"]

EXPOSE 8080

ENTRYPOINT java -jar $JAR
