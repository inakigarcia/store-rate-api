FROM maven:3.6.3-openjdk-11 as builder
WORKDIR /home/store-rate-api/
COPY /src /home/store-rate-api/src
COPY pom.xml /home/store-rate-api/
RUN mvn clean package

FROM openjdk:11-jre-slim
RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/*
VOLUME /home/logs
COPY /src/main/resources/application.properties /home/application.properties
COPY --from=builder /home/store-rate-api/target/store-rate-api*.jar /home/store-rate-api.jar
EXPOSE 8080
CMD ["java", "-jar", "/home/store-rate-api.jar", "--spring.config.location=file:///home/application.properties"]

HEALTHCHECK --interval=30s --timeout=3s --retries=3 CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1