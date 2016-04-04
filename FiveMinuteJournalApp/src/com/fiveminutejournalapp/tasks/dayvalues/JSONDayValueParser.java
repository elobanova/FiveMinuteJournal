package com.fiveminutejournalapp.tasks.dayvalues;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fiveminutejournalapp.model.DayValue;
import com.fiveminutejournalapp.model.DayValue.DayValueBuilder;
import com.fiveminutejournalapp.model.User;
import com.fiveminutejournalapp.tasks.JSONUserParser;

public class JSONDayValueParser {
	private static final String ID_PROPERTY_NAME = "id";
	private static final String DATE_PROPERTY_NAME = "date";
	private static final String GRATEFUL_FOR_PROPERTY_NAME = "gratefulfor";
	private static final String WHAT_WOULD_IMPROVE_PROPERTY_NAME = "whatwouldimprove";
	private static final String AMAZING_TODAY_PROPERTY_NAME = "amazingtoday";
	private static final String HOW_MAKE_BETTER_PROPERTY_NAME = "howmakebetter";
	private static final String CITATION_PROPERTY_NAME = "text";
	private static final String CREATED_BY_PROPERTY_NAME = "createdby";

	public static List<DayValue> parse(String jsonString) throws JSONException {
		List<DayValue> items = new ArrayList<>();
		JSONArray coursesList = new JSONArray(jsonString);
		for (int i = 0; i < coursesList.length(); i++) {
			JSONObject courseJSONObject = coursesList.getJSONObject(i);
			items.add(parseItem(courseJSONObject));
		}
		return items;
	}

	private static DayValue parseItem(JSONObject dayValueJSONObject) throws JSONException {
		String dayValueId = dayValueJSONObject.getString(ID_PROPERTY_NAME);
		String dayValueDate = dayValueJSONObject.getString(DATE_PROPERTY_NAME);
		String[] gratefulFor = formatAsString(dayValueJSONObject.getJSONArray(GRATEFUL_FOR_PROPERTY_NAME));
		String[] whatWouldImprove = formatAsString(dayValueJSONObject.getJSONArray(WHAT_WOULD_IMPROVE_PROPERTY_NAME));
		String[] amazingToday = formatAsString(dayValueJSONObject.getJSONArray(AMAZING_TODAY_PROPERTY_NAME));
		String[] howMakeBetter = formatAsString(dayValueJSONObject.getJSONArray(HOW_MAKE_BETTER_PROPERTY_NAME));
		User user = JSONUserParser.parseFormattedUserItem(dayValueJSONObject.getJSONObject(CREATED_BY_PROPERTY_NAME));
		String citation = dayValueJSONObject.getString(CITATION_PROPERTY_NAME);
		return new DayValueBuilder(user).setId(dayValueId).setDate(dayValueDate).setCitation(citation)
				.setWhatwouldimprove(whatWouldImprove).setAmazingtoday(amazingToday).setHowmakebetter(howMakeBetter)
				.setGratefulFor(gratefulFor).build();
	}

	private static String[] formatAsString(JSONArray jsonArray) throws JSONException {
		int length = jsonArray.length();
		String[] result = new String[length];
		for (int i = 0; i < length; i++) {
			result[i] = jsonArray.get(i).toString();
		}
		return result;
	}
}
