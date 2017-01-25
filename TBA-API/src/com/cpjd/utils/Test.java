package com.cpjd.utils;

import com.cpjd.models.Award;
import com.cpjd.models.Event;
import com.cpjd.models.Match;
import com.cpjd.models.Media;
import com.cpjd.models.Robot;
import com.cpjd.models.Team;

/**
 * Some testing methods for printing out information from a certain Model to the console.
 * @author Will Davies
 *
 */
public class Test {
	
	public static void printRobot(Robot robot) {
		System.out.println("NEW ROBOT");
		System.out.println(robot.key);
		System.out.println(robot.name);
		System.out.println(robot.team_key);
		System.out.println(robot.year);
		
	}
	
	public static void printMedia(Media media) {
		System.out.println("NEW MEDIA");
		System.out.println(media.type);
		System.out.println(media.foreign_key);
		System.out.println(media.preferred);
		if(media.details == null) return;
		for(int i = 0; i < media.details.length; i++) {
			System.out.println(media.details[i]);
		}
		
	}
	
	public static void printMatch(Match match) {
		System.out.println("NEW MATCHHHHHHHHHHHHH");
		System.out.println(match.key);
		System.out.println(match.comp_level);
		System.out.println(match.set_number);
		System.out.println(match.match_number);
		System.out.println(match.event_key);
		System.out.println(match.time_string);
		System.out.println(match.time);
		System.out.println(match.blueScore);
		System.out.println(match.redScore);
	}
	
	public static void printAward(Award award) {
		System.out.println(award.name);
		System.out.println(award.award_type);
		System.out.println(award.event_key);
		System.out.println(award.year);
		
		if(award.recipient_list == null) return;
		for(int i = 0; i < award.recipient_list.length; i++) {
			try {
			System.out.println(award.recipient_list[i].team_number);
			System.out.println(award.recipient_list[i].awardee);
			} catch(Exception e) {
				//e.printStackTrace();
			}
			
		}
	}
	
	public static void printEvent(Event event) {
		System.out.println("START EVENT");
		System.out.println(event.key);
		System.out.println(event.name);
		System.out.println(event.short_name);
		System.out.println(event.event_code);
		System.out.println(event.event_type_string);
		System.out.println(event.event_type);
		System.out.println(event.event_district_string);
		System.out.println(event.year);
		System.out.println(event.week);
		System.out.println(event.location);
		System.out.println(event.venue_address);
		System.out.println(event.timezone);
		System.out.println(event.website);
		System.out.println(event.official);
		
		if(event.alliances == null) return;
		for(int i = 0; i < event.alliances.length; i++) {
			try {
			for(int j = 0; j < event.alliances[i].picks.length; j++) {
				System.out.println("PICK: "+event.alliances[i].picks[j]);
			}} catch(Exception e) {}
			try {
			for(int j = 0; j < event.alliances[i].declines.length; j++) {
				System.out.println("DECLINE: "+event.alliances[i].declines[j]);
			}
			} catch(Exception e) {}
		}
		
	}
	public static void printTeam(Team team) {
		System.out.println(team.name);
		System.out.println(team.team_number);
		System.out.println(team.website);
		System.out.println(team.locality);
		System.out.println(team.region);
		System.out.println(team.country_name);
		System.out.println(team.location);
		System.out.println(team.key);
		System.out.println(team.nickname);
		System.out.println(team.rookie_year);
		System.out.println(team.motto);
		
		System.out.println(team.rank);
		System.out.println(team.rankingScore);
		System.out.println(team.auto);
		System.out.println(team.scaleOrChallenge);
		System.out.println(team.goals);
		System.out.println(team.defense);
		System.out.println(team.record);
		System.out.println(team.played);
		
		
	}
}
