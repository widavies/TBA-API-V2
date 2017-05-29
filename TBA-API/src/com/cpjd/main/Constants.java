package com.cpjd.main;

/**
 * Stores the app contants. Don't modify this class or any of it's variables.
 * @author Will Davies
 *
 */
public class Constants {
	/**
	 * Do NOT change this variable when attempting to enable V3 support, use Settings.setV3(true) instead.
	 */
	protected static boolean USING_V3;
	public static String URL = "https://www.thebluealliance.com/api/v2/";
	public static String APPID;
	
	/**
	 * Returns whether the API should attempt to use new V3 features, such as simple models.
	 * @return <b>true</b> if V3 support is enabled
	 */
	public static boolean usingV3() { return USING_V3; }
	
	// Return values for doesMatchContainTeam() in Match
	public static final int DOES_NOT_CONTAIN = 0;
	public static final int CONTAINS_TEAM_BLUE = 1;
	public static final int CONTAINS_TEAM_RED = 2;
}
