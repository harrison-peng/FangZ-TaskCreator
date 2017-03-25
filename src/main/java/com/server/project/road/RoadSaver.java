package com.server.project.road;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import com.google.gson.Gson;

public class RoadSaver {
	public void saveRoad(String address, int startNum, int endNum, List<List<String>> lane) throws Exception {
		Gson gson = new Gson();
		Class.forName("org.postgresql.Driver").newInstance();
		String url = "jdbc:postgresql://140.119.19.33:5432/project";
		Connection con = DriverManager.getConnection(url, "postgres", "093622");
		Statement insertST = con.createStatement();
		String laneStr = gson.toJson(lane);
		String startAddr = address + startNum + "號";
		String endAddr = address + endNum + "號";
		String insertSQL = "insert into road (name, lane, start_address, end_address) values ('" + address + "', '"
				+ laneStr + "', '" + startAddr + "', '" + endAddr + "');";
		System.out.println(insertSQL);
		insertST.executeUpdate(insertSQL);
		insertST.close();
		con.close();
	}
}
