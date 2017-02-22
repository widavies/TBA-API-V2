package com.cpjd.models;

import java.io.Serializable;
import java.util.Calendar;

public class Event implements Serializable, Comparable<Event> {

	private static final long serialVersionUID = 4829106493860206905L;
	
	public String key;
	public String name;
	public String short_name;
	public String event_code;
	public String event_type_string;
	public long event_type;
	public String event_district_string;
	public long event_district;
	public long year;
	public long week;
	public String location;
	public String venue_address;
	public String timezone;
	public String website;
	public String start_date;
	public String end_date;
	public boolean official;
	
	/** The following values are set depending on the configuration set up in the Settings class.**/
	public Webcast[] webcasts;
	public Alliance[] alliances;
	
	/**
	 * If Settings.FIND_TEAM_RANKINGS is enabled, Team[] teams will be sorted by rank, where teams[0] is the best team
	 * and teams[teams.length-1] is the worst team.
	 */
	public Team[] teams;
	public Match[] matches;
	public Award[] awards;
	
	public class Webcast implements Serializable {

		private static final long serialVersionUID = 7178723622969378349L;
		
		public String type;
		public String channel;
	}
	
	public class Alliance implements Serializable {

		private static final long serialVersionUID = -5010929758269698699L;
		
		public String[] picks;
		public String[] declines;
		public String backupIn;
		public String backupOut;
		public String name;
	}

	public long getStartDate(String date) {
		String[] tokens = date.split("-");
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(tokens[0]));
		c.set(Calendar.MONTH, Integer.parseInt(tokens[1]));
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tokens[2]));
		return c.getTimeInMillis();
	}
	
	@Override
	public int compareTo(Event o) {
		return Long.compare(getStartDate(start_date), getStartDate(o.start_date));
	}
}
