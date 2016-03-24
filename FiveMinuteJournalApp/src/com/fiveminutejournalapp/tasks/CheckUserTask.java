package com.fiveminutejournalapp.tasks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

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
				String urlParameters = "username=" + URLEncoder.encode(loggingInUser.getUserName(), "UTF-8")
						+ "&password=" + URLEncoder.encode(loggingInUser.getPassword(), "UTF-8");
				URL url = new URL(HostConnection.getFullAddress() + ApiPathEnum.USER_CHECK.getPath() + "/?"
						+ urlParameters);
				conn = (HttpsURLConnection) url.openConnection();
				conn.setSSLSocketFactory(SSLContextHelper.initSSLContext(context).getSocketFactory());
				conn.setHostnameVerifier(SSLContextHelper.getHostnameVerifier());
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

				conn.setUseCaches(false);
				conn.setDoInput(true);
				conn.setDoOutput(true);

				int code = conn.getResponseCode();
				ResponseEnum responseCode = ResponseEnum.getResponseEnumByCode(code);
				switch (responseCode) {
				case OK:
				case CREATED:
					return loggingInUser;
				default:
					break;
				}
			} catch (MalformedURLException e) {
				onResponseListener.onError(e.getMessage());
			} catch (ProtocolException e) {
				onResponseListener.onError(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
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