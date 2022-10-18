FROM 7-jdk8-jammy AS builder
WORKDIR /tmp
COPY . /tmp
RUN gradle 'bootJar'

FROM openjdk:8-jdk-alpine AS runner
WORKDIR /app
COPY --from=builder /tmp/baas-admin/build/libs/*.jar ./app.jar

CMD ["java", "-jar", "/app/app.jar"]