package com.stackroute.playerrecommendationservice;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.playerrecommendationservice.controller.RecommendedPlayerController;
import com.stackroute.playerrecommendationservice.model.PlayerStats;
import com.stackroute.playerrecommendationservice.service.RecommendedPlayerService;


@RunWith(SpringRunner.class)
@WebMvcTest
public class PlayerRecommendationControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PlayerStats player;

	@MockBean
	private RecommendedPlayerService recommendationService;

	@InjectMocks
	private RecommendedPlayerController recommendationController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(recommendationController).build();

		player = new PlayerStats();
		player.setName("Dhoni");
		player.setCountry("India");
		player.setBattingStyle("Right Handed Batsmen");
		
	}

	@Test
	public void getRecommendedPlayersTestSuccess() throws Exception {
		
		List<PlayerStats> playersList = new ArrayList<PlayerStats>();
		
		when(recommendationService.getAllRecommendedPlayers()).thenReturn(playersList);
		mockMvc.perform(get("/api/v1/recommendations/players").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
