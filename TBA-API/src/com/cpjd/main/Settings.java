package com.cpjd.main;

/**
 * Configure different API data pulling settings.
 * @author Will Davies
 *
 */
public class Settings extends Constants {
	
	/**
	 * Set whether to enable support for the V3 API. If V3 isn't worked, then the API will default to V2.
	 * Note: V3 API functionality is unstable currently.
	 * @param b Whether to enable or disable V3 support.
	 */
	public static void useAPIV3(boolean b) {
		if(b) Constants.URL = "https://www.thebluealliance.com/api/v3/";
		else Constants.URL = "https://www.thebluealliance.com/api/v2/";
		
		Constants.USING_V3 = b;
	}
	
	/**
	 * The following settings ONLY apply to data contained within an event. You can configure
	 * what data to pull, and if any data should be sorted. Note: Enabling more settings will make
	 * event pull request take longer because more data has to be downloaded.
	 */
	
	/**
	 * Set to <b>true</b> to add any awards in a particular event to that event's model when pulling it.
	 * 
	 * Note: This setting only applies to the data contained within an event.
	 * Default: false
	 */
	public static boolean GET_EVENT_AWARDS = false;
	/**
	 * Set to <b>true</b> to add a complete list of teams in a particular event to its event's model when pulling it.
	 * 
	 * Note: This setting only applies to the data contained within an event.
	 * Default: true.
	 */
	public static boolean GET_EVENT_TEAMS = true;
	/**
	 *Set to <b>true</b> to add a complete list of matches in a particular event to its event's model when pulling it.
	 *
	 * Note: This setting only applies to the data contained within an event.
	 * Default: true
	 */
	public static boolean GET_EVENT_MATCHES = true;
	/**
	 * Whether to get picks and declines for each team for alliances when pulling an event..
	 * 
	 * Note: This setting only applies to the data contained within an event.
	 * Default: false
	 */
	public static boolean GET_EVENT_ALLIANCES = false;
	/**
	 * Whether to automatically sort the Event.teams[] array by rankings. Event.teams[0] is the highest ranked team.
	 * 
	 * Note: This setting only applies to the data contained within an event.
	 * Default: false
	 */
	public static boolean FIND_TEAM_RANKINGS = false;
	/**
	 * Set to <b>true</b> to add any webcasts for the event to it's model.
	 * 
	 * Note: This setting only applies to the data contained within an event.
	 * Default: false
	 */
	public static boolean GET_EVENT_WEBCASTS = false;
}
