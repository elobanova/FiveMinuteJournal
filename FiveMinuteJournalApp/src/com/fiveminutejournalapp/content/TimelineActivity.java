package com.fiveminutejournalapp.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fiveminutejournalapp.R;
import com.fiveminutejournalapp.authorization.ActivityWithLogoutMenu;
import com.fiveminutejournalapp.authorization.LogInFragment;

public class TimelineActivity extends ActivityWithLogoutMenu {
	protected DrawerLayout drawerLayout;
	protected ListView drawerListView;
	protected LinearLayout slideMenu;
	protected TextView emailAddress;
	protected ActionBarDrawerToggle actionBarDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		Button addDayButton = (Button) findViewById(R.id.add_day_cutton);

		if (addDayButton != null) {
			addDayButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(TimelineActivity.this, DayActivity.class);
					startActivity(intent);
				}
			});
		}

		if (savedInstanceState == null) {
			displayView(R.id.frame_container);
		}
	}

	private void displayView(int frameContainer) {
		Fragment fragment = TimelineFragment.newInstance(LogInFragment.getConnectedUser());
		if (fragment != null) {
			FragmentManager fragmentManager = TimelineActivity.this.getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(frameContainer, fragment).commit();
		}
	}
}
