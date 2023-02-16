FROM openjdk:11
WORKDIR /app
ADD target/reading-is-good-0.0.1-SNAPSHOT.jar  reading-is-good:0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","reading-is-good-0.0.1-SNAPSHOT.jar"]

