package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.exception.FavouritePlayerExistException;
import com.stackroute.favouriteservice.model.PlayerStats;

public interface FavouriteService  {
	
	PlayerStats persistFavouritePlayer(PlayerStats stats);

    List<PlayerStats> findFavouritePlayerByUserName(String userName);
    
    PlayerStats findFavouritePlayerByPidAndUserName(String pid, String userName) throws FavouritePlayerExistException;
    
}
