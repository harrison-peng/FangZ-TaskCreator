package com.server.project.road;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.server.project.api.GoogleMapApiKey;
import com.server.project.api.Road;

public class RoadLengthGetter {
	public Road getRoadLength(String address) throws Exception {
		Road road = new Road();
		road.setAddress(address);
		/**
		 * find start address
		 */
		System.out.println("start find start address");
		for (int j = 1; j < 10; j++) {
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + j + "號&key="
					+ GoogleMapApiKey.getKey());
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("user-agent", "Chrome/7.0.517.44");

			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));

			// parse the HTML
			String retVal = "";
			String line = null;
			while ((line = br.readLine()) != null) {
				retVal = retVal + line + "\n";
			}
			Document doc = Jsoup.parse(retVal);

			// find geometry coordinate
			String text = doc.text();
			int index = text.indexOf("formatted_address");
			String checkVal = text.substring(index + 26, index + 27);
			if (checkVal.equals(String.valueOf(j))) {
				road.setStartNumber(j);
				break;
			} else if (checkVal.equals("You")) {
				j = j - 1;
			}
			TimeUnit.MILLISECONDS.sleep(100);
		}

		/**
		 * find end address
		 */
		System.out.println("start find end address");
		for (int i = 700; i > 0; i--) {
			if (i % 10 == 0) {
				System.out.println("count end address No." + i);
			}
			// connect to google map api
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
					+ GoogleMapApiKey.getKey());
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("user-agent", "Chrome/7.0.517.44");
			conn.setRequestProperty("Content-Language", "zh-tw");
			conn.setRequestProperty("Accept-Charset", "UTF-8");

			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));

			// parse the HTML
			String retVal = "";
			String line = null;
			while ((line = br.readLine()) != null) {
				retVal = retVal + line + "\n";
			}
			Document doc = Jsoup.parse(retVal);

			// find geometry coordinate
			String text = doc.text();
			int startIndex = text.indexOf("formatted_address");
			startIndex = startIndex + 26;
			int endIndex = text.indexOf(",", startIndex);
			String checkVal = text.substring(startIndex, endIndex);
			if (checkVal.equals(String.valueOf(i))) {
				road.setEndNumber(i);
				break;
			} else if (checkVal.equals("You")) {
				i = i + 1;
			}
			TimeUnit.MILLISECONDS.sleep(100);
		}

		return road;
	}

	public Road getLaneLength(String address) throws Exception {
		Road road = new Road();
		road.setAddress(address);

		// find start address
		for (int i = 1; i < 10; i++) {
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
					+ GoogleMapApiKey.getKey());
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("user-agent", "Chrome/7.0.517.44");

			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));

			// parse the HTML
			String retVal = "";
			String line = null;
			while ((line = br.readLine()) != null) {
				retVal = retVal + line + "\n";
			}
			Document doc = Jsoup.parse(retVal);
			String text = doc.text();
			int startIndex = text.indexOf("formatted_address");
			startIndex = startIndex + 26;
			int endIndex = text.indexOf(",", startIndex);
			String checkVal = text.substring(startIndex, endIndex);
			if (checkVal.equals(String.valueOf(i))) {
				startIndex = text.indexOf("long_name");
				startIndex = text.indexOf("long_name", startIndex + 1);
				startIndex = startIndex + 14;
				endIndex = text.indexOf(" ", startIndex);
				checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals("Lane")) {
					road.setStartNumber(i);
					break;
				} else if (checkVal.contains("You")) {
					i = i - 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}

		// find end address
		for (int i = 200; i > 10; i--) {
			if (i % 10 == 0) {
				System.out.println("count end lane No." + i);
			}
			TimeUnit.MILLISECONDS.sleep(100);
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
					+ GoogleMapApiKey.getKey());
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("user-agent", "Chrome/7.0.517.44");

			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));

			// parse the HTML
			String retVal = "";
			String line = null;
			while ((line = br.readLine()) != null) {
				retVal = retVal + line + "\n";
			}
			Document doc = Jsoup.parse(retVal);
			String text = doc.text();
			int startIndex = text.indexOf("formatted_address");
			startIndex = startIndex + 26;
			int endIndex = text.indexOf(",", startIndex);
			String checkVal = text.substring(startIndex, endIndex);
			if (checkVal.equals(String.valueOf(i))) {
				startIndex = text.indexOf("long_name");
				startIndex = text.indexOf("long_name", startIndex + 1);
				startIndex = startIndex + 14;
				endIndex = text.indexOf(" ", startIndex);
				checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals("Lane")) {
					road.setEndNumber(i);
					break;
				} else if (checkVal.contains("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}
		return road;
	}
}
