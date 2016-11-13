package com.cpjd.models;

public class Event {
	public String key;
	public String name;
	public String short_name;
	public String event_code;
	public String event_type_string;
	public String event_type;
	public String event_district_string;
	public long event_district;
	public long year;
	public long week;
	public String location;
	public String venue_address;
	public String timezone;
	public String website;
	public boolean official;
	
	public Alliance[] alliances;
	public Team[] teams;
	public Match[] matches;
	public Award[] awards;
	
	public class Alliance {
		public String[] picks;
		public String[] declines;
	}
}
