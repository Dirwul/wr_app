FROM eclipse-temurin:21.0.2_13-jdk-jammy AS builder

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/wr_database
ENV SPRING_DATASOURCE_USERNAME=dirwul
ENV SPRING_DATASOURCE_PASSWORD=some_pass

WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:21.0.2_13-jre-jammy AS final
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]