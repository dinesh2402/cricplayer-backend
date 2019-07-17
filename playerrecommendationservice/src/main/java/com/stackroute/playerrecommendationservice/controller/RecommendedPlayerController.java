package com.stackroute.playerrecommendationservice.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.playerrecommendationservice.model.PlayerStats;
import com.stackroute.playerrecommendationservice.service.RecommendedPlayerService;

@CrossOrigin(origins = "*")
@RestController
public class RecommendedPlayerController {

	private RecommendedPlayerService recommendedPlayerService;

	@Autowired
	public RecommendedPlayerController(RecommendedPlayerService recommendedPlayerService) {
		this.recommendedPlayerService = recommendedPlayerService;
	}

	@GetMapping("/api/v1/recommendations/players")
	public ResponseEntity<?> getAllRecommendedPlayers() throws ServletException {
		List<PlayerStats> recommendedPlayersList = null;
		try {
			recommendedPlayersList = recommendedPlayerService.getAllRecommendedPlayers();
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(recommendedPlayersList, HttpStatus.OK);
	}

}
