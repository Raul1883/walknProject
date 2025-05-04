FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Кешируем зависимости Gradle (в том числе wrapper)
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
RUN ./gradlew --version

# Копируем оставшийся проект
COPY . .

# Скачиваем зависимости (если они не изменились, слой будет кеширован)
RUN ./gradlew dependencies --no-daemon

# Собираем jar
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]