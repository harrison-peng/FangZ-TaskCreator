package com.server.project.createtask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.server.project.api.TaskInfomation;
import com.server.project.road.LaneFinder;
import com.server.project.road.RoadCutter;
import com.server.project.road.RoadLengthGetter;
import com.server.project.road.RoadSaver;
import com.server.project.api.GoogleMapApiKey;
import com.server.project.api.Road;

public class TaskCreator {
	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		TaskCreator tc = new TaskCreator();
		List<TaskInfomation> list = tc.createTask("台北市忠孝東路一段");
		System.out.println(gson.toJson(list));

		// List<String> laneList = tc.findLane("台北市忠孝東路一段", 48, 71);
		// System.out.println(laneList);

		// List<String> laneAddress = tc.getLaneAddress("台北市忠孝東路一段49巷");
		// System.out.println(laneAddress);
	}

	public List<TaskInfomation> createTask(String address) throws Exception {
		Gson gson = new Gson();
		RoadCutter roadCutter = new RoadCutter();
		RoadLengthGetter roadLengthGetter = new RoadLengthGetter();
		LaneFinder laneFinder = new LaneFinder();
		List<TaskInfomation> taskInfoList = new ArrayList<TaskInfomation>();

		/**
		 * get road length
		 */
		Road roadPath = roadLengthGetter.getRoadLength(address);

		/**
		 * cut road -> No.100 for one group
		 */
		System.out.println("start cut road");
		List<String> roadList = roadCutter.cutRoad(roadPath.getAddress(), roadPath.getStartNumber(),
				roadPath.getEndNumber());
		System.out.println(roadList);
		for (int i = 0; i < roadList.size() - 1; i++) {
			TaskInfomation taskInfoByRoad = createTaskInRoad(roadList.get(i), roadList.get(i + 1));
			taskInfoList.add(taskInfoByRoad);
		}

		/**
		 * find how many lane in this road
		 */
		System.out.println("start find lane");
		List<String> laneList = laneFinder.findLane(roadPath.getAddress(), roadPath.getStartNumber(),
				roadPath.getEndNumber());
		System.out.println(laneList);

		List<List<String>> laneInfoList = new ArrayList<List<String>>();
		if (!laneList.isEmpty()) {
			System.out.println("start get lane task");
			for (String lane : laneList) {
				List<String> laneInfo = new ArrayList<String>();
				Road landPath = roadLengthGetter.getLaneLength(lane);
				System.out.println(gson.toJson(landPath));
				if (landPath.getStartNumber() != 0 && landPath.getEndNumber() != 0) {
					String startAddr = landPath.getAddress() + landPath.getStartNumber() + "號";
					laneInfo.add(startAddr);
					String endAddr = landPath.getAddress() + landPath.getEndNumber() + "號";
					laneInfo.add(endAddr);
					laneInfoList.add(laneInfo);
					TaskInfomation taskInfoByLane = createTaskInRoad(startAddr, endAddr);
					taskInfoList.add(taskInfoByLane);
				}
			}
		} else {
			System.out.println("There is no any lane on this road!");
		}

		/**
		 * save road info to database
		 */
		RoadSaver roadSaver = new RoadSaver();
		roadSaver.saveRoad(address, roadPath.getStartNumber(), roadPath.getEndNumber(), laneInfoList);
		System.out.println("save this road info into database");
		System.out.println(gson.toJson(taskInfoList));

		return taskInfoList;
	}

	private TaskInfomation createTaskInRoad(String startAddress, String endAddress) throws Exception {
		TaskInfomation taskInfo = new TaskInfomation();
		Gson gson = new Gson();

		String apiURL = "https://maps.googleapis.com/maps/api/directions/json?origin=" + startAddress + "&destination="
				+ endAddress + "&mode=walking&key=" + GoogleMapApiKey.getKey();
//		System.out.println(apiURL);
		URL url = new URL(apiURL);
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

		// set address
		taskInfo.setStartAddress(startAddress);
		taskInfo.setEndAddress(endAddress);

		// get start geometry
		int geoIndex = text.indexOf("start_location");
		int setStartIndex = text.indexOf("{", geoIndex);
		int setEndIndex = text.indexOf("}", geoIndex) + 1;
		String geometry = text.substring(setStartIndex, setEndIndex);
		geometry = geometry.replace("lat", "startLat").replace("lng", "startLng");

		TaskInfomation startCoordinate = gson.fromJson(geometry, TaskInfomation.class);
		taskInfo.setStartLat(startCoordinate.getStartLat());
		taskInfo.setStartLng(startCoordinate.getStartLng());

		// get end geometry
		geoIndex = text.indexOf("end_location");
		setStartIndex = text.indexOf("{", geoIndex);
		setEndIndex = text.indexOf("}", geoIndex) + 1;
		geometry = text.substring(setStartIndex, setEndIndex);
		geometry = geometry.replace("lat", "endLat").replace("lng", "endLng");

		TaskInfomation endCoordinate = gson.fromJson(geometry, TaskInfomation.class);
		taskInfo.setEndLat(endCoordinate.getEndLat());
		taskInfo.setEndLng(endCoordinate.getEndLng());

		// get distance
		int disStartIndex = text.indexOf("distance");
		disStartIndex = text.indexOf("text", disStartIndex);
		int disEndIndex = text.indexOf(",", disStartIndex);
		String distance = text.substring(disStartIndex + 9, disEndIndex - 1);
		taskInfo.setDistance(distance);

		// get duration
		int durStartIndex = text.indexOf("duration");
		durStartIndex = text.indexOf("text", durStartIndex);
		int durEndIndex = text.indexOf(",", durStartIndex);
		String duration = text.substring(durStartIndex + 9, durEndIndex - 1);
		taskInfo.setDuration(duration);

		return taskInfo;
	}
}
