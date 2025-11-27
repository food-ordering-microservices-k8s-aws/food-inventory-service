FROM eclipse-temurin:21-jdk-jammy

WORKDIR /opt

COPY target/*.jar app.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]