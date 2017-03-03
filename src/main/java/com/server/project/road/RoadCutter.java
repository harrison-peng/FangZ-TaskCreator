package com.server.project.road;

import java.io.BufferedReader;
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

public class RoadCutter {
	/**
	 * this method will cut 100 number address for one group
	 */
	public List<String> cutRoad(String address, int startAddressNum, int endAddressNum) throws Exception {
		List<String> addressList = new ArrayList<String>();

		if (endAddressNum <= 100) {
			addressList.add(address + startAddressNum + "號");
			addressList.add(address + endAddressNum + "號");
		} else if (100 < endAddressNum && endAddressNum <= 200) {
			int addressNodeNum = 0;
			addressList.add(address + startAddressNum + "號");

			// for No.100
			for (int i = 101; i < 110; i++) {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
						+ GoogleMapApiKey.getKey());
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			addressList.add(address + endAddressNum + "號");
		} else if (200 < endAddressNum && endAddressNum <= 300) {
			int addressNodeNum = 0;
			addressList.add(address + startAddressNum + "號");

			// for No.100
			for (int i = 101; i < 110; i++) {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
						+ GoogleMapApiKey.getKey());
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.200
			for (int i = 201; i < 210; i++) {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
						+ GoogleMapApiKey.getKey());
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			addressList.add(address + endAddressNum + "號");
		} else if (300 < endAddressNum && endAddressNum <= 400) {
			int addressNodeNum = 0;
			addressList.add(address + startAddressNum + "號");

			// for No.100
			for (int i = 101; i < 110; i++) {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
						+ GoogleMapApiKey.getKey());
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.200
			for (int i = 201; i < 210; i++) {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
						+ GoogleMapApiKey.getKey());
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.300
			for (int i = 301; i < 310; i++) {
				URL url = new URL(
						"https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&sensor=false");
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			addressList.add(address + endAddressNum + "號");
		} else if (400 < endAddressNum && endAddressNum <= 500) {
			int addressNodeNum = 0;
			addressList.add(address + startAddressNum + "號");

			// for No.100
			for (int i = 101; i < 110; i++) {
				URL url = new URL(
						"https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&sensor=false");
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.200
			for (int i = 201; i < 210; i++) {
				URL url = new URL(
						"https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&sensor=false");
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.300
			for (int i = 301; i < 310; i++) {
				URL url = new URL(
						"https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&sensor=false");
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.400
			for (int i = 401; i < 410; i++) {
				URL url = new URL(
						"https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&sensor=false");
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			addressList.add(address + endAddressNum + "號");
		} else if (500 < endAddressNum && endAddressNum <= 600) {
			int addressNodeNum = 0;
			addressList.add(address + startAddressNum + "號");

			// for No.100
			for (int i = 101; i < 110; i++) {
				URL url = new URL(
						"https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&sensor=false");
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.200
			for (int i = 201; i < 210; i++) {
				URL url = new URL(
						"https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&sensor=false");
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.300
			for (int i = 301; i < 310; i++) {
				URL url = new URL(
						"https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&sensor=false");
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.400
			for (int i = 401; i < 410; i++) {
				URL url = new URL(
						"https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&sensor=false");
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.500
			for (int i = 501; i < 510; i++) {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
						+ GoogleMapApiKey.getKey());
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			addressList.add(address + endAddressNum + "號");
		} else {
			int addressNodeNum = 0;
			addressList.add(address + startAddressNum + "號");

			// for No.100
			for (int i = 101; i < 110; i++) {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
						+ GoogleMapApiKey.getKey());
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.200
			for (int i = 201; i < 210; i++) {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
						+ GoogleMapApiKey.getKey());
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.300
			for (int i = 301; i < 310; i++) {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
						+ GoogleMapApiKey.getKey());
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.400
			for (int i = 401; i < 410; i++) {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
						+ GoogleMapApiKey.getKey());
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.500
			for (int i = 501; i < 510; i++) {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
						+ GoogleMapApiKey.getKey());
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			// for No.600
			for (int i = 601; i < 610; i++) {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + i + "號&key="
						+ GoogleMapApiKey.getKey());
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
				int startIndex = text.indexOf("formatted_address");
				startIndex = startIndex + 26;
				int endIndex = text.indexOf(",", startIndex);
				String checkVal = text.substring(startIndex, endIndex);
				if (checkVal.equals(String.valueOf(i))) {
					addressNodeNum = i;
					addressList.add(address + addressNodeNum + "號");
					break;
				} else if (checkVal.equals("You")) {
					i = i + 1;
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

			addressList.add(address + endAddressNum + "號");
		}
		return addressList;
	}
}
