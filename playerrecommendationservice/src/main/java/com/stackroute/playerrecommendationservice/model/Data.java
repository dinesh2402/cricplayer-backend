package com.stackroute.playerrecommendationservice.model;

import com.stackroute.playerrecommendationservice.model.bowling.Bowling;

public class Data {

	private Batting batting;
	private Bowling bowling;

	public Data(Batting batting, Bowling bowling) {
		super();
		this.batting = batting;
		this.bowling = bowling;
	}

	public Batting getBatting() {
		return batting;
	}

	public void setBatting(Batting batting) {
		this.batting = batting;
	}

	public Bowling getBowling() {
		return bowling;
	}

	public void setBowling(Bowling bowling) {
		this.bowling = bowling;
	}

	@Override
	public String toString() {
		return "Data [batting=" + batting + ", bowling=" + bowling + "]";
	}

}
