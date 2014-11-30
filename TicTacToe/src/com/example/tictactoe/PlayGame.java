package com.example.tictactoe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayGame extends Activity {
	AlertDialog.Builder dialog;
	private Game mGame;
	private SharedPreferences gamePrefs;
	public static final String GAME_PREFS="ScoreFile";
	private Button mBoardButtons[];
 
	private TextView mInfoTextView;
	private TextView mHumanCount;
	private TextView mTieCount;
	private TextView mAndroidCount;
    private TextView Playername;
	private int mHumanCounter = 0;
	private int mTieCounter = 0;
	private int mAndroidCounter = 0;
 
	private boolean mHumanFirst = true;
	private boolean mGameOver = false;
 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        gamePrefs = getSharedPreferences(GAME_PREFS, 0);
   

if (isTablet(getBaseContext()))
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
        {
        	checkOrientation();
        }
    

 
        mBoardButtons = new Button[Game.getBOARD_SIZE()];
        mBoardButtons[0] = (Button) findViewById(R.id.one);
        mBoardButtons[1] = (Button) findViewById(R.id.two);
        mBoardButtons[2] = (Button) findViewById(R.id.three);
        mBoardButtons[3] = (Button) findViewById(R.id.four);
        mBoardButtons[4] = (Button) findViewById(R.id.five);
        mBoardButtons[5] = (Button) findViewById(R.id.six);
        mBoardButtons[6] = (Button) findViewById(R.id.seven);
        mBoardButtons[7] = (Button) findViewById(R.id.eight);
        mBoardButtons[8] = (Button) findViewById(R.id.nine);
 
        mInfoTextView = (TextView) findViewById(R.id.brightness);
        mHumanCount = (TextView) findViewById(R.id.humanCounter);
        Playername = (TextView) findViewById(R.id.human);
        Playername.setText(Register.sendValue());
        mTieCount = (TextView) findViewById(R.id.tiesCounter);
        mAndroidCount = (TextView) findViewById(R.id.androidCounter);
 
        mHumanCount.setText(Integer.toString(mHumanCounter));
        mTieCount.setText(Integer.toString(mTieCounter));
        mAndroidCount.setText(Integer.toString(mAndroidCounter));
 
        mGame = new Game();
 
        startNewGame();
 
    }
    public void onDestroy(){
	    setHighScore();
	    super.onDestroy();
	}   
    public void setHighScore(){
		int exScore = getScore();
		String name = Playername.getText().toString();
		if(exScore>0){
			SharedPreferences.Editor scoreEdit = gamePrefs.edit();
		//	SimpleDateFormat dateForm = new SimpleDateFormat("dd MM yy");
		//	String dateOutput = dateForm.format(new Date());
			String scores = gamePrefs.getString("highScores", "");
			if(scores.length()>0){
				List<Score> scoreStrings = new ArrayList<Score>();
				String[] exScores = scores.split("\\|");
				for(String eSc : exScores){
				    String[] parts = eSc.split(" - ");
				    scoreStrings.add(new Score(parts[0],Integer.parseInt(parts[1])));
				}
				Score newScore = new Score(name , exScore);
				scoreStrings.add(newScore);
				Collections.sort(scoreStrings);
				StringBuilder scoreBuild = new StringBuilder("");
				for(int s=0; s<scoreStrings.size(); s++){
				    if(s>=20) break;//only want ten
				    if(s>0) scoreBuild.append("|");//pipe separate the score strings
				    scoreBuild.append(scoreStrings.get(s).getScoreText());
				}
				//write to prefs
				scoreEdit.putString("highScores", scoreBuild.toString());
				scoreEdit.commit();
				
			}
			else{
				scoreEdit.putString("highScores", ""+name +" - "+exScore);
				scoreEdit.commit();
			}
			}
		
		}
    public int getScore() {
		// TODO Auto-generated method stub
		return 5;//mHumanCounter;
	}
	private void startNewGame()
    {
    	mGame.clearBoard();
 
    	for (int i = 0; i < mBoardButtons.length; i++)
    	{
    		mBoardButtons[i].setText("");
    		mBoardButtons[i].setEnabled(true);
    		mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
    	}
 
    	if (mHumanFirst)
    	{
    		mInfoTextView.setText(R.string.first_human);
    		mHumanFirst = false;
    	}
    	else
    	{
    		mInfoTextView.setText(R.string.turn_computer);
    		int move = mGame.getComputerMove();
    		setMove(Game.ANDROID_PLAYER, move);
    		mHumanFirst = true;
    	}
    	mGameOver = false;
    }
 
    
  //normal method
    public void checkOrientation( ){
        	
        	if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        	{
        	//	Toast.makeText(this, "portrait", Toast.LENGTH_LONG).show();
        	}
        	else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        	{
        	//	Toast.makeText(this, "landscape", Toast.LENGTH_LONG).show();
        	}
        	}


    public static boolean isTablet(Context context) {
    	    return (context.getResources().getConfiguration().screenLayout
    	            & Configuration.SCREENLAYOUT_SIZE_MASK)
    	            >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    	}

        
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            
            //Fn + Ctrl + F12
            // Checks the orientation of the screen for landscape and portrait
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
              //  Toast.makeText(this, "landscape", Toast.LENGTH_LONG).show();
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
              //  Toast.makeText(this, "portrait", Toast.LENGTH_LONG).show();
            }
            
          
        }





    private class ButtonClickListener implements View.OnClickListener
    {
    	int location;
 
    	public ButtonClickListener(int location)
    	{
    		this.location = location;
    	}
 
    	public void onClick(View view)
    	{
    		if (!mGameOver)
    		{
    			if (mBoardButtons[location].isEnabled())
    			{
    				setMove(Game.HUMAN_PLAYER, location);
 
    				int winner = mGame.checkForWinner();
 
    				if (winner == 0)
    				{
    					mInfoTextView.setText(R.string.turn_computer);
    					int move = mGame.getComputerMove();
    					setMove(Game.ANDROID_PLAYER, move);
    					winner = mGame.checkForWinner();    					
    				}
 
    				if (winner == 0)
    					mInfoTextView.setText(R.string.turn_human);
    				else if (winner == 1)
    				{
    					mInfoTextView.setText(R.string.result_tie);
    					mTieCounter++;
    					mTieCount.setText(Integer.toString(mTieCounter));
    					mGameOver = true;
    					startNew();
    				}
    				else if (winner == 5)
    				{
    					mInfoTextView.setText(R.string.result_human_wins);
    					mHumanCounter++;
    					mHumanCount.setText(Integer.toString(mHumanCounter));
    					mGameOver = true;
    					//setHighScore();
    					startNew();
    				}
    				else
    				{
    					mInfoTextView.setText(R.string.result_android_wins);
    					mAndroidCounter++;
    					mAndroidCount.setText(Integer.toString(mAndroidCounter));
    					mGameOver = true;
    					startNew();
    				}
    			}
    		}
    	}
    }
    private void startNew()
    {
    	if(mGameOver)
    	{
    		dialog = new AlertDialog.Builder(this);
    		dialog.setTitle("Like The Game?");
    		dialog.setPositiveButton("Play Again", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					startNewGame();
				}
    			
    		});
    		dialog.setNegativeButton("Exit Game", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					setHighScore();
					startActivity(new Intent(getApplicationContext(), Checklist.class));
				}
			});
    	dialog.show();
    	}
    }

    private void setMove(char player, int location)
    {
    	mGame.setMove(player, location);
    	mBoardButtons[location].setEnabled(false);
    	mBoardButtons[location].setText(String.valueOf(player));
    	if (player == Game.HUMAN_PLAYER)
    		mBoardButtons[location].setTextColor(Color.GREEN);
    	else
    		mBoardButtons[location].setTextColor(Color.RED);
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_game, menu);
		return true;
	}

	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		int exScore = getScore();
		savedInstanceState.putInt("score", exScore);
		//savedInstanceState.putInt("level", level);
		super.onSaveInstanceState(savedInstanceState);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		else if(id == R.id.item1){
			startNewGame();
			return true;
		}
		else if(id == R.id.item2){
			startActivity(new Intent(getApplicationContext(), Checklist.class));
		}
		return super.onOptionsItemSelected(item);
	}
	}


