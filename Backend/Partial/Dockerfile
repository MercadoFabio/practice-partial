# Etapa de build
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar archivos de configuración de Maven primero para aprovechar cache
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN mvn dependency:go-offline -B

# Copiar código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Crear usuario no-root para seguridad
RUN addgroup -g 1001 -S spring && adduser -u 1001 -S spring -G spring

# Copiar el JAR de la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Cambiar al usuario no-root
USER spring:spring

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
