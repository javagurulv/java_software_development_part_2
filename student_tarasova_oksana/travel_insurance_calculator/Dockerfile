FROM openjdk:18-jdk-alpine
COPY build/libs/*.jar insurance-calc.jar
ENTRYPOINT ["java"]
CMD ["-jar", "insurance-calc.jar"]
EXPOSE 8080
