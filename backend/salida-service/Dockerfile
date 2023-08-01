FROM openjdk:19
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} salida-service.jar
ENTRYPOINT ["java","-jar","/salida-service.jar"]