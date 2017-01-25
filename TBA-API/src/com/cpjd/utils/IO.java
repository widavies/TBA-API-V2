package com.cpjd.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.parser.JSONParser;

/**
 * Pulls raw data from the server. Nothing to see here.
 * @author Will Davies
 *
 */
public class IO {
	private static final JSONParser parser = new JSONParser();
	
	public static Object doRequest(String targetURL, String appID) {
		HttpURLConnection connection = null;

		try {
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "TBA-API");
			connection.setRequestProperty("X-TBA-App-Id", appID);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("charset", "utf-8");
			connection.setUseCaches(false);

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
			try {
				System.err.println("DATA REQUEST FAILED: " + e.getMessage() + " RESPONSE CODE: " + connection.getResponseCode()
						+ connection.getResponseMessage());
			} catch (IOException e1) {
				//e1.printStackTrace();
			}
			//e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.err.println("Data request failed. Check your connection / verify correct data key. If the issue persists, contact the developer");
			//e.printStackTrace();
			return null;
		} finally {
			if(connection != null) connection.disconnect();
			
		}
	}
}
