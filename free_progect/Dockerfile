# Build stage
FROM maven:3.6.3-jdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean test package

# Package stage
FROM openjdk:11.0.7-jdk-slim
COPY --from=build /home/app/target/*.jar app.jar
EXPOSE 8189
ENTRYPOINT ["java","-jar","app.jar"]
#FROM adoptopenjdk/openjdk11:alpine-jre
#ADD target/*.jar app.jar
#ENTRYPOINT ["java","-jar","app.jar"]