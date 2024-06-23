FROM maven AS build
WORKDIR /build
COPY . .
RUN mvn clean package

FROM openjdk:21
WORKDIR /app
COPY --from=build /build/target/Obligatorio-BD2.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]