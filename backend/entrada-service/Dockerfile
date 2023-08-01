FROM openjdk:19
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} entrada-service.jar
ENTRYPOINT ["java","-jar","/entrada-service.jar"]