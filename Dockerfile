FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/jph-app-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /jph-app-api.jar
ENTRYPOINT ["java","-jar", "-Duser.timezone=Asia/Seoul", "/jph-app-api.jar"]