package com.fiveminutejournalapp.content;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiveminutejournalapp.R;
import com.fiveminutejournalapp.authorization.LogInFragment;
import com.fiveminutejournalapp.model.User;

public class DayFragment extends Fragment {

	public static Fragment newInstance(User connectedUser) {
		Bundle args = new Bundle();
		args.putSerializable(LogInFragment.USER_TAG, connectedUser);
		DayFragment fragment = new DayFragment();

		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.day_value_fragment, container, false);
		return view;
	}

}
