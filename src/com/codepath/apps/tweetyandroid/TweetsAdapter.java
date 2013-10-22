package com.codepath.apps.tweetyandroid;

import java.util.List;

import com.codepath.apps.tweetyandroid.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetsAdapter extends ArrayAdapter<Tweet>{

	public TweetsAdapter(Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//take the list of tweets and then put the object stuff in
		//the tweet_item layout
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.tweet_item, parent, false);
		}
		
		//Get tweet from position
		Tweet tweet = getItem(position);
		
		//Get profile pic using an imageview
		ImageView imageView = (ImageView) convertView.findViewById(R.id.ivProfile);
		ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(), imageView);
		
		//user name
		TextView nameView = (TextView) convertView.findViewById(R.id.tvName);
		String formattedName = "<b>" + tweet.getUser().getName() + "</b>" + " <small><font color='#777777'>@" +
                tweet.getUser().getScreenName() + "</font></small>";
        nameView.setText(Html.fromHtml(formattedName));
        
        //body of tweet
        TextView bodyView = (TextView) convertView.findViewById(R.id.tvBody);
        bodyView.setText(Html.fromHtml(tweet.getBody()));
		
		return convertView;
	}
}
