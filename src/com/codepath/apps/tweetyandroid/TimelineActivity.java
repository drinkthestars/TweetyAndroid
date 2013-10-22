package com.codepath.apps.tweetyandroid;

import java.util.ArrayList;

import org.json.JSONArray;

import com.codepath.apps.tweetyandroid.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class TimelineActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		
		//call the app's singleton and get the rest client
		TwitterClientApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int arg0, JSONArray jsonArray) {
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonArray);
				ListView lvTweets = (ListView) findViewById(R.id.lvTweets);
				
				//new adapter
				TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
				
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
	}
	
	public void onProfileView(MenuItem m) {
		Log.d("DEBUG", "Profile View");
	}
}
