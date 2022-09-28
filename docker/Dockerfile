FROM openjdk:8-jdk-alpine
MAINTAINER "Pramod Patil"
USER root
RUN mkdir -p /home/tutorials
WORKDIR /home/tutorials
COPY tutorials-0.0.1-SNAPSHOT.jar app-backend.jar
#RUN chown -R app:app .
#USER app
ENTRYPOINT ["java", "-jar", "app-backend.jar"]
