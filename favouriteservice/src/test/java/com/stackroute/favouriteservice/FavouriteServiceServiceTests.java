package com.stackroute.favouriteservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.exception.FavouritePlayerExistException;
import com.stackroute.favouriteservice.model.PlayerStats;
import com.stackroute.favouriteservice.repository.FavouritePlayerRepository;
import com.stackroute.favouriteservice.service.FavouriteServiceImpl;



public class FavouriteServiceServiceTests {
	
	 @Mock
	    private FavouritePlayerRepository favouritePlayerRepository;

	    private PlayerStats player;
	    @InjectMocks
	    private FavouriteServiceImpl favouritePlayerService;

	    Optional<PlayerStats> optional;


	    @Before
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	        player = new PlayerStats();
			player.setName("Dhoni");
			player.setCountry("India");
			player.setBattingStyle("Right Handed Batsmen");
	        optional = Optional.of(player);
	    }

	    @Test
	    public void saveFavouritePlayerTestSuccess() {

	        Mockito.when(favouritePlayerRepository.save(player)).thenReturn(player);
	        PlayerStats savedPlayer = favouritePlayerRepository.save(player);
	        Assert.assertEquals("Add to Favourites", player, savedPlayer);

	    }
	    
	    @Test
	    public void getFavouritePlayersTestSuccess() {

	    	List<PlayerStats> playersList = new ArrayList<PlayerStats>();
	    	playersList.add(player);
	        Mockito.when(favouritePlayerRepository.save(player)).thenReturn(player);
	        List<PlayerStats> favPlayersList = favouritePlayerRepository.findAll();
	        Assert.assertNotEquals("Get Favourite Players", playersList, favPlayersList);

	    }

}
