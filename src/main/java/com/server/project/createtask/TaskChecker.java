package com.server.project.createtask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskChecker {
	public int checkWhereTaskStart() throws Exception {
		int insertId;
		// connect DB
		Class.forName("org.postgresql.Driver").newInstance();
		String url = "jdbc:postgresql://140.119.19.33:5432/project";
		Connection con = DriverManager.getConnection(url, "postgres", "093622"); // 帳號密碼
		Statement selectST = con.createStatement();
		String checkSQL = "select * from house where endNote='here';";
		ResultSet checkRS = selectST.executeQuery(checkSQL);

		if (checkRS.next()) {
			insertId = Integer.valueOf(checkRS.getString("id")) + 1;
		} else {
			insertId = 1;
		}
		selectST.close();
		checkRS.close();
		con.close();

		return insertId;
	}

	/**
	 * check if the road need to create new task
	 **/
	public boolean checkTask(int id) throws Exception {
		Boolean reVal = null;
		List<String> addressList = new ArrayList<String>();
		String currentAddress = null;

		if (id == 1) {
			reVal = true;
		} else {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://140.119.19.33:5432/project";
			Connection con = DriverManager.getConnection(url, "postgres", "093622"); // 帳號密碼
			Statement selectST = con.createStatement();

			// get address list
			for (int i = 1; i < id; i++) {
				String getAddressSQL = "select address from house where id=" + i + ";";
				ResultSet selectRS = selectST.executeQuery(getAddressSQL);
				while (selectRS.next()) {
					addressList.add(selectRS.getString("address"));
				}
			}

			// get current address
			String getAddressSQL = "select address from house where id=" + id + ";";
			ResultSet selectRS = selectST.executeQuery(getAddressSQL);
			while (selectRS.next()) {
				currentAddress = selectRS.getString("address");
			}

			// compare addres
			for (String address : addressList) {
				if (currentAddress.equals(address)) {
					reVal = false;
				} else {
					reVal = true;
				}
			}
			selectRS.close();
			selectST.close();
			con.close();
		}
		return reVal;
	}
}
