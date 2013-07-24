package com.example.connectnetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final static String ROOT_URL = "http://maps.googleapis.com/maps/api/geocode/json";
	private String EXAMPLE_URL;

	private ProgressDialog progress;
	private TextView resultTextView;
	private Button button1;
	private Button button2;
	private Button button3;
	private EditText addressEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		resultTextView = (TextView) findViewById(R.id.result);
		addressEditText = (EditText) findViewById(R.id.editText1);
		
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				String address = addressEditText.getText().toString();
				String params = String.format("?address=%s&sensor=false", address);
				
				EXAMPLE_URL = ROOT_URL + params;
				UrlLoader urlLoader = new UrlLoader();
				urlLoader.execute(2);
			}
		});

		/*button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UrlLoader urlLoader = new UrlLoader();
				urlLoader.execute(2);
			}
		});*/

		progress = new ProgressDialog(this);
	}

	private String readStreamToString(InputStream in) {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
		try {
			String line;
			StringBuilder content = new StringBuilder();
			while ((line = buffer.readLine()) != null) {
				content.append(line);
			}
			return content.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String fetchMethod1() {
		try {
			URL url = new URL(EXAMPLE_URL);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			String content = readStreamToString(is);
			return content;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String fetchMethod2() {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(EXAMPLE_URL);
		try {
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String content = httpclient.execute(httpget, responseHandler);
			return content;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Android �b API 8 ����K����b Main Thread �������ϥκ������\��A�_�h�|��X
	 * android.os.NetworkOnMainThreadException �o�Өҥ~�C�ҥH�����N�ϥκ������{���X�g�b�t�~�@�� Thread
	 * �����C�o�̧ڭ̨ϥ� AsyncTask �����U�ڭ̧����o��Ʊ��C
	 * 
	 * �t�~�n�`�N���O�A����|���� UI ���ާ@�@�w�n�b Main Thread ������CĴ�p TextView �̭��� setText()
	 * �ѩ� doInBackground() �ä��O Main Thread �ҥH����b�o�̾ާ@ UI�C 
	 */

	private class UrlLoader extends AsyncTask<Integer, Integer, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progress.setTitle("Fetching");
			progress.show();
		}

		@Override
		protected String doInBackground(Integer... params) {
			int useMethod = params[0];
			switch (useMethod) {
			case 1:
				return fetchMethod1();
			case 2:
				return fetchMethod2();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (result != null) {
				try {
					
					JSONObject object = new JSONObject(result);
					JSONObject locationObject = object.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
					double lat = locationObject.getDouble("lat");
					double lng = locationObject.getDouble("lng");
					
					resultTextView.setText("lat="+lat+",lng="+lng);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}				
			}
			progress.dismiss();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
