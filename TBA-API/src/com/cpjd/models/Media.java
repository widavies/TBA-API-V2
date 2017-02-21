package com.cpjd.models;

import java.io.Serializable;

public class Media implements Serializable {
	
	private static final long serialVersionUID = -4875694744217079818L;
	
	public String type;
	public String foreign_key;
	public String[] details;
	public boolean preferred;
}
