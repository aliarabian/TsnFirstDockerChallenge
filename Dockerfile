# Stage 1: download dependencies and build jar package
FROM maven:3.8.7-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
RUN ["mvn", "dependency:go-offline"]
COPY src src
RUN ["mvn", "clean"]
RUN ["mvn", "package"]

# Stage 2: copies jar file from previous runtime environment image and configures running java app
FROM amazoncorretto:17.0.5
COPY --from=build /app/target/FirstDockerChallenge-0.0.1-SNAPSHOT.jar /opt/app/helloApp.jar
ENTRYPOINT ["java", "-jar", "/opt/app/helloApp.jar"]