package com.fiveminutejournalapp.authorization;

import com.fiveminutejournalapp.model.User;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Button;

/**
 * Created by ekaterina on 23.03.2016.
 */
public class LogInFragment extends Fragment {
	public static final String HOST_ADDRESS = "http://e-cognita.herokuapp.com";
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String username = "usernameKey";
	public static final String password = "passwordKey";
	public static final String email = "emailKey";
	public static final String id = "idKey";
	private static final String TAG = "plug_login_tag";
	private static SharedPreferences sharedpreferences;

	public static final String USER_TAG = "user";
	private Button loginButton;
	private ProgressDialog progress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
	}

	/**
	 * Retrieves the connected user from the preferences file, where the logged
	 * in information is stored, e.g. his user name, password.
	 * 
	 * @return a connected user or null if no user is connected
	 */
	public static User getConnectedUser() {
		if (sharedpreferences != null && sharedpreferences.contains(username)) {
			String pass = sharedpreferences.getString(password, "");
			String userName = sharedpreferences.getString(username, "");
			return new User.UserBuilder(userName, pass).build();
		}
		return null;
	}

	/**
	 * Logges out the user from the system by removing his session information
	 * from the preference file. The user then is redirected to the initial log
	 * in page.
	 * 
	 * @param currentActivity
	 *            the activity from which action bar the user picked a log out
	 *            option.
	 */
	public static void performLogout(Activity currentActivity) {
		if (sharedpreferences != null) {
			SharedPreferences.Editor editor = sharedpreferences.edit();
			editor.clear();
			editor.commit();

			// redirect to the login screen
			Intent redirectToLoginIntent = new Intent(currentActivity, SignInActivity.class);
			currentActivity.startActivity(redirectToLoginIntent);
			currentActivity.finish();
		}
	}

	public static SharedPreferences getSharedpreferences() {
		return sharedpreferences;
	}
}
