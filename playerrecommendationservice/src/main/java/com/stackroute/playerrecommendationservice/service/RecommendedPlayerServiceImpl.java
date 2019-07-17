package com.stackroute.playerrecommendationservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.playerrecommendationservice.model.PlayerStats;
import com.stackroute.playerrecommendationservice.repository.RecommendedPlayerRepository;

@Service
public class RecommendedPlayerServiceImpl implements RecommendedPlayerService{
	
	private RecommendedPlayerRepository recommendedPlayerRepository;

	@Autowired
	public RecommendedPlayerServiceImpl(RecommendedPlayerRepository recommendedPlayerRepository) {
		this.recommendedPlayerRepository = recommendedPlayerRepository;
	}
	
	@Override
	public PlayerStats saveRecommendedPlayer(PlayerStats recommendedPlayer) {
		
		PlayerStats addedPlayer = null;
		try {
			addedPlayer = this.recommendedPlayerRepository.findByPid(recommendedPlayer.getPid());
			if (addedPlayer == null) {
				addedPlayer = recommendedPlayerRepository.insert(recommendedPlayer);
			} else {
				addedPlayer = recommendedPlayer;
			}

		} catch (Exception e) {
		}
		
		return addedPlayer;
	}

	@Override
	public List<PlayerStats> getAllRecommendedPlayers() {
		return this.recommendedPlayerRepository.findAll();
	}

	@Override
	public PlayerStats findByPid(String pid) {
		return this.recommendedPlayerRepository.findByPid(pid);
	}

	@Override
	public PlayerStats updateRecommendedPlayer(PlayerStats stats) {
		return this.recommendedPlayerRepository.save(stats);
	}

	@Override
	public void deleteRecommendedPlayer(PlayerStats stats) {
	    this.recommendedPlayerRepository.delete(stats);
	}
	

}
