# Build Stage
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /home/app

# Copy only the POM file to leverage Docker cache for dependencies
COPY pom.xml .

# Download the project dependencies (cached if pom.xml is unchanged)
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src /home/app/src

# Build the application
RUN mvn package -DskipTests

# Final Stage
FROM amazoncorretto:17-alpine

# Copy the built JAR file from the build stage
COPY --from=build /home/app/target/*.jar /app.jar

# Expose the port on which the application is listening
EXPOSE 8070

# Specify the command to run the application
CMD ["java", "-jar", "/app.jar"]