FROM openjdk:17-oracle
RUN mkdir /app
WORKDIR /app

COPY bianxi-api-*.jar /app/bianxi-api.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bianxi-api.jar"]