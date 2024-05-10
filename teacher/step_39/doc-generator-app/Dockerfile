FROM eclipse-temurin:21

# Install bash
# # RUN apk add --no-cache bash
# RUN apk update && apk add bash

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x /wait-for-it.sh

COPY doc-generator-1.0.2.jar doc-generator.jar

CMD ["./wait-for-it.sh", "message-broker:5672", "--", "java", "-Dspring.profiles.active=rabbitmq-container", "-jar", "doc-generator.jar"]

EXPOSE 8080
