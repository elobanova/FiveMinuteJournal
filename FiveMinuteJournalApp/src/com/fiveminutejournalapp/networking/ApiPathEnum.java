package com.fiveminutejournalapp.networking;

public enum ApiPathEnum {
	SIGN_UP("/signup"),
	USER_CHECK("/login"),
	HOME("/home");

	private final String path;

	private ApiPathEnum(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}
}
