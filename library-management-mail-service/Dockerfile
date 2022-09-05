FROM openjdk:18
WORKDIR /app
#
COPY . .
#RUN chmod +x mvnw && ./mvnw clean install -U
#ENTRYPOINT ["./mvnw", "spring-boot:run"]
ADD target/library-management-mail-service-0.0.1-SNAPSHOT.jar library-management-mail-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","library-management-mail-service-0.0.1-SNAPSHOT.jar","--Dspring.profiles.active=docker"]