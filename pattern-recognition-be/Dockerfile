FROM eclipse-temurin:17-jre-alpine

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/pattern-recognition-be.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/usr/pattern-recognition-be.jar"]
