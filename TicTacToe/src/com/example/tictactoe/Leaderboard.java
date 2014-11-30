package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences.Editor;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.service.textservice.SpellCheckerService.Session;


public class Leaderboard extends Activity{
	
	public static final String GAME_PREFS="ScoreFile";
	Button fetch;
	TextView text;
	EditText et;
	private final String APP_SHARED_PREFS = "POP_TILE_PLUS_PREFERENCES";
	private SharedPreferences appSharedPrefs;
	private Editor prefsEditor;
//	private String DEBUG_TAG = "POP_TILE_PLUS_PREFS";
	
	public void Database(Context context) {
		this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS,
				Activity.MODE_PRIVATE);
		this.prefsEditor = appSharedPrefs.edit();
	}
	
	/**
	 * Get user highscore
	 * 
	 * @return score
	 */
	public int getHighscore(int gameMode) {
		
		//where am i getting the high scores and senging it back to the main activity (checklist) where it will be able to send it 
		//to the google data base
//		if (!Session.chaos)
			return appSharedPrefs.getInt("highscore" + gameMode, 0);
//		else
//			return appSharedPrefs.getInt("highscore_chaos" + gameMode, 0);

	}

	/**
	 * Set the user highscore
	 * 
	 * @param score
	 */
	public void setHighscore(int score, int gameMode) {
		
		//setting the use high score in a diffrent functions
//		if (!Session.chaos)
			prefsEditor.putInt("highscore" + gameMode, score);
//		else
//			prefsEditor.putInt("highscore_chaos" + gameMode, score);
		prefsEditor.commit();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leaderboard);
		TextView scoreView = (TextView)findViewById(R.id.textView2);
		SharedPreferences scorePrefs = getSharedPreferences(PlayGame.GAME_PREFS, 0);
		String[] savedScores = scorePrefs.getString("highScores", "").split("\\|");
		StringBuilder scoreBuild = new StringBuilder("");
		for(String score : savedScores){
		    scoreBuild.append(score+"\n");
		}
		scoreView.setText(scoreBuild.toString());
		if (isTablet(getBaseContext()))
	    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	        else
	        {checkOrientation();}
		fetch= (Button) findViewById(R.id.fetch);
		text = (TextView) findViewById(R.id.text);
		et = (EditText) findViewById(R.id.et);
	}
	
	//normal method
		public void checkOrientation( ){
			if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){}
		    	else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){}
		    	}

		public static boolean isTablet(Context context) {
			    return (context.getResources().getConfiguration().screenLayout
			            & Configuration.SCREENLAYOUT_SIZE_MASK)
			            >= Configuration.SCREENLAYOUT_SIZE_LARGE;
			}		    
		    public void onConfigurationChanged(Configuration newConfig) {
		        super.onConfigurationChanged(newConfig);
		        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
		          //  Toast.makeText(this, "landscape", Toast.LENGTH_LONG).show();
		        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
		          //  Toast.makeText(this, "portrait", Toast.LENGTH_LONG).show();
		        }      
		    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.leaderboard, menu);
		return true;
	}
	
	public void Leaderback (View view){
    	Intent i = new Intent (getApplicationContext(), Checklist.class);
    	startActivity(i);
    }
	
/*	public void showPlayServices(View v) {
		CharSequence playServices[] = new CharSequence[] { "High scores","Achievements" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Play Services");
		builder.setItems(playServices, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int optionChose) {
				if (optionChose == 0) showLeaderBoards();
				else showAchievements();
			}
		});
		builder.show();
	} */
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
