package com.codepath.apps.tweetyandroid;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.tweetyandroid.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends Activity {
	ArrayList<Tweet> tweets;
	ListView lvTweets;
	TweetsAdapter adapter;
	
	public final int REQUEST_CODE = 7;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		getTimeline();
		
	}

	public void getTimeline() {
		//call the app's singleton and get the rest client
		TwitterClientApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int arg0, JSONArray jsonArray) {
				tweets = Tweet.fromJson(jsonArray);
				lvTweets = (ListView) findViewById(R.id.lvTweets);
				
				//new adapter
				adapter = new TweetsAdapter(getBaseContext(), tweets);
				
				//set the adapter on the list view
				lvTweets.setAdapter(adapter);
			}
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				Log.d("DEBUG", "Failed! - " + arg0.toString());
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}
	
	public void onComposeAction(MenuItem m) {
		Log.d("DEBUG", "Compose tweet");
		Intent i = new Intent(this, ComposeActivity.class);
		// REQUEST_CODE can be any value we like, used to determine the result later
		startActivityForResult(i, REQUEST_CODE);
	}
	
	public void onRefreshView(MenuItem m) {
		getTimeline();
	}
	
	public void onTrendsView(MenuItem m) {
		Log.d("DEBUG", "Clicked on Trends button");
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			 getTimeline();
		     Toast.makeText(this, data.getStringExtra("status"),
		        Toast.LENGTH_SHORT).show();
		     
		}
	}
	
	public void onProfileView(MenuItem m) {
		Log.d("DEBUG", "Profile View");
		Intent i = new Intent(this, ProfileActivity.class);
   	 	startActivity(i);
	}
}
