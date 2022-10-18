FROM gradle:7.5-jdk17-jammy AS builder
WORKDIR /tmp
COPY . /tmp
RUN gradle 'bootJar'
RUN cd /tmp
RUN ls -lrt

FROM openjdk:17-jdk-alpine AS runner
WORKDIR /app
COPY --from=builder /tmp/GraphQL-Study/build/libs/*.jar ./app.jar

CMD ["java", "-jar", "/app/app.jar"]