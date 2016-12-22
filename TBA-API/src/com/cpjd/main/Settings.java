package com.cpjd.main;

public class Settings {
	/**
	 * Whether to get rewards won by the event.
	 * Default: true.
	 */
	public static boolean GET_EVENT_AWARDS = false;
	/**
	 * Whether to get event teams and team data.
	 * Default: true.
	 */
	public static boolean GET_EVENT_TEAMS = true;
	/**
	 * Whether to get event matches and match data.
	 * Default: true
	 */
	public static boolean GET_EVENT_MATCHES = true;
	/**
	 * Whether to get picks and declines for each team for alliances.
	 * Default: false
	 */
	public static boolean GET_EVENT_ALLIANCES = false;
	/**
	 * Whether to automatically sort the Event.teams[] array by rankings. Event.teams[0] is the best team.
	 * Default: false
	 */
	public static boolean FIND_TEAM_RANKINGS = false;
}
