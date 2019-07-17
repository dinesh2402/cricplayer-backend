package com.stackroute.favouriteservice.controller;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.ConnectionFactory;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.stackroute.favouriteservice.exception.FavouritePlayerExistException;
import com.stackroute.favouriteservice.model.PlayerStats;
import com.stackroute.favouriteservice.service.FavouriteService;


@CrossOrigin(origins = "*")
@RestController
public class FavouritePlayerController {
	
	private FavouriteService favouriteService;
	public String QUEUENAME = "playerQueue";
	public String host = "localhost";

	@Autowired
	public FavouritePlayerController(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}

	@PostMapping("/api/v1/favourites/save/{userName}")
	public ResponseEntity<?> saveFavouritePlayer(@PathVariable("userName") String userName,  @RequestBody PlayerStats favouritePlayer) throws ServletException {
		favouritePlayer.setUserName(userName);
		PlayerStats addedPlayer = null;
		
		try {
		    addedPlayer = favouriteService.findFavouritePlayerByPidAndUserName(favouritePlayer.getPid(), userName);
			if(addedPlayer == null) {
				addedPlayer = favouriteService.persistFavouritePlayer(favouritePlayer);
				sendToRabbitMQChaneel(favouritePlayer);
			} 		
		} catch (FavouritePlayerExistException e) { 
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(addedPlayer, HttpStatus.OK);
	}
	

	private void sendToRabbitMQChaneel(PlayerStats favouritePlayer) {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        Gson gson = new Gson();
        String jsonInString = gson.toJson(favouritePlayer);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUENAME, false, false, false, null);
            channel.basicPublish("", QUEUENAME, null, jsonInString.getBytes() );
        } catch (IOException | TimeoutException e) {
        	e.printStackTrace();
		}
	}
	
	@GetMapping("/api/v1/favourites/favplayers/{userName}")
	public ResponseEntity<?> getFavouritePlayersByEmail(@PathVariable("userName") String userName) throws ServletException {
	
		List<PlayerStats> playersList = null;
		try {
			playersList = favouriteService.findFavouritePlayerByUserName(userName);
		} catch (Exception e) { 
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(playersList, HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/favourites/isFavourite/{pid}/{userName}")
	public ResponseEntity<?> isFavourite(@PathVariable("pid") String pid, @PathVariable("userName") String userName) throws ServletException {
	
		PlayerStats addedPlayer = null;
		boolean isFavourite = false;
		try {
		    addedPlayer = favouriteService.findFavouritePlayerByPidAndUserName(pid, userName);
		    if(addedPlayer == null) {
		    	isFavourite = false;
		    }
		} catch (FavouritePlayerExistException e) { 
			isFavourite = true;
			return new ResponseEntity<>(isFavourite, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(isFavourite, HttpStatus.OK);
	}

}
