FROM openjdk:18-jdk-alpine

# Install bash
RUN apk add --no-cache bash

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x /wait-for-it.sh

COPY insurance-calculat-1.0.2.jar insurance-calculat.jar

CMD ["./wait-for-it.sh", "mysql-container:3306", "--", "java", "-Dspring.profiles.active=mysql-container", "-jar", "insurance-calculat.jar"]

EXPOSE 8080
