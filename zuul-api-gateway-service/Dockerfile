FROM openjdk:8
ADD target/zuul-api-gateway-service-1.0.jar Zuul-Api-Gateway-Service.jar
EXPOSE 8765:8765
ENTRYPOINT ["java","-jar","Zuul-Api-Gateway-Service.jar"]