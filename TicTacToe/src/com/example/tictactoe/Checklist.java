package com.example.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

public class Checklist extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checklist);
		if (isTablet(getBaseContext()))
	    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	        else
	        {checkOrientation();}
		doThis();
	}
   public void doThis()
   {
	   final TextView tv = (TextView)findViewById(R.id.brightness);
	   tv.setText(Register.sendValue());
   }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.checklist, menu);
		return true;
		}
	
	public void CheckBack(View view){
    	Intent i = new Intent (getApplicationContext(), Register.class);
    	startActivity(i);
    }
		
	public void checkOrientation( ){
  	
    	if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){}
    	else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){}
    	}
		
public static boolean isTablet(Context context) {
	    return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)>= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}
    
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {} 
		else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){}
    }

	public void InfoButton(View view){
    	Intent i = new Intent (getApplicationContext(), InfoScreen.class);
    	startActivity(i);
    }
	
	public void ListPlay(View view){
    	Intent i = new Intent (getApplicationContext(), GameScreen.class);
    	startActivity(i);
    }
	
	public void showPlayServices(View v) {
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
	}
	
	public void showAchievements() {
 		if (getApiClient().isConnected())
 			startActivityForResult(
 					Games.Achievements.getAchievementsIntent(getApiClient()), 11);
 		else
 			beginUserInitiatedSignIn();
	}
	
	public void showLeaderBoards() {
 		if (!getApiClient().isConnected()) {
 			beginUserInitiatedSignIn();
 			return;
 		}
	}
		
	class GamePlayService {
		public void submitScore(long score) {
 			if (!isSignedIn())
 				return;
 
 			switch (Session.gameMode) {
 			
 			default: Session.GAME_MODE_DEC:
 				if (Session.chaos)
 					Games.Leaderboards.submitScore(getApiClient(),
 							getString(R.string.leaderboard_regular_chaos),
 							score);
 				else
 					Games.Leaderboards.submitScore(getApiClient(),
 							getString(R.string.leaderboard_regular), score);
 				break;

			}
		}
		
	public void unLockAchievement(int id) {
 			if (isSignedIn())
 				Games.Achievements.unlock(getApiClient(), getString(id));
 		}
 
 		public void incrementAchievement(int id) {
 			if (isSignedIn())
 				Games.Achievements.increment(getApiClient(), getString(id), 1);
		} 
		
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		final TextView tv = (TextView)findViewById(R.id.brightness);
		if (id == R.id.action_settings) {
			Intent i = new Intent (getApplicationContext(), Settings.class);
	    	startActivity(i);
			return true;
		}
		else if (id == R.id.option1) {
			Intent n = new Intent (getApplicationContext(), GameScreen.class);	
			        	startActivity(n);
						return true;
					}
		else if (id == R.id.option3) {
			Intent i = new Intent (getApplicationContext(), Leaderboard.class);
	    	startActivity(i);
            return true;
        }
		else if (id == R.id.info) {
			Intent i = new Intent (getApplicationContext(), InfoScreen.class);
			i.putExtra("userName", tv.getText().toString());
	    	startActivity(i);
            return true;
        }
		return false;
		}
	}
}

