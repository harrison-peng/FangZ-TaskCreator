package com.server.project.road;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.server.project.api.GoogleMapApiKey;
import com.server.project.api.Road;

public class RoadLengthGetter {
	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		RoadLengthGetter roadLengthGetter = new RoadLengthGetter();

		// Road road = roadLengthGetter.getRoadLength("台北市文山區秀明路二段");
		// System.out.println(gson.toJson(road));

		Road lane = roadLengthGetter.getLaneLength("台中市西屯區福順路225巷");
		System.out.println(gson.toJson(lane));
	}

	public Road getRoadLength(String address) throws Exception {
		Road road = new Road();
		road.setAddress(address);
		/**
		 * find start address
		 */
		System.out.println("start find start address");
		for (int j = 1; j < 30; j++) {
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
		boolean ifContinue = true;
		int roadEndNum = 1000;
		while (ifContinue) {
			switch (roadEndNum) {
			case 1000:
				System.out.println("start from 1000");
				ifContinue = checkEndAddress(address, 1000);
				if (ifContinue == true) {
					roadEndNum = 900;
				}
				break;

			case 900:
				System.out.println("start from 900");
				ifContinue = checkEndAddress(address, 900);
				if (ifContinue == true) {
					roadEndNum = 800;
				}
				break;

			case 800:
				System.out.println("start from 800");
				ifContinue = checkEndAddress(address, 800);
				if (ifContinue == true) {
					roadEndNum = 700;
				}
				break;

			case 700:
				System.out.println("start from 700");
				ifContinue = checkEndAddress(address, 700);
				if (ifContinue == true) {
					roadEndNum = 600;
				}
				break;

			case 600:
				System.out.println("start from 600");
				ifContinue = checkEndAddress(address, 600);
				if (ifContinue == true) {
					roadEndNum = 500;
				}
				break;

			case 500:
				System.out.println("start from 500");
				ifContinue = checkEndAddress(address, 500);
				if (ifContinue == true) {
					roadEndNum = 400;
				}
				break;

			case 400:
				System.out.println("start from 400");
				ifContinue = checkEndAddress(address, 400);
				if (ifContinue == true) {
					roadEndNum = 300;
				}
				break;

			case 300:
				System.out.println("start from 300");
				ifContinue = checkEndAddress(address, 300);
				if (ifContinue == true) {
					roadEndNum = 200;
				}
				break;

			case 200:
				System.out.println("start from 200");
				ifContinue = checkEndAddress(address, 200);
				if (ifContinue == true) {
					roadEndNum = 100;
				}
				break;

			case 100:
				System.out.println("start from 100");
				ifContinue = checkEndAddress(address, 100);
				break;

			default:
				System.out.println("error");
				break;
			}
		}

		roadEndNum = roadEndNum + 100;
		for (int i = roadEndNum; i > 0; i--) {
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

		boolean ifContinue = true;
		int roadEndNum = 250;
		while (ifContinue) {
			switch (roadEndNum) {
			case 250:
				System.out.println("start from 250");
				ifContinue = checkEndLaneAddress(address, 250);
				if (ifContinue == true) {
					roadEndNum = 200;
				}
				break;

			case 200:
				System.out.println("start from 200");
				ifContinue = checkEndLaneAddress(address, 200);
				if (ifContinue == true) {
					roadEndNum = 150;
				}
				break;

			case 150:
				System.out.println("start from 150");
				ifContinue = checkEndLaneAddress(address, 150);
				if (ifContinue == true) {
					roadEndNum = 100;
				}
				break;

			case 100:
				System.out.println("start from 100");
				ifContinue = checkEndLaneAddress(address, 100);
				if (ifContinue == true) {
					roadEndNum = 50;
				}
				break;

			case 50:
				System.out.println("start from 50");				
				roadEndNum = 0;
				ifContinue = false;			
				break;
			default:
				System.out.println("error");
				break;
			}
		}

		// find end address
		roadEndNum = roadEndNum + 50;
		for (int i = roadEndNum; i > 10; i--) {
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

	private boolean checkEndAddress(String address, int startNum) throws Exception {
		for (int i = startNum; i > (startNum - 10); i--) {
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
				System.out.println(
						"find end address! end address is between No." + startNum + " and No." + (startNum + 100));
				return false;
			} else if (checkVal.equals("You")) {
				i = i + 1;
			}
			TimeUnit.MILLISECONDS.sleep(100);
		}
		return true;
	}

	private boolean checkEndLaneAddress(String address, int startNum) throws Exception {
		for (int i = startNum; i > (startNum - 10); i--) {
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
					System.out.println(
							"find end address! end address is between No." + startNum + " and No." + (startNum + 50));
					return false;
				} else if (checkVal.contains("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}

		return true;
	}
}
