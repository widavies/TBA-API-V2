package com.cpjd.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.cpjd.models.Award;
import com.cpjd.models.Award.Recipient;
import com.cpjd.models.Event;
import com.cpjd.models.Event.Alliance;
import com.cpjd.models.Match;
import com.cpjd.models.Team;

public class TBA {

	private final JSONParser parser = new JSONParser();
	
	/**
	 * Creates a new TBA object for getting data.
	 * The three parameters are required for the identification header sent to the server.
	 * @param id The team / person id
	 * @param description App description
	 * @param version App version
	 */
	public TBA(String id, String description, String version) {
		Constants.APPID = id + ":" + description + ":" + version;
	}

	/**
	 * Returns the </code>Event</code> model for the specified key.
	 * Some values may be null if they are not available on the server, or 
	 * they are disabled in the </code>Settings</code> class. 
	 * @param key The event key (example: casd)
	 * @param year The year of the event (example: 2016)
	 * @return An instance of the </code>Event</code> model
	 */
	@SuppressWarnings("rawtypes")
	public Event getEvent(String key, int year) {
		HashMap hash = (HashMap) doRequest(Constants.URL + "event/"+year+key, Constants.APPID);
		Event event = new Event();
		event.key = year + key;
		event.name = (String) hash.get("name");
		event.short_name = (String) hash.get("short_name");
		event.event_code = (String) hash.get("event_code");
		event.event_type_string = (String) hash.get("event_type_string");
		event.event_district_string = (String) hash.get("event_district_string");
		event.event_district = (Long) hash.get("event_district");
		event.year = (Long) hash.get("year");
		event.week = (Long) hash.get("week");
		event.location = (String) hash.get("location");
		event.venue_address = (String) hash.get("venue_address");
		event.timezone = (String) hash.get("timezone");
		event.website = (String) hash.get("website");
		event.official = (boolean) hash.get("official");
		if(Settings.GET_EVENT_MATCHES) event.matches = getMatches(key, year);
		if(Settings.GET_EVENT_TEAMS) event.teams = getTeams(key, year);
		if(Settings.GET_EVENT_AWARDS) event.awards = getAwards(key, year);
		
		if(Settings.GET_EVENT_ALLIANCES) {
			JSONArray alliances = (JSONArray) hash.get("alliances");
			event.alliances = new Alliance[alliances.size()];
			
			for(int i = 0; i < alliances.size(); i++) {
				JSONObject obj = (JSONObject) alliances.get(i);
				JSONArray declines = (JSONArray) obj.get("declines");
				JSONArray picks = (JSONArray) obj.get("picks");
				event.alliances[i].declines = new String[declines.size()];
				for(int j = 0; j < event.alliances[i].declines.length; j++) {
					 event.alliances[i].declines[j] = (String) declines.get(j);
				}
				event.alliances[i].picks = new String[picks.size()];
				for(int j = 0; j < event.alliances[i].picks.length; j++) {
					event.alliances[i].picks[j] = (String) picks.get(j);
				}
			}
		}
		return event;
	}
	
	/**
	 * Returns a list of the </code>Match</code> model for the specified key.
	 * Some values may be null if they are not available on the server, or 
	 * they are disabled in the </code>Settings</code> class. 
	 * @param key The event key (example: casd)
	 * @param year The event year (example: 2016) 
	 * @return An array of type </code>Match</code>
	 */
	public Match[] getMatches(String key, int year) {
		JSONArray matches = (JSONArray) doRequest(Constants.URL + "event/"+year+key+"/matches", Constants.APPID);
		
		Match[] toGet = new Match[matches.size()];
		
		for(int i = 0; i < toGet.length; i++) {
			toGet[i] = parseMatch(matches.get(i));
		}
		
		return toGet;
	}
	
	/**
	 * Returns a list of the </code>Team</code> model for the specified key.
	 * Some values may be null if they are not available on the server, or 
	 * they are disabled in the </code>Settings</code> class. 
	 * @param key The event key (example: casd)
	 * @param year The event year (example: 2016) 
	 * @return An array of type </code>Match</code>
	 */
	public Team[] getTeams(String key, int year) {
		JSONArray teams = (JSONArray) doRequest(Constants.URL + "event/"+year+key+"/teams", Constants.APPID);
		
		Team[] toGet = new Team[teams.size()];
		
		for(int i = 0; i < toGet.length; i++) {
			toGet[i] = parseTeam(teams.get(i));
		}
		
		
		return toGet;
	}
	
	/**
	 * Returns a single </code>Team</code> model 
	 * @param number The team's frc number (example: 4859)
	 * @return </code>Team</code> model
	 */
	public Team getTeam(int number) {
		return parseTeam(doRequest(Constants.URL + "team/frc"+number, Constants.APPID));
	}
	
	/**
	 * Returns a single </code>Match</code> model 
	 * @param year The event year (example: 2016)
	 * @param key The event key (example: casd)
	 * @param matchKey The match key (example: f1m1)
	 * @return </code>Match</code> model
	 */
	public Match getMatch(int year, String key, String matchKey) {
		return parseMatch(doRequest(Constants.URL + "match/"+year+key+"_"+key, Constants.APPID));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Match parseMatch(Object object) {
		Match m = new Match();
		HashMap hash = (HashMap) object;
		m.key = (String) hash.get("key");
		m.comp_level = (String) hash.get("comp_level");
		m.set_number = (Long) hash.get("set_number");
		m.match_number = (Long) hash.get("match_number");
		m.event_key = (String) hash.get("event_key");
		m.time_string = (String) hash.get("time_string");
		try {
			m.time = Long.parseLong(hash.get("time").toString());
		} catch(Exception e) {}
		JSONObject alliances = (JSONObject) hash.get("alliances");
		
		JSONObject blueTeam = (JSONObject) alliances.get("blue");
		JSONObject redTeam = (JSONObject) alliances.get("red");
		m.blueScore = (Long)blueTeam.get("score");
		m.redScore = (Long)redTeam.get("score");
		
		m.blueTeams = new String[3];
		m.redTeams = new String[3];
		
		for(int j = 0; j < 3; j++) {
			m.blueTeams[j] = (String) blueTeam.get(String.valueOf(j));
			m.redTeams[j] = (String) redTeam.get(String.valueOf(j));
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
		} catch(Exception e) {
			
		}
		return m;
	}
	
	
	@SuppressWarnings("rawtypes")
	private Team parseTeam(Object object) {
		Team t = new Team();
		HashMap hash = (HashMap) object;
		
		t.name = (String) hash.get("name");
		t.team_number = (Long) hash.get("team_number");
		t.website = (String) hash.get("website");
		t.locality = (String) hash.get("locality");
		t.region = (String) hash.get("region");
		t.country_name = (String) hash.get("country_name");
		t.location = (String) hash.get("location");
		t.key = (String) hash.get("key");
		t.nickname = (String) hash.get("nickname");
		t.rookie_year = (Long) hash.get("rookie_year");
		t.motto = (String) hash.get("motto");
		
		return t;
	}
	
	
	@SuppressWarnings("rawtypes")
	private Award[] getAwards(String key, int year) {
		JSONArray awards = (JSONArray) doRequest(Constants.URL + "event/"+year+key+"/awards", Constants.APPID);
		
		Award[] toGet = new Award[awards.size()];
		
		for(int i = 0; i < toGet.length; i++) {
			Award m = new Award();
			HashMap hash = (HashMap) awards.get(i);
			m.name = (String) hash.get("name");
			m.award_type = (Long) hash.get("award_type");
			m.event_key = (String) hash.get("event_key");
			m.year = (Long) hash.get("year");
			
			try {
				JSONArray recipient_list = (JSONArray) hash.get("recipient_list");
				m.recipient_list = new Recipient[recipient_list.size()];
				for (int j = 0; j < recipient_list.size(); j++) {
					JSONObject obj = (JSONObject) recipient_list.get(j);

					m.recipient_list[j].team_number = (Long) obj.get("team_number");
					m.recipient_list[j].awardee = (String) obj.get("awardee");
				}
			} catch (Exception e) {
			}
			toGet[i] = m;
		}
		
		return toGet;
	}
	
	private Object doRequest(String targetURL, String appID) {
		HttpURLConnection connection = null;

		try {
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "TBA-API");
			connection.setRequestProperty("X-TBA-App-Id", appID);

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return parser.parse(response.toString());
		} catch (FileNotFoundException e) {
			return null;
		} catch(Exception e) {
			System.err.println("Data request failed. Check your connection / verify correct data key. If the issue persists, contact the developer");
			e.printStackTrace();
			return null;
		} finally {
			if(connection != null) connection.disconnect();
		}
	}
}
