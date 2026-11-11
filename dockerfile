FROM eclipse-temurin:21-jre

COPY target/PodDiscovery-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]