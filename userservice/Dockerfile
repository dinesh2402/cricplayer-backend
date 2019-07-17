FROM openjdk:8
ADD target/userservice-1.0.jar UserService.jar
EXPOSE 8500:8500
ENTRYPOINT ["java","-jar","UserService.jar"]