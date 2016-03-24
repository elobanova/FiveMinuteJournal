package com.fiveminutejournalapp.networking;

public class HostConnection {
	public static final String HOST_ADDRESS = "https://128.199.60.69";
	public static final String PORT = "8181";

	public static String getFullAddress() {
		return HOST_ADDRESS + ":" + PORT;
	}
}
