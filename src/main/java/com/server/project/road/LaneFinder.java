package com.server.project.road;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.server.project.api.GoogleMapApiKey;

public class LaneFinder {
	public List<String> findLane(String address, int startNum, int endNum) throws Exception {
		List<String> laneList = new ArrayList<String>();
		for (int i = startNum; i < endNum; i++) {
			try {
				if (i % 10 == 0) {
					System.out.println("count number to find lane No." + i);
				}
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "巷&key="
						+ GoogleMapApiKey.getKey());
				if (i % 100 == 0) {
					System.out.println(url);
				}
				URLConnection conn = url.openConnection();
				conn.setRequestProperty("user-agent", "Chrome/7.0.517.44");
				InputStream in = conn.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));

				String retVal = "";
				String line = null;
				while ((line = br.readLine()) != null) {
					retVal = retVal + line + "\n";
				}
				Document doc = Jsoup.parse(retVal);

				String text = doc.text();
				int index = text.indexOf("long_name");
				index = index + 19;
				int endIndex = text.indexOf(",", index);
				String checkLane = text.substring(index, endIndex);
				if (checkLane.equals(String.valueOf(i))) {
					laneList.add(address + i + "巷");
				} else if (checkLane.contains("You")) {
					i = i - 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (IOException e) {
				i = i - 1;
			}
		}
		return laneList;
	}
}
