package com.cpjd.requests;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cpjd.main.Constants;
import com.cpjd.models.Award;
import com.cpjd.models.Event;
import com.cpjd.models.Match;
import com.cpjd.models.Media;
import com.cpjd.models.Robot;
import com.cpjd.models.Team;
import com.cpjd.utils.IO;
import com.cpjd.utils.Parser;

/**
 * TeamRequest contains all the request methods found on https://www.thebluealliance.com/apidocs under the <b>Team Requests</b> category.
 * Note: Any of the models or values they contain can be null. Make sure you account for this in your application.
 * @author Will Davies
 *
 */
public class TeamRequest extends Parser {

	/**
	 * A page of teams, zero-indexed. Each page consists of teams whose numbers start at start = 500 * page_num and end at end = start + 499, inclusive.
	 * @param pageNumber The aforementioned page from which to get the team list. Examples: 0, 4, 7
	 * @return An array of the <b>Team</b> model
	 */
	public Team[] getTeams(int pageNumber) {
		JSONArray teams = (JSONArray) IO.doRequest(Constants.URL + "teams/"+pageNumber, Constants.APPID);

		Team[] toGet = new Team[teams.size()];

		for (int i = 0; i < toGet.length; i++) {
			toGet[i] = parseTeam(teams.get(i));
		}

		return toGet;
	}
	
	/**
	 * Returns a single <b>Team</b> model
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return <b>Team</b> model
	 */
	public Team getTeam(int teamNumber) {
		return parseTeam(IO.doRequest(Constants.URL + "team/frc" + teamNumber, Constants.APPID));
	}

	/**
	 * Returns all the events a team has participated in for a given year.
	 * 
	 * @param teamNumber The team's frc number (example: 4850)
	 * @return An array of the <b>Event</b> model
	 */
	public Event[] getTeamEvents(int teamNumber, int year) {
		JSONArray events = (JSONArray) IO.doRequest(Constants.URL + "team/frc" + teamNumber + "/" + year + "/events", Constants.APPID);
		// Get all the events of a team in a given year.
		Event[] toGet = new Event[events.size()];
		for (int i = 0; i < toGet.length; i++) {
			toGet[i] = parseEvent(events.get(i));
		}
		return toGet;
	}
	
	/**
	 * Returns all the awards that the specified team won in an event.
	 * @param year The event's year (example: 2017)
	 * @param eventKey The event's code (example: casd)
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of the <b>Award</b> model
	 */
	public Award[] getTeamEventAwards(int year, String eventKey, int teamNumber) {
		JSONArray awards = (JSONArray) IO.doRequest(Constants.URL + "team/frc"+teamNumber+"/event/"+year+eventKey+"/awards", Constants.APPID);
		Award[] toGet = new Award[awards.size()];
		for(int i = 0; i < toGet.length; i++) {
			toGet[i] = parseAward(awards.get(i));
		}
		return toGet;
	}
	
	/**
	 * Returns all the matches the specified team participated in within an event.
	 * @param year The event's year (example: 2017)
	 * @param eventKey The event's code (example: casd)
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of the <b>Match</b> model
	 */
	public Match[] getTeamEventMatches(int year, String eventKey, int teamNumber) {
		JSONArray matches = (JSONArray) IO.doRequest(Constants.URL + "team/frc"+teamNumber+"/event/"+year+eventKey+"/matches", Constants.APPID);
		Match[] toGet = new Match[matches.size()];
		for(int i = 0; i < toGet.length; i++) {
			toGet[i] = parseMatch(matches.get(i));
		}
		return toGet;
	}
	
	/**
	 * Returns a list of years the specified team has participated in robotics events.
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of type <b>long</b>, each element is a year
	 */
	public long[] getYearsParticipated(int teamNumber) {
		JSONArray years = (JSONArray) IO.doRequest(Constants.URL + "team/frc"+teamNumber+"/years_participated", Constants.APPID);
		long[] toGet = new long[years.size()];
		for(int i = 0; i < toGet.length; i++) {
			toGet[i] = (Long) years.get(i);
		}
		return toGet;
	}
	
	/**
	 * Returns a list of media that this team is contained in.
	 * @param teamNumber The team's frc number (example: 4859)
	 * @param year The year to get media from (example: 2017)
	 * @return An array of the <b>Media</b> model
	 */
	public Media[] getMedia(int teamNumber, int year) {
		JSONArray medias = (JSONArray) IO.doRequest(Constants.URL + "team/frc"+teamNumber+"/"+year+"/media", Constants.APPID);
		Media[] toGet = new Media[medias.size()];
		for(int i = 0; i < medias.size(); i++) {
			toGet[i] = parseMedia(medias.get(i));
		}
		return toGet;
	}
	
	/**
	 * Gets a list of all events the specified team has participated in.
	 * 
	 * This method can take some time to run so be wary when pulling a list of a team's events.
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of the <b>Event</b> model
	 */
	public Event[] getTeamHistoryEvents(int teamNumber) {
		JSONArray events = (JSONArray) IO.doRequest(Constants.URL + "team/frc"+teamNumber+"/history/events", Constants.APPID);
		Event[] toGet = new Event[events.size()];
		for(int i = 0; i < toGet.length; i++) {
			toGet[i] = parseEvent(events.get(i));
		}
		return toGet;
	}
	
	/**
	 * Gets a list of all awards the specified team has ever won.
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of the <b>Award</b> model
	 */
	public Award[] getTeamHistoryAwards(int teamNumber) {
		JSONArray awards = (JSONArray) IO.doRequest(Constants.URL + "team/frc"+teamNumber+"/history/awards", Constants.APPID);
		Award[] toGet = new Award[awards.size()];
		for(int i = 0; i < toGet.length; i++) {
			toGet[i] = parseAward(awards.get(i));
		}
		return toGet;
	}
	
	/**
	 * Gets a list of all robots the team has ever built.
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of the <b>Robot</b> model
	 */
	public Robot[] getTeamHistoryRobots(int teamNumber) {
		JSONObject robots = (JSONObject) IO.doRequest(Constants.URL + "team/frc"+teamNumber+"/history/robots", Constants.APPID);
		@SuppressWarnings("unchecked")
		Iterator<String> itr = robots.keySet().iterator();
		Robot[] toGet = new Robot[robots.keySet().size()];
		for (int i = 0; itr.hasNext(); i++) {
			String key = itr.next();
			toGet[i] = parseRobot(robots.get(key));
		}
		return toGet;
	}
	
	/**
	 * Gets a list of all districts the specified team has been a part of.
	 * @param teamNumber The team's frc number (example: 4859)
	 * @return An array of type <b>String</b>. Each element is a year plus a region 
	 */
	public String[] getTeamDistricts(int teamNumber) {
		JSONObject object = (JSONObject) IO.doRequest(Constants.URL + "team/frc"+teamNumber+"/history/districts", Constants.APPID);
		@SuppressWarnings("unchecked")
		Iterator<String> itr = object.keySet().iterator();
		String[] toGet = new String[object.keySet().size()];
		for (int i = 0; itr.hasNext(); i++) {
			String key = itr.next();
			toGet[i] = (String) object.get(key);
		}
		return toGet;
	}
	
	/**
	 * If you just want to get the OPR for one team, pass the team into this method
	 * @param team
	 * @return
	 */
	public Team fillOPR(Event event, Team team) {
		return new Parser().parseOPR(event, team);
	}
	
}
