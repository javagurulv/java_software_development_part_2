FROM eclipse-temurin:18-jdk-alpine
# Install bash
RUN apk add --no-cache bash

COPY build/libs/*.jar app.jar

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x /wait-for-it.sh

CMD ["./wait-for-it.sh", "mysql-container:3306", "--", "java", "-jar", "-Dspring.profiles.active=case1", "/app.jar"]

EXPOSE 8080