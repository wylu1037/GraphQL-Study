FROM 192.168.1.20:5000/baas/gradle:7-jdk8-jammy AS builder
WORKDIR /tmp
COPY . /tmp
RUN gradle 'bootJar'

FROM 192.168.1.20:5000/baas/jgjdk:base AS runner
WORKDIR /app
COPY --from=builder /tmp/baas-admin/build/libs/*.jar ./app.jar

CMD ["java", "-jar", "/app/app.jar"]