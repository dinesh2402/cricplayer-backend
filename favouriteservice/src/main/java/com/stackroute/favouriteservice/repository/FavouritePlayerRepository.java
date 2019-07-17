package com.stackroute.favouriteservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.favouriteservice.model.PlayerStats;

public interface FavouritePlayerRepository extends MongoRepository<PlayerStats, String> {
	
	List<PlayerStats> findByUserName(String userName);
	
	PlayerStats findByPid(String pid);
	
	PlayerStats findByPidAndUserName(String pid, String userName);

}
