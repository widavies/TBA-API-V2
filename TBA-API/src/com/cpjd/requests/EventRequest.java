package com.cpjd.requests;

import java.util.HashMap;

import org.json.simple.JSONArray;

import com.cpjd.main.Constants;
import com.cpjd.models.Award;
import com.cpjd.models.Event;
import com.cpjd.models.Match;
import com.cpjd.models.Team;
import com.cpjd.models.other.EventPoint;
import com.cpjd.models.other.Ranking;
import com.cpjd.utils.IO;
import com.cpjd.utils.Parser;

public class EventRequest extends Parser {
	
	/**
	 * Gets a list of all events within the specified year.
	 * @param year A year (example: 2017)
	 * @return An array of the <b>Event</b> model
	 */
	public Event[] getEvents(int year) {
		JSONArray events = (JSONArray) IO.doRequest(Constants.URL + "events/"+year, Constants.APPID);
		Event[] toGet = new Event[events.size()];
		for(int i = 0; i < toGet.length; i++) {
			toGet[i] = parseEvent(events.get(i));
		}
		return toGet;
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
		return parseEvent(IO.doRequest(Constants.URL + "event/" + year + eventKey, Constants.APPID));
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
		JSONArray teams = (JSONArray) IO.doRequest(Constants.URL + "event/" + year + eventKey + "/teams", Constants.APPID);

		Team[] toGet = new Team[teams.size()];

		for (int i = 0; i < toGet.length; i++) {
			toGet[i] = parseTeam(teams.get(i));
		}

		return toGet;
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
	public Match[] getMatches(String eventKey, int year) {
		JSONArray matches = (JSONArray) IO.doRequest(Constants.URL + "event/" + year + eventKey + "/matches", Constants.APPID);

		Match[] toGet = new Match[matches.size()];

		for (int i = 0; i < toGet.length; i++) {
			toGet[i] = parseMatch(matches.get(i));
		}

		return toGet;
	}
	
	/**
	 * Gets some event stats.
	 * @param event
	 * @param stateventKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Double> getEventStats(Event event, String stateventKey) {
		HashMap<String, Double> stat = ((HashMap<String, HashMap<String, Double>>) IO.doRequest(
				Constants.URL + "event/" + event.year + event.key+ "/stats", Constants.APPID)).get(stateventKey);

		HashMap<Integer, Double> toGet = new HashMap<Integer, Double>();

		for (String eventKey : stat.keySet()) { // Transfer to the other Hashmap
			toGet.put(Integer.parseInt(eventKey), stat.get(eventKey));
		}
		return toGet;
	}
	
	/**
	 * Gets a list of team rankings from the specified event.
	 * @param eventKey The event's key (example: casd)
	 * @param year The year that the event occured (example: 2017)
	 * @return An array of the <b>Ranking</b> model
	 */
	public Ranking[] getEventRankings(String eventKey, int year) {
		JSONArray ranks = (JSONArray) IO.doRequest(Constants.URL + "event/"+year+eventKey+"/rankings", Constants.APPID);
		Ranking[] toGet = new Ranking[ranks.size()];
		for(int i = 0; i < ranks.size(); i++) {
			Ranking r = new Ranking();
			JSONArray items = (JSONArray) ranks.get(i);
			r.rank = (Long) items.get(0);
			r.team = (Long) items.get(1);
			r.played = (Long) items.get(2);
			r.seeding_score = (Long) items.get(3);
			r.coopertition_bonus = (Long) items.get(4);
			toGet[i] = r;
		}
		return toGet;
	}
	
	/**
	 * Gets a list of all awards given within the specified event.
	 * @param eventKey The event's key (example: casd)
	 * @param year The year that the event occured (example: 2017)
	 * @return An arry of the <b>Award</b> model
	 */
	public Award[] getEventAwards(String eventKey, int year) {
		JSONArray awards = (JSONArray) IO.doRequest(Constants.URL + "event/"+year+eventKey+"/awards", Constants.APPID);
		Award[] toGet = new Award[awards.size()];
		for(int i = 0; i < toGet.length; i++) {
			toGet[i] = parseAward(awards.get(i));
		}
		return toGet;
	}
	
	/**
	 * Gets a list of rankings within the district of the specified event.
	 * @param eventKey The event's key (example: casd)
	 * @param year  The year that the event occured (example: 2017)
	 * @return An array of the <b>EventPoint</b> model
	 */
	public EventPoint[] getEventDistrictPoints(String eventKey, int year) {
		JSONArray points = (JSONArray) IO.doRequest(Constants.URL + "event/"+year+eventKey+"/district_points", Constants.APPID);
		EventPoint[] toGet = new EventPoint[points.size()];
		for(int i = 0; i < points.size(); i++) {
			EventPoint dp = new EventPoint();
			JSONArray items = (JSONArray) points.get(i);
			dp.alliance_points = (Long) items.get(0);
			dp.total = (Long) items.get(1);
			dp.award_points = (Long) items.get(2);
			dp.elim_points = (Long) items.get(3);
			dp.qual_points = (Long) items.get(4);
			toGet[i] = dp;
		}
		return toGet;
	}
}
