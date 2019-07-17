package com.stackroute.favouriteservice;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
import com.stackroute.favouriteservice.controller.FavouritePlayerController;
import com.stackroute.favouriteservice.model.PlayerStats;
import com.stackroute.favouriteservice.service.FavouriteService;


@RunWith(SpringRunner.class)
@WebMvcTest
public class FavouriteServiceControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PlayerStats player;

	@MockBean
	private FavouriteService favouriteService;

	@InjectMocks
	private FavouritePlayerController favouriteController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(favouriteController).build();

		player = new PlayerStats();
		player.setName("Dhoni");
		player.setCountry("India");
		player.setBattingStyle("Right Handed Batsmen");
		
	}

	@Test
	public void addToFavouriteTestSuccess() throws Exception {
		when(favouriteService.persistFavouritePlayer(player)).thenReturn(player);
		mockMvc.perform(post("/api/v1/favourites/save/Sachin").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(player))).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getFavouritesTestFailure() throws Exception {
		
		List<PlayerStats> playersList = new ArrayList<PlayerStats>();
		
		when(favouriteService.findFavouritePlayerByUserName(Mockito.anyString())).thenReturn(playersList);
		mockMvc.perform(get("/api/v1/favourites/favplayers/test").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void getFavouritesTestSuccess() throws Exception {
		
		List<PlayerStats> playersList = new ArrayList<PlayerStats>();
		playersList.add(player);
		
		when(favouriteService.findFavouritePlayerByUserName(Mockito.anyString())).thenReturn(playersList);
		mockMvc.perform(get("/api/v1/favourites/favplayers/Dhoni").contentType(MediaType.APPLICATION_JSON))
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
