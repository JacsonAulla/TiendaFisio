# Etapa 1: Build con Maven
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app

# Copiar solo el pom.xml primero para aprovechar el caché de dependencias de Docker
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiar el .jar compilado desde la Etapa 1
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando para arrancar Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
