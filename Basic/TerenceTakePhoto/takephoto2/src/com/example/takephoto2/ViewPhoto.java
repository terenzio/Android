package com.example.takephoto2;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ViewPhoto extends Activity {
	
	private ImageView imageView;
	private LinearLayout linearLayout;
	private LinearLayout.LayoutParams imageMargin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		setContentView(R.layout.activity_view_photo);
		
		
		imageView =  (ImageView) findViewById(R.id.imageView1);
		linearLayout = (LinearLayout) findViewById(R.id.container);
		
		imageMargin = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		imageMargin.bottomMargin = 15;
		
		
		Bundle extras = getIntent().getExtras();
		byte[] b = extras.getByteArray("picture");

		Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
		ImageView image = (ImageView) findViewById(R.id.imageView1);

		image.setImageBitmap(bmp);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_photo, menu);
		return true;
	}

}
