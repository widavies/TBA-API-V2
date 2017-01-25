package com.cpjd.requests;

import com.cpjd.main.Constants;
import com.cpjd.models.Match;
import com.cpjd.utils.IO;
import com.cpjd.utils.Parser;

public class MatchRequest extends Parser {
	/**
	 * Returns a single <b>Match</b> model
	 * 
	 * @param year The event year (example: 2016)
	 * @param eventKey The event key (example: casd)
	 * @param matchKey The match key (example: f1m1)
	 * @return A <b>Match</b> model
	 */
	public Match getMatch(int year, String eventKey, String matchKey) {
		return parseMatch(IO.doRequest(Constants.URL + "match/" + year + eventKey + "_" + matchKey, Constants.APPID));
	}


}
