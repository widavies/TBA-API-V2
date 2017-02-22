package com.cpjd.models;

import java.io.Serializable;

public class Award implements Serializable {
	
	private static final long serialVersionUID = 3295532282723916508L;
	
	public String name;
	public long award_type;
	public String event_key;
	public Recipient[] recipient_list;
	public long year;
	
	public class Recipient implements Serializable {
		
		private static final long serialVersionUID = 4476804454775395554L;
		
		public long team_number;
		public String awardee;
	}
}
