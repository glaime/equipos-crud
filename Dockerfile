FROM openjdk:17-jdk-alpine
WORKDIR /app/equipos-crud
COPY ./pom.xml .
COPY ./.mvn ./.mvn
COPY ./mvnw .
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./src ./src
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "./target/dux-0.0.1-SNAPSHOT.jar"]