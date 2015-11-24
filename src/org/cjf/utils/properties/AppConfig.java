package org.cjf.utils.properties;

public class AppConfig {
	private static String userHeadImagePath = "";

	public static String getUserHeadImagePath() {
		return userHeadImagePath;
	}

	public static void setUserHeadImagePath(String userHeadImagePath) {
		AppConfig.userHeadImagePath = userHeadImagePath;
	}
	
	
}
