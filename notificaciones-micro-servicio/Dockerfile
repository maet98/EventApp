FROM openjdk:13-jdk

LABEL maintainer="Miguel Estevez <maletaveras@gmail.com>"

ENV NOMBRE_APP = 'microservicio notificaciones'

COPY ./build/libs/notificaciones-micro-servicio-0.0.1-SNAPSHOT.jar mi_app.jar

ENTRYPOINT java -jar mi_app.jar
