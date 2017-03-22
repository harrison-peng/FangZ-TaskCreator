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
	public static void main(String[] args) throws Exception {
		RoadCutter roadCutter = new RoadCutter();
		List<String> aString = roadCutter.cutRoad("台北市中正區博愛路", 1, 501);
		System.out.println(aString);
	}

	/**
	 * this method will cut 100 number address for one group
	 */
	public List<String> cutRoad(String address, int startAddressNum, int endAddressNum) throws Exception {
		List<String> addressList = new ArrayList<String>();

		if (endAddressNum <= 100) {
			addressList.add(address + startAddressNum + "號");
			addressList.add(address + endAddressNum + "號");
		} else if (100 < endAddressNum && endAddressNum <= 200) {			
			addressList.add(address + startAddressNum + "號");

			// for No.100
			String insertAddress = getInsertAddress(address, 100);
			addressList.add(insertAddress);
		} else if (200 < endAddressNum && endAddressNum <= 300) {
			addressList.add(address + startAddressNum + "號");

			// for No.100
			String insertAddress = getInsertAddress(address, 100);
			addressList.add(insertAddress);

			// for No.200
			insertAddress = getInsertAddress(address, 200);
			addressList.add(insertAddress);

			addressList.add(address + endAddressNum + "號");
		} else if (300 < endAddressNum && endAddressNum <= 400) {
			addressList.add(address + startAddressNum + "號");

			// for No.100
			String insertAddress = getInsertAddress(address, 100);
			addressList.add(insertAddress);

			// for No.200
			insertAddress = getInsertAddress(address, 200);
			addressList.add(insertAddress);

			// for No.300
			insertAddress = getInsertAddress(address, 300);
			addressList.add(insertAddress);

			addressList.add(address + endAddressNum + "號");
		} else if (400 < endAddressNum && endAddressNum <= 500) {
			addressList.add(address + startAddressNum + "號");

			// for No.100
			String insertAddress = getInsertAddress(address, 100);
			addressList.add(insertAddress);

			// for No.200
			insertAddress = getInsertAddress(address, 200);
			addressList.add(insertAddress);

			// for No.300
			insertAddress = getInsertAddress(address, 300);
			addressList.add(insertAddress);

			// for No.400
			insertAddress = getInsertAddress(address, 400);
			addressList.add(insertAddress);

			addressList.add(address + endAddressNum + "號");
		} else if (500 < endAddressNum && endAddressNum <= 600) {
			addressList.add(address + startAddressNum + "號");

			// for No.100
			String insertAddress = getInsertAddress(address, 100);
			addressList.add(insertAddress);

			// for No.200
			insertAddress = getInsertAddress(address, 200);
			addressList.add(insertAddress);

			// for No.300
			insertAddress = getInsertAddress(address, 300);
			addressList.add(insertAddress);

			// for No.400
			insertAddress = getInsertAddress(address, 400);
			addressList.add(insertAddress);

			// for No.500
			insertAddress = getInsertAddress(address, 500);
			addressList.add(insertAddress);

			addressList.add(address + endAddressNum + "號");
		} else if (600 < endAddressNum && endAddressNum <= 700) {
			addressList.add(address + startAddressNum + "號");

			// for No.100
			String insertAddress = getInsertAddress(address, 100);
			addressList.add(insertAddress);

			// for No.200
			insertAddress = getInsertAddress(address, 200);
			addressList.add(insertAddress);

			// for No.300
			insertAddress = getInsertAddress(address, 300);
			addressList.add(insertAddress);

			// for No.400
			insertAddress = getInsertAddress(address, 400);
			addressList.add(insertAddress);

			// for No.500
			insertAddress = getInsertAddress(address, 500);
			addressList.add(insertAddress);

			// for No.600
			insertAddress = getInsertAddress(address, 600);
			addressList.add(insertAddress);

			addressList.add(address + endAddressNum + "號");
		} else if (700 < endAddressNum && endAddressNum <= 800) {
			addressList.add(address + startAddressNum + "號");

			// for No.100
			String insertAddress = getInsertAddress(address, 100);
			addressList.add(insertAddress);

			// for No.200
			insertAddress = getInsertAddress(address, 200);
			addressList.add(insertAddress);

			// for No.300
			insertAddress = getInsertAddress(address, 300);
			addressList.add(insertAddress);

			// for No.400
			insertAddress = getInsertAddress(address, 400);
			addressList.add(insertAddress);

			// for No.500
			insertAddress = getInsertAddress(address, 500);
			addressList.add(insertAddress);

			// for No.600
			insertAddress = getInsertAddress(address, 600);
			addressList.add(insertAddress);

			// for No.700
			insertAddress = getInsertAddress(address, 700);
			addressList.add(insertAddress);

			addressList.add(address + endAddressNum + "號");
		} else if (800 < endAddressNum && endAddressNum <= 900) {
			addressList.add(address + startAddressNum + "號");

			// for No.100
			String insertAddress = getInsertAddress(address, 100);
			addressList.add(insertAddress);

			// for No.200
			insertAddress = getInsertAddress(address, 200);
			addressList.add(insertAddress);

			// for No.300
			insertAddress = getInsertAddress(address, 300);
			addressList.add(insertAddress);

			// for No.400
			insertAddress = getInsertAddress(address, 400);
			addressList.add(insertAddress);

			// for No.500
			insertAddress = getInsertAddress(address, 500);
			addressList.add(insertAddress);

			// for No.600
			insertAddress = getInsertAddress(address, 600);
			addressList.add(insertAddress);

			// for No.700
			insertAddress = getInsertAddress(address, 700);
			addressList.add(insertAddress);

			// for No.800
			insertAddress = getInsertAddress(address, 800);
			addressList.add(insertAddress);

			addressList.add(address + endAddressNum + "號");
		} else if (900 < endAddressNum && endAddressNum <= 1000) {
			addressList.add(address + startAddressNum + "號");

			// for No.100
			String insertAddress = getInsertAddress(address, 100);
			addressList.add(insertAddress);

			// for No.200
			insertAddress = getInsertAddress(address, 200);
			addressList.add(insertAddress);

			// for No.300
			insertAddress = getInsertAddress(address, 300);
			addressList.add(insertAddress);

			// for No.400
			insertAddress = getInsertAddress(address, 400);
			addressList.add(insertAddress);

			// for No.500
			insertAddress = getInsertAddress(address, 500);
			addressList.add(insertAddress);

			// for No.600
			insertAddress = getInsertAddress(address, 600);
			addressList.add(insertAddress);

			// for No.700
			insertAddress = getInsertAddress(address, 700);
			addressList.add(insertAddress);

			// for No.800
			insertAddress = getInsertAddress(address, 800);
			addressList.add(insertAddress);

			// for No.900
			insertAddress = getInsertAddress(address, 900);
			addressList.add(insertAddress);

			addressList.add(address + endAddressNum + "號");
		} else {
			addressList.add(address + startAddressNum + "號");

			// for No.100
			String insertAddress = getInsertAddress(address, 100);
			addressList.add(insertAddress);

			// for No.200
			insertAddress = getInsertAddress(address, 200);
			addressList.add(insertAddress);

			// for No.300
			insertAddress = getInsertAddress(address, 300);
			addressList.add(insertAddress);

			// for No.400
			insertAddress = getInsertAddress(address, 400);
			addressList.add(insertAddress);

			// for No.500
			insertAddress = getInsertAddress(address, 500);
			addressList.add(insertAddress);

			// for No.600
			insertAddress = getInsertAddress(address, 600);
			addressList.add(insertAddress);

			// for No.700
			insertAddress = getInsertAddress(address, 700);
			addressList.add(insertAddress);

			// for No.800
			insertAddress = getInsertAddress(address, 800);
			addressList.add(insertAddress);

			// for No.900
			insertAddress = getInsertAddress(address, 900);
			addressList.add(insertAddress);

			// for No.1000
			insertAddress = getInsertAddress(address, 1000);
			addressList.add(insertAddress);

			addressList.add(address + endAddressNum + "號");
		}
		return addressList;
	}

	private String getInsertAddress(String address, int addressNum) throws Exception {
		String addressInsert = null;
		int addressNodeNum = 0;

		for (int i = (addressNum + 1); i < (addressNum + 10); i++) {
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
				addressInsert = address + addressNodeNum + "號";
				break;
			} else if (checkVal.equals("You")) {
				i = i + 1;
			}
			TimeUnit.MILLISECONDS.sleep(100);
		}

		return addressInsert;
	}
}
