FROM openjdk:19
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} movimientos-service.jar
ENTRYPOINT ["java","-jar","/movimientos-service.jar"]