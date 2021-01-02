FROM openjdk:13-jdk-alpine

LABEL maintainer="Miguel Estevez <maletaveras@gmail.com>"

ENV NOMBRE_APP = 'Config Server'

EXPOSE 8888

COPY ./build/libs/config-server-0.0.1-SNAPSHOT.jar mi_app.jar

ENTRYPOINT java -jar mi_app.jar
