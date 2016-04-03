package com.fiveminutejournalapp.content;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.fiveminutejournalapp.R;
import com.fiveminutejournalapp.authorization.ActivityWithLogoutMenu;
import com.fiveminutejournalapp.authorization.LogInFragment;

public class DayActivity extends ActivityWithLogoutMenu {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day);

		if (savedInstanceState == null) {
			Fragment fragment = DayFragment.newInstance(LogInFragment.getConnectedUser());
			if (fragment != null) {
				FragmentManager fragmentManager = DayActivity.this.getSupportFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.day_frame_container, fragment).commit();
			}
		}
	}
}
