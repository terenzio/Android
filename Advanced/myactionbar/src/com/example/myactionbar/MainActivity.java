package com.example.myactionbar;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.actionbarsherlock.app.ActionBar;

public class MainActivity extends SherlockActivity {	

	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		actionBar = getSupportActionBar();
		actionBar.setTitle("MySherlockBar!");
		actionBar.setHomeButtonEnabled(true);
		
   	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()){
			case R.id.subItem1:
				break;
			case R.id.subItem2:				
				break;						
		}		
		return super.onOptionsItemSelected(item);
	}



	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
//		MenuInflater menuInflater = getSupportMenuInflater();
//		menuInflater.inflate(R.menu.main, menu);
		
		SubMenu submenu1 = menu.addSubMenu("Start2");
		submenu1.add("subItemStart1");
		submenu1.add("subItemStart2");
		submenu1.add("subItemStart3");
		
		MenuItem subMenuItem = submenu1.getItem();
		subMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
					
		return super.onCreateOptionsMenu(menu);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
}
