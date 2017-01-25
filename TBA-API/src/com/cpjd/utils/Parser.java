package com.cpjd.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cpjd.main.Constants;
import com.cpjd.main.Settings;
import com.cpjd.models.Award;
import com.cpjd.models.Event;
import com.cpjd.models.Event.Webcast;
import com.cpjd.models.Match;
import com.cpjd.models.Match.Video;
import com.cpjd.models.Media;
import com.cpjd.models.Robot;
import com.cpjd.models.Team;
import com.cpjd.requests.EventRequest;

/**
 * Parses raw data and transforms it into models.
 * @author Will Davies
 *
 */
public class Parser {
	@SuppressWarnings("rawtypes")
	protected Team parseTeam(Object object) {
		Team t = new Team();
		HashMap hash = (HashMap) object;

		try { t.name = (String) hash.get("name"); } catch(Exception e) {}
		try { t.team_number = (Long) hash.get("team_number"); } catch(Exception e) {}
		try { t.website = (String) hash.get("website"); } catch(Exception e) {}
		try { t.locality = (String) hash.get("locality"); } catch(Exception e) {}
		try { t.region = (String) hash.get("region"); } catch(Exception e) {}
		try { t.country_name = (String) hash.get("country_name"); } catch(Exception e) {}
		try { t.location = (String) hash.get("location"); } catch(Exception e) {}
		try { t.key = (String) hash.get("key"); } catch(Exception e) {}
		try { t.nickname = (String) hash.get("nickname"); } catch(Exception e) {}
		try { t.rookie_year = (Long) hash.get("rookie_year"); } catch(Exception e) {}
		try { t.motto = (String) hash.get("motto"); } catch(Exception e) {}

		return t;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Media parseMedia(Object object) {
		Media m = new Media();
		HashMap hash = (HashMap) object;
		
		try { m.type = (String) hash.get("type"); } catch(Exception e) {}
		try { m.preferred = (boolean) hash.get("preferred"); } catch(Exception e) {}
		try { m.foreign_key = (String) hash.get("foreign_key"); } catch(Exception e) {}
		
		try {
			JSONObject details = (JSONObject) hash.get("details");
			Iterator<String> itr = details.keySet().iterator();
			String[] toGet = new String[details.keySet().size()];
			for (int i = 0; itr.hasNext(); i++) {
				String key = itr.next();
				toGet[i] = (String) details.get(key);
			}
			m.details = toGet;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return m;
	}
	
	@SuppressWarnings("rawtypes")
	protected Robot parseRobot(Object object) {
		Robot r = new Robot();
		HashMap hash = (HashMap) object;

		try { r.key = (String) hash.get("key"); } catch(Exception e) {}
		try { r.team_key = (String) hash.get("team_key"); } catch(Exception e) {}
		try { r.year = (Long) hash.get("year"); } catch(Exception e) {}
		try { r.name = (String) hash.get("name"); } catch(Exception e) {}

		return r;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Match parseMatch(Object object) {
		Match m = new Match();
		HashMap hash = (HashMap) object;
		try { m.key = (String) hash.get("key"); } catch(Exception e) {}
		try { m.comp_level = (String) hash.get("comp_level"); } catch(Exception e) {}
		try { m.set_number = (Long) hash.get("set_number"); } catch(Exception e) {}
		try { m.match_number = (Long) hash.get("match_number"); } catch(Exception e) {}
		try { m.event_key = (String) hash.get("event_key"); } catch(Exception e) {}
		try { m.time_string = (String) hash.get("time_string"); } catch(Exception e) {}		
		try { m.time = Long.parseLong(hash.get("time").toString()); } catch(Exception e) {}
		
		JSONArray vids = (JSONArray) hash.get("videos");
		try {
			m.videos = new Video[vids.size()];
			for(int i = 0; i < m.videos.length; i++) {
				JSONObject vid = (JSONObject) vids.get(i);
				Video v = m.new Video();
				v.type = (String) vid.get("type");
				v.key = (String) vid.get("key");
				m.videos[i] = v;
			}
		} catch(Exception e) {}
		
		try {
		JSONObject alliances = (JSONObject) hash.get("alliances");
		JSONObject blueTeam = (JSONObject) alliances.get("blue");
		JSONObject redTeam = (JSONObject) alliances.get("red");

		m.blueScore = (Long) blueTeam.get("score");
		m.redScore = (Long) redTeam.get("score");

		m.blueTeams = new String[3];
		m.redTeams = new String[3];

		String[] redTeamTokens = redTeam.get("teams").toString().replace("\"", "").replace("[", "").replace("]", "").split(",");
		String[] blueTeamTokens = blueTeam.get("teams").toString().replace("\"", "").replace("[", "").replace("]", "").split(",");

		for (int j = 0; j < 3; j++) {
			m.blueTeams[j] = blueTeamTokens[j];
			m.redTeams[j] = redTeamTokens[j];
		}

		try {
			JSONObject score_breakdown = (JSONObject) hash.get("score_breakdown");
			JSONObject red = (JSONObject) score_breakdown.get("red");
			JSONObject blue = (JSONObject) score_breakdown.get("blue");

			m.scorableItems = new String[red.keySet().size()];
			m.redValues = new String[m.scorableItems.length];
			m.blueValues = new String[m.scorableItems.length];
			Iterator<String> itr = red.keySet().iterator();
			for (int i = 0; itr.hasNext(); i++) {
				String key = itr.next();

				m.scorableItems[i] = key;

				m.redValues[i] = red.get(key).toString();
				m.blueValues[i] = blue.get(key).toString();
			}
		} catch (Exception e) {}
		} catch(Exception e) {}
		return m;
	}
	@SuppressWarnings("rawtypes")
	protected Award parseAward(Object object) {
		Award a = new Award();
		HashMap hash = (HashMap) object;
		
		try { a.event_key = (String) hash.get("event_key"); } catch(Exception e) {}
		try { a.award_type = (Long) hash.get("award_type"); } catch(Exception e) {}
		try { a.name = (String) hash.get("name"); } catch(Exception e) {}
		try { a.year = (Long) hash.get("year"); } catch(Exception e) {}
		try { a.event_key = (String) hash.get("event_key"); } catch(Exception e) {}
		
		try {
			JSONArray recipient_list = (JSONArray) hash.get("recipient_list");
			a.recipient_list = new Award.Recipient[recipient_list.size()];
			for (int j = 0; j < recipient_list.size(); j++) {
				Award.Recipient r = a.new Recipient();
				
				JSONObject obj = (JSONObject) recipient_list.get(j);
				
				HashMap hash2 = (HashMap) obj;
				r.team_number = (Long) hash2.get("team_number");
				r.awardee = (String) hash2.get("awardee");

				a.recipient_list[j] = r;
			}
		} catch (Exception e) {}
		
		return a;
	}
	
	@SuppressWarnings("rawtypes")
	protected Event parseEvent(Object object) {
		Event e = new Event();
		HashMap hash = (HashMap) object;
		
		// Get general event information first
		try { e.key = (String) hash.get("key"); } catch(Exception ex) {}
		try { e.name = (String) hash.get("name"); } catch(Exception ex) {}
		try { e.short_name = (String) hash.get("short_name"); } catch(Exception ex) {}
		try { e.event_code = (String) hash.get("event_code"); } catch(Exception ex) {}
		try { e.event_type_string = (String) hash.get("event_type_string"); } catch(Exception ex) {}
		try { e.event_type = (long) hash.get("event_type"); } catch(Exception ex) {}
		try { e.event_district_string = (String) hash.get("event_district_string"); } catch(Exception ex) {}
		try { e.event_district = (long) hash.get("event_district"); } catch(Exception ex) {}
		try { e.year = (long) hash.get("year"); } catch(Exception ex) {}
		try { e.week = (long) hash.get("week"); } catch(Exception ex) {}
		try { 	e.location = (String) hash.get("location"); } catch(Exception ex) {}
		try { e.venue_address = (String) hash.get("venue_address"); } catch(Exception ex) {}
		try { 	e.timezone = (String) hash.get("timezone"); } catch(Exception ex) {}
		try { e.website = (String) hash.get("website"); } catch(Exception ex) {}
		try { e.official = (boolean) hash.get("official"); } catch(Exception ex) {}
		try { e.start_date = (String) hash.get("start_date"); } catch(Exception ex) {}
		
		if(Settings.GET_EVENT_WEBCASTS) {
			try {
				JSONArray array = (JSONArray) hash.get("webcast");
				e.webcasts = new Webcast[array.size()];
				for(int i = 0 ; i < e.webcasts.length; i++) {
					JSONObject obj = (JSONObject) array.get(i);
					Webcast w = e.new Webcast();
					w.type = (String) obj.get("type");
					w.channel = (String) obj.get("channel");
					e.webcasts[i] = w;
				}
			} catch(Exception ex) {}
		}
		
		// Process alliance picks and declines
		if(Settings.GET_EVENT_ALLIANCES) try { e = parseEventAlliances(e, hash); } catch(Exception ex) {}
		if(Settings.GET_EVENT_TEAMS) try { e = parseEventTeams(e, hash); } catch(Exception ex) {}
		if(Settings.GET_EVENT_MATCHES) try { e = parseEventMatches(e, hash); } catch(Exception ex) {}
		if(Settings.GET_EVENT_AWARDS) try { e = parseEventAwards(e, hash); } catch(Exception ex) { ex.printStackTrace();}
		
		return e;
	}
	
	@SuppressWarnings("rawtypes")
	private Event parseEventAwards(Event event, HashMap hash) throws Exception {
		Award[] awards = new EventRequest().getEventAwards(event.key.replace(String.valueOf(event.year), ""), (int) event.year);
		event.awards = awards;
		return event;
	}
	
	@SuppressWarnings("rawtypes")
	private Event parseEventMatches(Event event, HashMap hash) throws Exception {
		Match[] matches = new EventRequest().getMatches(event.key.replace(String.valueOf(event.year), ""), (int) event.year);
		event.matches = matches;
		return event;
	}
	
	@SuppressWarnings("rawtypes")
	private Event parseEventTeams(Event event, HashMap hash) throws Exception {
		Team[] teams = new EventRequest().getTeams(event.key.replace(String.valueOf(event.year), ""), (int) event.year);
		event.teams = teams;
		teams = null;
		
		// Check if the user wants the teams to be ranked
		if(Settings.FIND_TEAM_RANKINGS) {
			// First, add all scoring information to the teams
			JSONArray ranks = (JSONArray) IO.doRequest(Constants.URL + "event/" + event.year + event.key.replace(String.valueOf(event.year), "") + "/rankings", Constants.APPID);
			
			for(int i = 1; i < ranks.size(); i++) {
				JSONArray array = (JSONArray) ranks.get(i);
				for(int j = 0; j < event.teams.length; j++) {
					if(event.teams[j].team_number == Integer.parseInt((String) array.get(1))) {
						event.teams[j].rank = Integer.parseInt((String) array.get(0));
						event.teams[j].rankingScore = Double.parseDouble((String) array.get(2));
						event.teams[j].auto = Double.parseDouble((String) array.get(3));
						event.teams[j].scaleOrChallenge = Double.parseDouble((String) array.get(4));
						event.teams[j].goals = Double.parseDouble((String) array.get(5));
						event.teams[j].defense = Double.parseDouble((String) array.get(6));
						event.teams[j].record = (String) array.get(7);
						event.teams[j].played = Integer.parseInt((String) array.get(8));
					}
				}
			}
			// Next, rank the teams
			ArrayList<Team> tempRanked = new ArrayList<Team>();
			for(int i = 0; i < event.teams.length; i++) {
				if(event.teams[i].rank == tempRanked.size() + 1) {
					tempRanked.add(event.teams[i]);
					i = -1;
				}
			}
			Team[] ranked = new Team[tempRanked.size()];
			for(int i = 0; i < ranked.length; i++) {
				ranked[i] = tempRanked.get(i);
			}
			event.teams = ranked;
		}
		
		return event;
	}
	
	@SuppressWarnings("rawtypes") 
	private Event parseEventAlliances(Event event,HashMap hash) throws Exception {
		JSONArray alliances = (JSONArray) hash.get("alliances");
		event.alliances = new Event.Alliance[alliances.size()];

		for(int i = 0; i < alliances.size(); i++) {
			JSONObject obj = (JSONObject) alliances.get(i);
			Event.Alliance a = event.new Alliance();
			a.name = (String) obj.get("name");
			try {
				a.backupIn = (String) ((JSONObject)obj.get("backup")).get("in");
				a.backupOut = (String) ((JSONObject)obj.get("backup")).get("out");
			} catch(Exception e) {}
			
			JSONArray declines = (JSONArray) obj.get("declines");
			JSONArray picks = (JSONArray) obj.get("picks");
			a.declines = new String[declines.size()];
			a.picks = new String[picks.size()];
			
			for(int j = 0; j < declines.size(); j++) {
				a.declines[j] = (String) declines.get(j);
			}
			for(int j = 0; j < picks.size(); j++) {
				a.picks[j] = (String) picks.get(j);
			}
		}
		
		return event;
	}
	
}
