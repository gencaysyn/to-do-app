FROM maven:3.6.0-jdk-11-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:17-oracle
MAINTAINER gencaysyn
ARG JAR_FILE=/usr/src/app/target/todo-app-0.0.1-SNAPSHOT.jar
COPY --from=build /usr/src/app/target/todo-app-0.0.1-SNAPSHOT.jar /usr/app/todo-app-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/todo-app-0.0.1-SNAPSHOT.jar"]