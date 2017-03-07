package com.cpjd.main;

import java.util.HashMap;

import com.cpjd.models.Award;
import com.cpjd.models.Event;
import com.cpjd.models.Match;
import com.cpjd.models.Media;
import com.cpjd.models.Robot;
import com.cpjd.models.Team;
import com.cpjd.models.other.District;
import com.cpjd.models.other.DistrictRanking;
import com.cpjd.models.other.EventPoint;
import com.cpjd.models.other.Ranking;
import com.cpjd.requests.DistrictRequest;
import com.cpjd.requests.EventRequest;
import com.cpjd.requests.MatchRequest;
import com.cpjd.requests.TeamRequest;

/**
 * Use the TBA class for pulling all your data. 
 * 
 * All data requests are divided into four classes: DistrictRequest, EventRequest, MatchRequest, and TeamRequest.
 * You can also use those classes directly if you'd like, otherwise, just use this class.
 * 
 * @author Will Davies
 *
 */
public class TBA {
	
	public TBA() {
	}
	
	/**
	 * REQUIRED Creates a new TBA object for getting data. The three parameters
	 * are required for the identification header sent to the server.
	 * 
	 * @param id The team / person id
	 * @param description  App description
	 * @param version App version
	 */
	public static void setID(String id, String description, String version) {
		Constants.APPID = id + ":" + description + ":" + version;
	}
	
	/* TEAM REQUEST METHODS */
	/**
	 * A page of teams, zero-indexed. Each page consists of teams whose numbers start at start = 500 * page_num and end at end = start + 499, inclusive.
	 * @param pageNumber The aforementioned page from which to get the team list. Examples: 0, 4, 7
	 * @return An array of the <b>Team</b> model
	 */
	public Team[] getTeams(int pageNumber) {
		return new TeamRequest().getTeams(pageNumber);
	}
	
	/**
	 * Returns a single <b>Team</b> model
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return <b>Team</b> model
	 */
	public Team getTeam(int teamNumber) {
		return new TeamRequest().getTeam(teamNumber);
	}

	/**
	 * Returns all the events a team has participated in for a given year.
	 * 
	 * @param teamNumber The team's frc number (example: 4850)
	 * @return An array of the <b>Event</b> model
	 */
	public Event[] getTeamEvents(int teamNumber, int year) {
		return new TeamRequest().getTeamEvents(teamNumber, year);
	}
	
	/**
	 * Returns all the awards that the specified team won in an event.
	 * @param year The event's year (example: 2017)
	 * @param eventKey The event's code (example: casd)
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of the <b>Award</b> model
	 */
	public Award[] getTeamEventAwards(int year, String eventKey, int teamNumber) {
		return new TeamRequest().getTeamEventAwards(year, eventKey, teamNumber);
	}
	
	/**
	 * Returns all the matches the specified team participated in within an event.
	 * @param year The event's year (example: 2017)
	 * @param eventKey The event's code (example: casd)
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of the <b>Match</b> model
	 */
	public Match[] getTeamEventMatches(int year, String eventKey, int teamNumber) {
		return new TeamRequest().getTeamEventMatches(year, eventKey, teamNumber);
	}

	/**
	 * Returns a list of years the specified team has participated in robotics events.
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of type <b>long</b>, each element is a year
	 */
	public long[] getYearsParticipated(int teamNumber) {
		return new TeamRequest().getYearsParticipated(teamNumber);
	}

	/**
	 * Returns a list of media that this team is contained in.
	 * @param teamNumber The team's frc number (example: 4859)
	 * @param year The year to get media from (example: 2017)
	 * @return An array of the <b>Media</b> model
	 */
	public Media[] getMedia(int teamNumber, int year) {
		return new TeamRequest().getMedia(teamNumber, year);
	}

	/**
	 * Gets a list of all events the specified team has participated in.
	 * 
	 * This method can take some time to run so be wary when pulling a list of a team's events.
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of the <b>Event</b> model
	 */
	public Event[] getTeamHistoryEvents(int teamNumber) {
		return new TeamRequest().getTeamHistoryEvents(teamNumber);
	}

	/**
	 * Gets a list of all awards the specified team has ever won.
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of the <b>Award</b> model
	 */
	public Award[] getTeamHistoryAwards(int teamNumber) {
		return new TeamRequest().getTeamHistoryAwards(teamNumber);
	}
	
	/**
	 * Gets a list of all robots the team has ever built.
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of the <b>Robot</b> model
	 */
	public Robot[] getTeamHistoryRobots(int teamNumber) {
		return new TeamRequest().getTeamHistoryRobots(teamNumber);
	}

	/**
	 * Gets a list of all districts the specified team has been a part of.
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of type <b>String</b>. Each element is a year plus a region 
	 */
	public String[] getTeamDistricts(int teamNumber) {
		return new TeamRequest().getTeamDistricts(teamNumber);
	}

	/* EVENT REQUEST METHODS */
	/**
	 * Gets a list of all events within the specified year.
	 * @param year A year (example: 2017)
	 * @param sorted Whether to return the event[] array with index 0 being the earliest event.
	 * @return An array of the <b>Event</b> model
	 */
	public Event[] getEvents(int year, boolean sorted) {
		return new EventRequest().getEvents(year, sorted);
	}
	/**
	 * Returns the <b>Event</b> model for the specified eventKey. Some values
	 * may be null if they are not available on the server, or they are disabled
	 * in the <b>Settings</b> class.
	 * 
	 * @param eventKey The event key (example: casd)
	 * @param year The year of the event (example: 2017)
	 * @return <b>Event</b> model
	 */
	public Event getEvent(String eventKey, int year) {
		return new EventRequest().getEvent(year + eventKey);
	}
	/**
	 * Returns the <b>Event</b> model for the specified eventKey. Some values
	 * may be null if they are not available on the server, or they are disabled
	 * in the <b>Settings</b> class.
	 * 
	 * @param key year+key (example: 2016casd)
	 * @return <b>Event</b> model
	 */
	public Event getEvent(String key) {
		return new EventRequest().getEvent(key);
	}
	
	/**
	 * Returns a list of the <b>Team</b> model for the specified eventKey.
	 * Some values may be null if they are not available on the server, or they
	 * are disabled in the <b>Settings</b> class.
	 * 
	 * @param eventKey The event key (example: casd)
	 * @param year The event year (example: 2017)
	 * @return An array of the <b>Team</b> model
	 */
	public Team[] getTeams(String eventKey, int year) {
		return new EventRequest().getTeams(eventKey, year);
	}
	/**
	 * Returns a list of the <b>Match</b> model for the specified eventKey.
	 * Some values may be null if they are not available on the server, or they
	 * are disabled in the <b>Settings</b> class.
	 * 
	 * @param eventKey The event key (example: casd)
	 * @param year The event year (example: 2017)
	 * @return An array of the <b>Match</b> model
	 */
	public Match[] getMatches(String eventKey, int year, boolean sorted) {
		return new EventRequest().getMatches(eventKey, year, sorted);
	}
	
	/**
	 * Gets some event stats.
	 * @param event
	 * @param stateventKey
	 * @return
	 */
	public HashMap<Integer, Double> getEventStats(Event event, String stateventKey) {
		return new EventRequest().getEventStats(event, stateventKey);
	}
	
	/**
	 * Gets a list of team rankings from the specified event.
	 * @param eventKey The event's key (example: casd)
	 * @param year The year that the event occured (example: 2017)
	 * @return An array of the <b>Ranking</b> model
	 */
	public Ranking[] getEventRankings(String eventKey, int year) {
		return new EventRequest().getEventRankings(eventKey, year);
	}
	
	/**
	 * Gets a list of all awards given within the specified event.
	 * @param eventKey The event's key (example: casd)
	 * @param year The year that the event occured (example: 2017)
	 * @return An arry of the <b>Award</b> model
	 */
	public Award[] getEventAwards(String eventKey, int year) {
		return new EventRequest().getEventAwards(eventKey, year);
	}
	
	/**
	 * Gets a list of rankings within the district of the specified event.
	 * @param eventKey The event's key (example: casd)
	 * @param year  The year that the event occured (example: 2017)
	 * @return An array of the <b>EventPoint</b> model
	 */
	public EventPoint[] getEventDistrictPoints(String eventKey, int year) {
		return new EventRequest().getEventDistrictPoints(eventKey, year);
	}
	
	/* MATCH REQUEST METHODS */
	/**
	 * Returns a single <b>Match</b> model
	 * 
	 * @param year The event year (example: 2016)
	 * @param eventKey The event key (example: casd)
	 * @param matchKey The match key (example: f1m1)
	 * @return A <b>Match</b> model
	 */
	public Match getMatch(int year, String eventKey, String matchKey) {
		return new MatchRequest().getMatch(year, eventKey, matchKey);
	}
	/* DISTRICT REQUEST METHODS */
	/**
	 * Gets a list of all the existent districts within the specified year.
	 * @param year A year (example: 2017)
	 * @return An array of the <b>District</b> model
	 */
	public District[] getDistricts(int year) {
		return new DistrictRequest().getDistricts(year);
	}
	/**
	 * Gets a list of all events within the specified district.
	 * @param districtShort The district's short code (example: ne)
	 * @param year The district's year (example: 2017)
	 * @return An array of the <b>Event</b> model
	 */
	public Event[] getDistrictEvents(String districtShort, int year) {
		return new DistrictRequest().getDistrictEvents(districtShort, year);
	}
	
	/**
	 * Gets a list of the rankings of teams within the district.
	 * @param districtShort The district's short code (example: ne)
	 * @param year The district's year (example: 2017)
	 * @return An array of the <b>DistrictRanking</b> model
	 */
	public DistrictRanking[] getDistrictRankings(String districtShort, int year) {
		return new DistrictRequest().getDistrictRankings(districtShort, year);
	}
	
	/**
	 * Gets a list of all the teams within the specified district.
	 * @param districtShort The district's short code (example: ne)
	 * @param year  The district's year (example: 2017)
	 * @return A array of the <b>Team</b> model
	 */
	public Team[] getDistrictTeams(String districtShort, int year) {
		return new DistrictRequest().getDistrictTeams(districtShort, year);
	}
	
}
