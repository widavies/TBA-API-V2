package com.cpjd.models;

public class Award {
	
	public String name;
	public Long award_type;
	public String event_key;
	public Recipient[] recipient_list;
	public long year;
	
	public class Recipient {
		public long team_number;
		public String awardee;
	}
}
