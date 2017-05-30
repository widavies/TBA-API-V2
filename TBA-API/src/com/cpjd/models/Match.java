package com.cpjd.models;

import java.io.Serializable;

import com.cpjd.main.Constants;

public class Match implements Serializable, Comparable<Match> {
	
	private static final long serialVersionUID = -1274461093306977059L;
	
	public String key;
	public String comp_level;
	public long set_number;
	public long match_number;
	public String event_key;
	public String time_string;
	public long time;
	
	public String[] blueTeams;
	public long blueScore;
	public String[] redTeams;
	public long redScore;
	
	public String[] scorableItems;
	public String[] redValues;
	public String[] blueValues; //
	
	public Video[] videos;
	
	/**
	 * Checks if the specified team is contained within this match.
	 * @param teamNumber A frc team number. Example: 4859
	 * @return an integer, Constants.DOES_NOT_CONTAIN, Constants.CONTAINS_TEAM_BLUE, Constants.CONTAINS_TEAM_RED
	 */
	public int doesMatchContainTeam(int teamNumber) {
		for(String s : blueTeams) if(Integer.parseInt(s.replace("frc", "")) == teamNumber) return Constants.CONTAINS_TEAM_BLUE;
		for(String s : redTeams) if(Integer.parseInt(s.replace("frc", "")) == teamNumber) return Constants.CONTAINS_TEAM_RED;
		return Constants.DOES_NOT_CONTAIN;
	}
	
	/**
	 * Returns whether the specified team is on the winning alliance.
	 * Precondition: the match contains the specified team
	 * @return true if team is on winning alliance, false if team is on losing alliance, true if tie
	 */
	public boolean isOnWinningAlliance(int teamNumber) {
		int alliance = doesMatchContainTeam(teamNumber);
		if(alliance == Constants.DOES_NOT_CONTAIN) return false;
		if(redScore == blueScore) return true;
		else if(alliance == Constants.CONTAINS_TEAM_RED) return redScore > blueScore;
		else return blueScore > redScore;
	}
	
	public class Video implements Serializable {
		
		private static final long serialVersionUID = 7645909171187325331L;
		
		public String type;
		public String key;
	}

	/**
	 * Sorts matches by:
	 * -Quals
	 * -Quarters
	 * -Semis
	 * -Finals 
	 */
	
	@Override
	public int compareTo(Match o) {
		long localScore = match_number;
		if(comp_level.equals("qf")) localScore += 1000;
		else if(comp_level.equals("sf")) localScore += 10000;
		else if(comp_level.equals("f")) localScore += 100000;
		localScore += match_number;
		
		long compareScore = o.match_number;
		if(o.comp_level.equals("qf")) compareScore += 1000;
		else if(o.comp_level.equals("sf")) compareScore += 10000;
		else if(o.comp_level.equals("f")) compareScore += 100000;
		compareScore += o.match_number;
		
		return Long.compare(localScore, compareScore);
	}
}
