FROM gradle:jdk21-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon

FROM eclipse-temurin:21-jdk-alpine
RUN adduser -D -u 1001 -s /bin/sh codebase \
  && mkdir /app \
  && chown codebase: /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/codebase.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/codebase.jar"]