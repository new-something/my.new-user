FROM amazoncorretto:11-alpine
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app-service.jar"]