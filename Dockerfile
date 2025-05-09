# Step 1: Use Maven to build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY src ./src

# Package the application (skip tests if needed)
RUN mvn clean package -DskipTests

# Step 2: Use a lightweight Java image to run the application
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the new container
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/notification-0.0.1-SNAPSHOT.jar app.jar

# Expose the default port (adjust if different)
EXPOSE 8084

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
