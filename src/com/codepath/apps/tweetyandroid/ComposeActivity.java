package com.codepath.apps.tweetyandroid;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends Activity {
	Button btTweet;
	EditText etBody;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		setupViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}
	
	public void setupViews() {
		btTweet = (Button) findViewById(R.id.btTweet);
		etBody = (EditText) findViewById(R.id.etBody);
	}
	
	public void onTweet(View v) {
		String body = etBody.getText().toString();
		TwitterClientApp.getRestClient().postNewTweet(body, new JsonHttpResponseHandler() {
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				Log.d("DEBUG", "Failed sending tweet! - " + arg0.toString());
				Toast.makeText(getBaseContext(), "Sending tweet failed!", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onSuccess(int arg0, JSONArray arg1) {
				// TODO Auto-generated method stub
				Log.d("DEBUG", "Sent tweet! - " + arg1.toString());	
			}
		});
		
		Intent data = new Intent();
		data.putExtra("status", "Sending tweet...");
		setResult(RESULT_OK, data);
		finish();
	}
}
