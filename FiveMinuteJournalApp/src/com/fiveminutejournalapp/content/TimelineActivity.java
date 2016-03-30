package com.fiveminutejournalapp.content;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fiveminutejournalapp.authorization.ActivityWithLogoutMenu;

public class TimelineActivity extends ActivityWithLogoutMenu {
	protected DrawerLayout drawerLayout;
	protected ListView drawerListView;
	protected LinearLayout slideMenu;
	protected TextView emailAddress;
	protected ActionBarDrawerToggle actionBarDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
