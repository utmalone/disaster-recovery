FROM openjdk:8
EXPOSE 8080
ARG JAR_FILE
COPY ${JAR_FILE} disasterrecovery.jar
ENTRYPOINT ["java", "-jar", "/disasterrecovery.jar"]