# Use the official Maven image with JDK 17 for the build stage
FROM maven:3.8.4-openjdk-17 AS build
COPY src /build/src
COPY pom.xml /build
RUN mvn -f /build/pom.xml clean package

# Use the official OpenJDK 17 image for the runtime stage
FROM openjdk:17-slim
COPY --from=build /build/target/*.jar /usr/local/lib/my-application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/my-application.jar"]


