package com.fiveminutejournalapp.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.content.Context;
import android.os.AsyncTask;

import com.fiveminutejournalapp.model.User;
import com.fiveminutejournalapp.networking.ApiPathEnum;
import com.fiveminutejournalapp.networking.HostConnection;
import com.fiveminutejournalapp.networking.ResponseEnum;
import com.fiveminutejournalapp.networking.SSLContextHelper;

public class CheckUserTask {
	private Context context;
	private OnResponseListener<User> onResponseListener;

	public CheckUserTask(Context context) {
		this.context = context;
	}

	/**
	 * Sends a GET request to authenticate a user
	 * 
	 * @param user
	 *            a user's entered data
	 */
	public void send(User user) {
		new HttpCheckUserTask().execute(user);
	}

	public void setOnResponseListener(OnResponseListener<User> onResponseListener) {
		this.onResponseListener = onResponseListener;
	}

	private class HttpCheckUserTask extends AsyncTask<User, Void, User> {

		@Override
		protected User doInBackground(User... args) {
			User loggingInUser = args[0];

			HttpsURLConnection conn = null;
			try {
				URL url = new URL(HostConnection.getFullAddress() + ApiPathEnum.USER_CHECK.getPath());
				conn = (HttpsURLConnection) url.openConnection();
				conn.setSSLSocketFactory(SSLContextHelper.initSSLContext(context).getSocketFactory());
				conn.setHostnameVerifier(SSLContextHelper.getHostnameVerifier());
				conn.setRequestMethod("GET");

				conn.setDoInput(true);
				conn.connect();
				int code = conn.getResponseCode();
				ResponseEnum responseCode = ResponseEnum.getResponseEnumByCode(code);
				switch (responseCode) {
				case OK:
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line + "\n");
					}
					br.close();
					return JSONUserParser.findUser(sb.toString(), loggingInUser);
				default:
					break;
				}
			} catch (Exception e) {
				onResponseListener.onError(e.getMessage());
			} finally {
				if (conn != null) {
					conn.disconnect();
				}
			}

			return null;
		}

		@Override
		protected void onPostExecute(User connectedUser) {
			super.onPostExecute(connectedUser);
			onResponseListener.onResponse(connectedUser);
		}
	}
}