package com.server.project.createtask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import com.server.project.api.TaskInfomation;

public class HouseToTask {
	public static void main(String[] args) throws Exception {
		HouseToTask htu = new HouseToTask();
		htu.toTask(2);

		// boolean reVal = htu.checkTask(2);
		// System.out.println(reVal);
	}

	public void toTask(int id) throws Exception {
		TaskChecker taskChecker = new TaskChecker();

		// connect DB
		Class.forName("org.postgresql.Driver").newInstance();
		String url = "jdbc:postgresql://140.119.19.33:5432/project";
		Connection con = DriverManager.getConnection(url, "postgres", "093622");
		Statement selectST = con.createStatement();
		Statement updateST = con.createStatement();

		TaskCreator taskCreator = new TaskCreator();
		System.out.println("start from id=" + id);

		boolean ifCreateNewTask = taskChecker.checkTask(id);
		if (ifCreateNewTask) {
			String selectSQL = "select * from house where id=" + id;
			ResultSet selectRS = selectST.executeQuery(selectSQL);
			while (selectRS.next()) {
				/**
				 * task time classify : 0-> morning; 1-> afternoon; 2-> night;
				 * 3-> midnight;
				 */
				List<TaskInfomation> taskInfoList = taskCreator.createTask(selectRS.getString("address"));
				for (int i = 0; i <= 3; i++) {
					int countRoad = 0;
					for (TaskInfomation taskInfo : taskInfoList) {
						String startGeometry = "ST_GeomFromText('POINT(" + taskInfo.getStartLng() + " "
								+ taskInfo.getStartLat() + ")', 4326)";
						String endGeometry = "ST_GeomFromText('POINT(" + taskInfo.getEndLng() + " "
								+ taskInfo.getEndLat() + ")', 4326)";
						String taskTime;
						if (i == 0) {
							System.out.println("start insert morning task");
							taskTime = "morning";
						} else if (i == 1) {
							System.out.println("start insert afternoon task");
							taskTime = "afternoon";
						} else if (i == 2) {
							System.out.println("start insert night task");
							taskTime = "night";
						} else {
							System.out.println("start insert midnight task");
							taskTime = "midnight";
						}

						String title = selectRS.getString("address");
						if (taskInfo.getStartAddress().contains("巷")) {
							int toLaneIndex = taskInfo.getStartAddress().indexOf("巷");
							String lane = taskInfo.getStartAddress().substring(0, toLaneIndex + 1);
							title = lane + "_" + taskTime;
						} else {
							countRoad += 1;
							title = selectRS.getString("address") + "(" + countRoad + ")_" + taskTime;
						}

						String insertSQL = "insert into task (title, address, start_geometry, end_geometry, time, distance, duration, start_address, end_address) values ('"
								+ title + "', '" + selectRS.getString("address") + "', " + startGeometry + ", "
								+ endGeometry + ", '" + taskTime + "', '" + taskInfo.getDistance() + "', '"
								+ taskInfo.getDuration() + "', '" + taskInfo.getStartAddress() + "', '"
								+ taskInfo.getEndAddress() + "');";
						System.out.println(insertSQL);

						updateST.executeUpdate(insertSQL);
					}
				}
			}
			String updateStartSQL = "update house set endNote='' where id=" + id + ";";
			String updateEndSQL = "update house set endNote='here' where id=" + (id + 1) + ";";
			System.out.println("finish create task");
			selectST.executeUpdate(updateStartSQL + updateEndSQL);
		} else {
			String updateStartSQL = "update house set endNote='' where id=" + (id - 1) + ";";
			String updateEndSQL = "update house set endNote='here' where id=" + id + ";";
			System.out.println("no need to crete task");
			selectST.executeUpdate(updateStartSQL + updateEndSQL);
		}

		selectST.close();
		updateST.close();
		con.close();
	}
}
