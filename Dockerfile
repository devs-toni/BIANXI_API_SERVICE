FROM openjdk:17-oracle

RUN mkdir /app
WORKDIR /app

COPY *.jar /app/bianxi-api.jar

ENV SERVER_PORT 8080
ENV DB_PORT 3307
ENV DB_NAME bianxi
ENV DB_HOST localhost
ENV DB_USER bianxi
ENV DB_PASSWORD bianxi

EXPOSE 8080
EXPOSE 3307

ENTRYPOINT ["java", "-jar", "bianxi-api.jar"]