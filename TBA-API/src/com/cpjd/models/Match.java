package com.cpjd.models;

import java.io.Serializable;

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
	
	public class Video implements Serializable {
		
		private static final long serialVersionUID = 7645909171187325331L;
		
		public String type;
		public String key;
	}

	@Override
	public int compareTo(Match o) {
		return Long.compare(match_number, o.match_number);
	}
}
