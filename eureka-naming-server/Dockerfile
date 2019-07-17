FROM openjdk:8
ADD target/eureka-naming-server-1.0.jar Eureka-Naming-Server.jar
EXPOSE 8761:8761
ENTRYPOINT ["java","-jar","Eureka-Naming-Server.jar"]