FROM openjdk:22-slim
WORKDIR /app
COPY build/libs/CinemaReservation-v1.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
