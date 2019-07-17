package com.stackroute.playerrecommendationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.playerrecommendationservice.model.PlayerStats;

public interface RecommendedPlayerRepository extends MongoRepository<PlayerStats, String> {
	
	PlayerStats findByPid(String pid);

}
