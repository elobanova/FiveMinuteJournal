package com.fiveminutejournalapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends Activity {
	private TextView welcomeTextView;
	private Button openJournalButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		welcomeTextView = (TextView) findViewById(R.id.welcomeText);
		Typeface ralewayFace = Typeface.createFromAsset(getAssets(),
				"fonts/Raleway-Regular.ttf");
		welcomeTextView.setTypeface(ralewayFace);

		openJournalButton = (Button) findViewById(R.id.open_journal_button);
		Typeface gothamFace = Typeface.createFromAsset(getAssets(),
				"fonts/gotham-bold.ttf");
		openJournalButton.setTypeface(gothamFace);
	}
}
