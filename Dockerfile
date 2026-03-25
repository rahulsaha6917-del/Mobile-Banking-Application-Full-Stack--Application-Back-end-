# Use lightweight Java runtime
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy JAR file
COPY target/online-banking-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 9093

# Run application
ENTRYPOINT ["java","-jar","app.jar"]