FROM amazoncorretto:11-alpine
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} user-service.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app/user-service.jar"]