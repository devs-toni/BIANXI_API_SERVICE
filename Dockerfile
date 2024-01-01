FROM openjdk:17-oracle

RUN mkdir /app
WORKDIR /app

COPY *.jar /app/bianxi-api.jar
COPY /db/migrations /db/migrations
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "bianxi-api.jar"]