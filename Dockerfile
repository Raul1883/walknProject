# Используем официальный JDK 21
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Копируем gradle wrapper и зависимости
COPY . .

# Собираем jar
RUN ./gradlew bootJar --no-daemon

# Новый слой — только runtime
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
