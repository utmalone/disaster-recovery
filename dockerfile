FROM openjdk:8
EXPOSE 8085
ADD target/disasterrecovery.jar disasterrecovery.jar
ENTRYPOINT ["java", "-jar", "/disasterrecovery.jar"]