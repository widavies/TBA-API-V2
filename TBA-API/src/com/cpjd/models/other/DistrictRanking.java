package com.cpjd.models.other;

import java.io.Serializable;

public class DistrictRanking implements Serializable{

	private static final long serialVersionUID = 745116446620821060L;
	
	public long point_total;
	public String team_key;
	public long rank;
	public long rookie_bonus;
	public EventPoint[] points;
}
