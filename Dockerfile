FROM openjdk:17
COPY target/pantry-app.war app.war
ENTRYPOINT ["java", "-jar", "/app.war"]