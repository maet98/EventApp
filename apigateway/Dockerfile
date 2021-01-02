FROM openjdk:13-jdk-alpine

LABEL maintainer="Miguel Estevez <maletaveras@gmail.com>"

ENV NOMBRE_APP = 'Api gateway'

EXPOSE 8989

COPY ./build/libs/api-gateway-0.0.1-SNAPSHOT.jar mi_app.jar

ENTRYPOINT java -jar mi_app.jar
