package com.fiveminutejournalapp.tasks;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fiveminutejournalapp.model.User;
import com.fiveminutejournalapp.model.User.UserBuilder;

public class JSONUserParser {
	private static final String USERNAME_PROPERTY_NAME = "username";
	private static final String EMAIL_PROPERTY_NAME = "email";
	private static final String PASSWORD_PROPERTY_NAME = "password";

	public static User findUser(String string, User user) throws JSONException {
		List<User> users = parse(string);

		for (User parsedUser : users) {
			if (parsedUser.getUserName() == user.getUserName()) {
				return user;
			}
		}
		return null;
	}

	public static List<User> parse(String jsonString) throws JSONException {
		List<User> items = new ArrayList<User>();
		JSONArray usersList = new JSONArray(jsonString);
		for (int i = 0; i < usersList.length(); i++) {
			JSONObject fullUserJSONObject = usersList.getJSONObject(i);
			items.add(parseItem(fullUserJSONObject));
		}
		return items;
	}

	public static User parseFormattedUserItem(JSONObject formattedUserJSONObject) throws JSONException {
		String userName = formattedUserJSONObject.getString(USERNAME_PROPERTY_NAME);
		String email = formattedUserJSONObject.getString(EMAIL_PROPERTY_NAME);
		return new UserBuilder(userName).setEmail(email).build();
	}

	private static User parseItem(JSONObject fullUserJSONObject) throws JSONException {
		String userName = fullUserJSONObject.getString(USERNAME_PROPERTY_NAME);
		String email = fullUserJSONObject.getString(EMAIL_PROPERTY_NAME);
		String password = fullUserJSONObject.getString(PASSWORD_PROPERTY_NAME);
		return new UserBuilder(userName, password).setEmail(email).build();
	}

}
