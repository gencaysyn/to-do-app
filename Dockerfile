FROM openjdk:17-oracle
MAINTAINER gencaysyn
ARG JAR_FILE=target/todo-app-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]