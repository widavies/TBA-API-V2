package com.cpjd.models.other;

import java.io.Serializable;

public class Ranking implements Serializable {
	
	private static final long serialVersionUID = -8321715421269855400L;
	
	public long rank;
	public long team;
	public long played;
	public long seeding_score;
	public long coopertition_bonus;
	public long hanging_points;
}
