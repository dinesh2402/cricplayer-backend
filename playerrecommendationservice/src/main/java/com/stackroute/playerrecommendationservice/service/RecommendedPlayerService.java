package com.stackroute.playerrecommendationservice.service;

import java.util.List;

import com.stackroute.playerrecommendationservice.model.PlayerStats;

public interface RecommendedPlayerService {
	
	PlayerStats saveRecommendedPlayer(PlayerStats stats);
	
	PlayerStats updateRecommendedPlayer(PlayerStats stats);
	
	PlayerStats findByPid(String pid);
	
	List<PlayerStats> getAllRecommendedPlayers(); 
	
    void deleteRecommendedPlayer(PlayerStats stats);

}
