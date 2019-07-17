FROM openjdk:8
ADD target/playerrecommendationservice-1.0.jar RecommendationService.jar
EXPOSE 8502:8502
ENTRYPOINT ["java","-jar","RecommendationService.jar"]