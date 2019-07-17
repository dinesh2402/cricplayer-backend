package com.stackroute.playerrecommendationservice.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recommendedPlayers")
public class PlayerStats {

	@Id
	private ObjectId _id;
	private String pid;
	private Data data;
	private String userName;
	private String creditsLeft;
	private String country;
	private String profile;
	private String imageURL;
	private String battingStyle;
	private String bowlingStyle;
	private String majorTeams;
	private String currentAge;
	private String born;
	private String fullName;
	private String name;
	private String playingRole;

	public PlayerStats(String userName, Data data, String creditsLeft, String pid, String country,
			String profile, String imageURL, String battingStyle, String bowlingStyle, String majorTeams,
			String currentAge, String born, String fullName, String name, String playingRole) {
		super();
		this.userName = userName;
		this.data = data;
		this.creditsLeft = creditsLeft;
		this.pid = pid;
		this.country = country;
		this.profile = profile;
		this.imageURL = imageURL;
		this.battingStyle = battingStyle;
		this.bowlingStyle = bowlingStyle;
		this.majorTeams = majorTeams;
		this.currentAge = currentAge;
		this.born = born;
		this.fullName = fullName;
		this.name = name;
		this.playingRole = playingRole;
	}
	
	public PlayerStats() {
		
	}
	
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getCreditsLeft() {
		return creditsLeft;
	}

	public void setCreditsLeft(String creditsLeft) {
		this.creditsLeft = creditsLeft;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getBattingStyle() {
		return battingStyle;
	}

	public void setBattingStyle(String battingStyle) {
		this.battingStyle = battingStyle;
	}

	public String getBowlingStyle() {
		return bowlingStyle;
	}

	public void setBowlingStyle(String bowlingStyle) {
		this.bowlingStyle = bowlingStyle;
	}

	public String getMajorTeams() {
		return majorTeams;
	}

	public void setMajorTeams(String majorTeams) {
		this.majorTeams = majorTeams;
	}

	public String getCurrentAge() {
		return currentAge;
	}

	public void setCurrentAge(String currentAge) {
		this.currentAge = currentAge;
	}

	public String getBorn() {
		return born;
	}

	public void setBorn(String born) {
		this.born = born;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlayingRole() {
		return playingRole;
	}

	public void setPlayingRole(String playingRole) {
		this.playingRole = playingRole;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
