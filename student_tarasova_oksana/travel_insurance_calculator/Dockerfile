FROM openjdk:18-jdk-alpine
COPY insurance-calc-1.0.2.jar insurance-calc.jar
CMD ["java", "-Dspring.profiles.active=mysql-container", "-jar", "insurance-calc.jar"]
EXPOSE 8080
