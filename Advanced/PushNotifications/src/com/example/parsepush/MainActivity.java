package com.example.parsepush;

import android.os.Bundle;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		  Parse.initialize(this, "BEfHGc1EGoiA6ZKLhLOPS5fdiXlbVBSjc7R12B8u", "Xr9rDHfjwabLi3mJ22F28YsAjtYgS0uZMWII7cPx");
		  
		  PushService.setDefaultPushCallback(this, MainActivity.class);
		  ParseInstallation.getCurrentInstallation().saveInBackground();
		  
		  ParseAnalytics.trackAppOpened(getIntent());
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
