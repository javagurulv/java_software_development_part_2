FROM eclipse-temurin:21

# Install bash
# RUN apk add --no-cache bash

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x /wait-for-it.sh

COPY insurance-calculator-1.0.2.jar insurance-calculator.jar

CMD ["./wait-for-it.sh", "mysql-container:3306", "--", "java", "-Dspring.profiles.active=mysql-container", "-jar", "insurance-calculator.jar"]

EXPOSE 8080
