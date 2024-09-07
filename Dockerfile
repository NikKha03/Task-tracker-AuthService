FROM openjdk:21
WORKDIR /app
COPY target/authService-0.0.1-SNAPSHOT.jar authService.jar
ENTRYPOINT ["java", "-jar", "authService.jar"]