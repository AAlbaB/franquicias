FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/franquicias-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9191
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh