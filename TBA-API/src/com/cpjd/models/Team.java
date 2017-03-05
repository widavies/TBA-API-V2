package com.cpjd.models;

import java.io.Serializable;

public class Team implements Serializable {

	private static final long serialVersionUID = -4327097327503259963L;
	
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
	 * 
	 * These values are ONLY filled in when the teams list is pulled from
	 * an event!
	 */
	
	public long rank;
	public double rankingScore;
	public double auto;
	public double scaleOrChallenge;
	public double goals;
	public double defense;
	public String record;
	public String played;
}
