FROM gradle:jdk17 as build_stage
WORKDIR /app
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
COPY ./src /app/src

RUN --mount=type=cache,mode=0777,target=/root/.gradle-cache gradle assemble -Pversion="$(git describe --tags || echo 'development-builds')"

FROM eclipse-temurin:19-jre-jammy
WORKDIR /deployment

# copy the app
COPY --from=build_stage /app/build/libs/*.jar app.jar

# run the app on startup
CMD ["java","-jar","app.jar"]
