package com.cpjd.requests;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cpjd.main.Constants;
import com.cpjd.models.Event;
import com.cpjd.models.Team;
import com.cpjd.models.other.District;
import com.cpjd.models.other.DistrictRanking;
import com.cpjd.models.other.EventPoint;
import com.cpjd.utils.IO;
import com.cpjd.utils.Parser;

public class DistrictRequest extends Parser {
	/**
	 * Gets a list of all the existent districts within the specified year.
	 * @param year A year (example: 2017)
	 * @return An array of the <b>District</b> model
	 */
	public District[] getDistricts(int year) {
		JSONArray districts = (JSONArray) IO.doRequest(Constants.URL + "districts/"+year, Constants.APPID);
		District[] toGet = new District[districts.size()];
		for(int i = 0; i < toGet.length; i++) {
			District d = new District();
			JSONObject object = (JSONObject) districts.get(i);
			try { d.name = (String) object.get("name"); } catch(Exception e) {}
			try { d.key = (String) object.get("key"); } catch(Exception e) {}
			toGet[i] = d;
		}
		return toGet;
	}
	/**
	 * Gets a list of all events within the specified district.
	 * @param districtShort The district's short code (example: ne)
	 * @param year The district's year (example: 2017)
	 * @return An array of the <b>Event</b> model
	 */
	public Event[] getDistrictEvents(String districtShort, int year) {
		JSONArray events = (JSONArray) IO.doRequest(Constants.URL + "district/"+districtShort+"/"+year+"/events", Constants.APPID);
		Event[] toGet = new Event[events.size()];
		for(int i = 0; i < toGet.length; i++) {
			toGet[i] = parseEvent(events.get(i));
		}
		return toGet;
	}
	/**
	 * Gets a list of the rankings of teams within the district.
	 * @param districtShort The district's short code (example: ne)
	 * @param year The district's year (example: 2017)
	 * @return An array of the <b>DistrictRanking</b> model
	 */
	@SuppressWarnings("unchecked")
	public DistrictRanking[] getDistrictRankings(String districtShort, int year) {
		JSONArray rankings = (JSONArray) IO.doRequest(Constants.URL + "district/"+districtShort+"/"+year+"/rankings", Constants.APPID);
		DistrictRanking[] toGet = new DistrictRanking[rankings.size()];
		for(int i = 0; i < toGet.length; i++) {
			DistrictRanking r = new DistrictRanking();
			JSONObject object = (JSONObject) rankings.get(i);

			r.point_total = (Long) object.get("point_total");
			r.team_key = (String) object.get("team_key");
			r.rank = (Long) object.get("rank");
			r.rookie_bonus = (Long) object.get("rookie_bonus");
			
			JSONObject obj = (JSONObject) object.get("event_points");
			Iterator<String> itr = obj.keySet().iterator();
			r.points = new EventPoint[obj.keySet().size()];
			for (int j = 0; itr.hasNext(); j++) {
				String key = itr.next();
				JSONObject pb = (JSONObject) obj.get(key);
				EventPoint p = new EventPoint();
				p.alliance_points = (long) pb.get("alliance_points");
				p.award_points = (long) pb.get("award_points");
				p.elim_points = (long) pb.get("elim_points");
				p.district_cmp = (boolean) pb.get("district_cmp");
				p.total = (long) pb.get("total");
				p.qual_points = (long) pb.get("qual_points");
				r.points[j] = p;
			}
			toGet[i] = r;
		}
		return toGet;
	}
	
	/**
	 * Gets a list of all the teams within the specified district.
	 * @param districtShort The district's short code (example: ne)
	 * @param year  The district's year (example: 2017)
	 * @return A array of the <b>Team</b> model
	 */
	public Team[] getDistrictTeams(String districtShort, int year) {
		JSONArray teams = (JSONArray) IO.doRequest(Constants.URL + "district/"+districtShort+"/"+year+"/teams", Constants.APPID);
		Team[] toGet = new Team[teams.size()];
		for(int i = 0; i < toGet.length; i++) {
			toGet[i] = parseTeam(teams.get(i));
		}
		return toGet;
	}
	
}
