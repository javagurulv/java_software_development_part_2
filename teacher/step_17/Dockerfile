FROM eclipse-temurin:21
COPY insurance-calculator-1.0.2.jar insurance-calculator.jar
CMD ["java", "-Dspring.profiles.active=mysql-container", "-jar", "insurance-calculator.jar"]
EXPOSE 8080
