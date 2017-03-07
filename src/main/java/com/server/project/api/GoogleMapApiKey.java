package com.server.project.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GoogleMapApiKey {
	public static void main(String[] args) throws Exception {
		String key = GoogleMapApiKey.getKey();
		System.out.println(key);
	}

	public static String getKey() throws Exception {
		int id;
		String key = null;
		int times;
		// connect DB
		Class.forName("org.postgresql.Driver").newInstance();
		String url = "jdbc:postgresql://140.119.19.33:5432/project";
		Connection con = DriverManager.getConnection(url, "postgres", "093622");
		Statement selectST = con.createStatement();
		Statement getCountST = con.createStatement();
		Statement updateST = con.createStatement();

		String selectSQL = "select * from googlemap where selected='here';";
		ResultSet selectRS = selectST.executeQuery(selectSQL);
		while (selectRS.next()) {
			key = selectRS.getString("key");
			times = selectRS.getInt("times") + 1;
			id = selectRS.getInt("id");

			if (times >= 3000) {
				int count = 0;
				String getCountSQL = "select count(id) from googlemap;";
				ResultSet getCountRS = getCountST.executeQuery(getCountSQL);
				while (getCountRS.next()) {
					count = getCountRS.getInt("count");
				}
				if (id == count) {
					String changeSQL = "update googlemap set selected='', times =0 where id=" + id + ";";
					updateST.executeUpdate(changeSQL);
					changeSQL = "update googlemap set selected='here', times =0 where id=1;";
					updateST.executeUpdate(changeSQL);
				} else {
					String changeSQL = "update googlemap set selected='', times =0 where id=" + id + ";";
					updateST.executeUpdate(changeSQL);
					changeSQL = "update googlemap set selected='here', times =0 where id=" + (id + 1) + ";";
					updateST.executeUpdate(changeSQL);
				}
				getCountRS.close();
			} else {
				String changeSQL = "update googlemap set times =" + times + " where id=" + id + ";";
				updateST.executeUpdate(changeSQL);
			}
		}
		con.close();
		selectST.close();
		selectRS.close();
		getCountST.close();
		updateST.close();

		return key;
	}

}
