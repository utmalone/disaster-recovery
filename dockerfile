FROM openjdk:8
ARG JAR_FILE
COPY ${JAR_FILE} disasterrecovery.jar
ENTRYPOINT ["java", "-jar", "/disasterrecovery.jar"]