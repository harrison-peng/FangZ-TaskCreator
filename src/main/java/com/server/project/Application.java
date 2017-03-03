package com.server.project;

import com.server.project.createtask.HouseToTask;

public class Application {
	public static void main(String[] args) throws Exception {
		HouseToTask toTask = new HouseToTask();
		toTask.toTask(10);
	}
}
