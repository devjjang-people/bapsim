# 1️⃣ Gradle 빌드용 JDK 이미지 사용
FROM gradle:8.8-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon

# 2️⃣ 실제 실행용 JRE 이미지 (경량)
FROM eclipse-temurin:17-jre
WORKDIR /app

# JAR 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 3️⃣ 애플리케이션 포트 설정
EXPOSE 8080

# 4️⃣ 실행 명령
ENTRYPOINT ["java", "-jar", "app.jar"]
