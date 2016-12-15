package com.cpjd.models;

public class Team {

	public String name;
	public long team_number;
	public String website;
	public String locality;
	public String region;
	public String country_name;
	public String location;
	public String key;
	public String nickname;
	public long rookie_year;
	public String motto;
	
	/**
	 * Ranking information about the team within a certain event. These variables are only
	 * set if Settings.FIND_TEAM_RANKINGS is true
	 */
	
	public int rank;
	public double rankingScore;
	public double auto;
	public double scaleOrChallenge;
	public double goals;
	public double defense;
	public String record;
	public int played;
}
