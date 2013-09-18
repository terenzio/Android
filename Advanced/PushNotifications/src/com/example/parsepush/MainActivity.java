package com.example.parsepush;

import android.os.Bundle;
import android.provider.Settings.Secure;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.PushService;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView textView;
    private Button button;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		  Parse.initialize(this, "BEfHGc1EGoiA6ZKLhLOPS5fdiXlbVBSjc7R12B8u", "Xr9rDHfjwabLi3mJ22F28YsAjtYgS0uZMWII7cPx");
		  
		  PushService.setDefaultPushCallback(this, MainActivity.class);
		  PushService.
		  		 		  
			// Save the current Installation to Parse.
			ParseInstallation.getCurrentInstallation().saveInBackground();
			//tracking push
			ParseAnalytics.trackAppOpened(getIntent());
			// When users indicate they are Giants fans, we subscribe them to that channel.	
			PushService.subscribe(this, "all", MainActivity.class);
			
			button = (Button) findViewById(R.id.button1);
			
			
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					ParsePush push = new ParsePush();
					push.setChannel("all");
					push.setMessage(editText.getText().toString());
					push.sendInBackground();
				}
			});
			textView.setText(getDeviceId() {
				return Secure.getString(getContentResolver(), Secure.ANDROID_ID);
			}
	
	
	}
	
	
	//to push to a unique device
	private String deviceId() {
		return Secure.getString(getContentResolver(), Secure.ANDROID_ID);
	}
	
	private void register() {
		ParseObject object = new ParseObject("info");
		object.put("device_id", getDeviceId());
		object.saveInBackground();
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
