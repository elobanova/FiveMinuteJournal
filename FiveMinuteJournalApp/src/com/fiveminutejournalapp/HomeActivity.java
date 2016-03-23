package com.fiveminutejournalapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity {
	private TextView welcomeTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		welcomeTextView = (TextView) findViewById(R.id.welcomeText);
        Typeface face= Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        welcomeTextView.setTypeface(face);
	}
}
