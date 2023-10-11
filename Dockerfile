# Use an official OpenJDK base image with Java 17
FROM openjdk:17-alpine

# Setting the working directory in the container
WORKDIR /app

# Copying the JAR file into the container at /app
COPY target/ontology-sync-0.0.1-SNAPSHOT.jar /app/ontology-sync.jar

# Exposing port 8080 for the Spring Boot application
EXPOSE 8080

# Specifying the command to run when the container starts
CMD ["java", "-jar", "ontology-sync.jar"]
