package com.stackroute.favouriteservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.exception.FavouritePlayerExistException;
import com.stackroute.favouriteservice.model.PlayerStats;
import com.stackroute.favouriteservice.repository.FavouritePlayerRepository;


@Service
public class FavouriteServiceImpl implements FavouriteService {

	private FavouritePlayerRepository favPlayerRepository;

	@Autowired
	public FavouriteServiceImpl(FavouritePlayerRepository favPlayerRepository) {
		this.favPlayerRepository = favPlayerRepository;
	}
	
	@Override
	public PlayerStats persistFavouritePlayer(PlayerStats stats) {
		return this.favPlayerRepository.insert(stats);
	}

	@Override
	public List<PlayerStats> findFavouritePlayerByUserName(String userName) {
		return this.favPlayerRepository.findByUserName(userName);
	}


	@Override
	public PlayerStats findFavouritePlayerByPidAndUserName(String pid, String userName) throws FavouritePlayerExistException {
		PlayerStats playerExist = this.favPlayerRepository.findByPidAndUserName(pid, userName);

		if (playerExist != null)
			throw new FavouritePlayerExistException("Player already exists in favourites");
		return playerExist;
	}


}
