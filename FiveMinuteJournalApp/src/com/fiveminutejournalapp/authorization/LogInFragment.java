package com.fiveminutejournalapp.authorization;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fiveminutejournalapp.R;
import com.fiveminutejournalapp.content.TimelineActivity;
import com.fiveminutejournalapp.model.User;
import com.fiveminutejournalapp.tasks.CheckUserTask;
import com.fiveminutejournalapp.tasks.OnResponseListener;


/**
 * Created by ekaterina on 23.03.2016.
 */
public class LogInFragment extends Fragment {
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View fragmentView = inflater.inflate(R.layout.login_fragment, container, false);
		this.loginButton = (Button) fragmentView.findViewById(R.id.login_button);
		Typeface gothamFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gotham-bold.ttf");
		loginButton.setTypeface(gothamFace);
		this.loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				progress = new ProgressDialog(getActivity());
				progress.setTitle(getResources().getString(R.string.sign_in_progress_title));
				progress.setMessage(getResources().getString(R.string.sign_in_progress_message));
				progress.setCancelable(false);
				progress.show();
				attemptToLogin(fragmentView.findViewById(R.id.login_username_input),
						fragmentView.findViewById(R.id.login_password_input));
			}
		});
		return fragmentView;
	}

	private void attemptToLogin(View usernameInput, View passwordInput) {
		if (usernameInput instanceof EditText && passwordInput instanceof EditText) {
			String username = ((EditText) usernameInput).getText().toString();
			String passwordValue = ((EditText) passwordInput).getText().toString();
			performLogin(username, passwordValue);
		}
	}

	private void performLogin(String usernameValue, String passwordValue) {
		CheckUserTask checkUserTask = new CheckUserTask(getActivity());
		checkUserTask.setOnResponseListener(new OnResponseListener<User>() {

			@Override
			public void onResponse(User connectedUser) {
				if (connectedUser != null) {
					SharedPreferences.Editor editor = sharedpreferences.edit();
					editor.putString(email, connectedUser.getEmailAddress());
					editor.putString(password, connectedUser.getPassword());
					editor.putString(username, connectedUser.getUserName());
					editor.putString(id, connectedUser.getId());
					editor.commit();
					Intent coursesActivityIntent = new Intent(getActivity(), TimelineActivity.class);
					startActivity(coursesActivityIntent);
				} else {
					progress.dismiss();
					onError(getResources().getString(R.string.failed_to_login_message));
				}
			}

			@Override
			public void onError(String errorMessage) {
				Log.e(TAG, errorMessage);
				Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
			}
		});
		checkUserTask.send(new User.UserBuilder("kitts", "kitts").build());
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

	/**
	 * Encodes the user's logging name and password as a base64 string to be
	 * sent to the server.
	 * 
	 * @param login
	 *            a user's logging name
	 * @param pass
	 *            a user's password
	 * @return the encoded string, containing user's logging name and password
	 *         as a base64 string
	 */
	public static String getB64Auth(String login, String pass) {
		String source = login + ":" + pass;
		String ret = "Basic " + Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
		return ret;
	}
}
