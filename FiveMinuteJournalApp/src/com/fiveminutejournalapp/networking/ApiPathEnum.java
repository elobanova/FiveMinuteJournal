package com.fiveminutejournalapp.networking;

public enum ApiPathEnum {
	SIGN_UP("/signup"),
	USER_CHECK("/api/users"),
	POST_DAY_VALUE("/api/dayvalues"),
	GET_ALL_DAY_VALUES("/api/dayvalues"),
	GET_OWN_DAY_VALUES("/api/owndayvalues");

	private final String path;

	private ApiPathEnum(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}
}
