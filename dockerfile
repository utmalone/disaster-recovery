FROM openjdk:8
EXPOSE 8000
ADD target/disasterrecovery.jar disasterrecovery.jar
ENTRYPOINT ["java", "-jar", "/disasterrecovery.jar"]