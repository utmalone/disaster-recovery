FROM openjdk:8
EXPOSE 8089
ADD target/disasterrecovery.jar disasterrecovery.jar
ENTRYPOINT ["java", "-jar", "/disasterrecovery.jar"]