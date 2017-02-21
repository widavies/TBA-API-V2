package com.cpjd.models.other;

import java.io.Serializable;

public class EventPoint implements Serializable {
	
	private static final long serialVersionUID = -5961413363920162941L;
	
	public long alliance_points;
	public long total;
	public long award_points;
	public long elim_points;
	public long qual_points;
	
	public boolean district_cmp;
}
