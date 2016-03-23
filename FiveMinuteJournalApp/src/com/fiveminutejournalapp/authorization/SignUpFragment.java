package com.fiveminutejournalapp.authorization;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fiveminutejournalapp.R;

/**
 * Created by ekaterina on 23.03.2016.
 */
public class SignUpFragment extends Fragment {
	public static final String TAG = "sign_up_tag";
	private Button signupButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View fragmentView = inflater.inflate(R.layout.signup_fragment, container, false);
		this.signupButton = (Button) fragmentView.findViewById(R.id.signup_button);

		return fragmentView;
	}

	private void performSignUp(final String userName, final String id, final String email, final String password) {
	}
}
