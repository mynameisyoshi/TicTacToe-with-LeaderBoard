package com.example.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import android.view.View;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class TwoPlayerActivity extends Activity{

	private Player2Game mGame;
	AlertDialog.Builder dialog;
	private SharedPreferences gamePrefs;
	// Buttons making up the board
	private Button mBoardButtons[];
 
	// Various text displayed
	private TextView mInfoTextView;
	private TextView mPlayerOneCount; 
	private TextView mTieCount;
	private TextView mPlayerTwoCount; 
	private TextView mPlayerOneText; 
	private TextView mPlayerTwoText; 
 
	// Counters for the wins and ties
	private int mPlayerOneCounter = 0; 
	private int mTieCounter = 0;
	private int mPlayerTwoCounter = 0; 
 
	// Bools needed to see if player one goes first
	// if the gameType is to be single or local multiplayer
	// if it is player one's turn
	// and if the game is over
	private boolean mPlayerOneFirst = true; 
	//private boolean mIsSinglePlayer = false; 
	private boolean mIsPlayerOneTurn = true; 
	private boolean mGameOver = false;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE); 
      setContentView(R.layout.activity_two_player);
      gamePrefs = getSharedPreferences(PlayGame.GAME_PREFS, 0);
      
      if (isTablet(getBaseContext()))
      	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
          else
          {
          	checkOrientation();
          }
      

 
        //boolean mGameType = getIntent().getExtras().getBoolean("gameType");
 
        // Initialize the buttons
        mBoardButtons = new Button[Player2Game.getBOARD_SIZE()];
        mBoardButtons[0] = (Button) findViewById(R.id.one1);
        mBoardButtons[1] = (Button) findViewById(R.id.two2);
        mBoardButtons[2] = (Button) findViewById(R.id.three);
        mBoardButtons[3] = (Button) findViewById(R.id.four);
        mBoardButtons[4] = (Button) findViewById(R.id.five);
        mBoardButtons[5] = (Button) findViewById(R.id.six);
        mBoardButtons[6] = (Button) findViewById(R.id.seven);
        mBoardButtons[7] = (Button) findViewById(R.id.eight);
        mBoardButtons[8] = (Button) findViewById(R.id.nine);
 
        // setup the textviews
        mInfoTextView = (TextView) findViewById(R.id.brightness);
        mPlayerOneCount = (TextView) findViewById(R.id.humanCounter); 
        mTieCount = (TextView) findViewById(R.id.tiesCounter);
        mPlayerTwoCount = (TextView) findViewById(R.id.androidCounter); 
        mPlayerOneText = (TextView) findViewById(R.id.human); 
        mPlayerTwoText = (TextView) findViewById(R.id.android); 
 
        // set the initial counter display values
        mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
        mTieCount.setText(Integer.toString(mTieCounter));
        mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
 
        // create a new game object
        mGame = new Player2Game();
 
        // start a new game
        startNewGame();
 
    }
    public void onDestroy(){
	    setHighScore();
	    super.onDestroy();
	}   
    public void setHighScore(){
		int exScore = getScore();
		String name;
		String p1name = mPlayerOneText.getText().toString();
		String p2name = mPlayerTwoText.getText().toString();
		if(exScore == mPlayerOneCounter)name=p1name;
		else name=p2name;
		if(exScore>0){
			SharedPreferences.Editor scoreEdit = gamePrefs.edit();
		//	SimpleDateFormat dateForm = new SimpleDateFormat("dd MM yy");
		//	String dateOutput = dateForm.format(new Date());
			String scores = gamePrefs.getString("highScores", "");
			if(scores.length()>0){
				List<Score> scoreStrings = new ArrayList<Score>();
				String[] exScores = scores.split("\\|");
				for(String eSc : exScores){
				    String[] parts = eSc.split("-");
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
    	if(mPlayerOneCounter > mPlayerTwoCounter)
  		return mPlayerOneCounter;
    	else
    		return mPlayerTwoCounter;
  	}
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.two_player, menu);
 
    	return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
 
    // start a new game
    // clears the board and resets all buttons / text
    // sets game over to be false
    private void startNewGame()
    {
 
    	//this.mIsSinglePlayer = isSingle;
 
    	mGame.clearBoard();
 
    	for (int i = 0; i < mBoardButtons.length; i++)
    	{
    		mBoardButtons[i].setText("");
    		mBoardButtons[i].setEnabled(true);
    		mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
    		//mBoardButtons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.blank));
    	}
 
    		mPlayerOneText.setText(Register.sendValue()); 
    		mPlayerTwoText.setText("Player Two:"); 
 
    		if (mPlayerOneFirst) 
        	{
    			mInfoTextView.setText(R.string.turn_player_one); 
        		mPlayerOneFirst = false;
        	}
        	else
        	{
        		mInfoTextView.setText(R.string.turn_player_two); 
        		mPlayerOneFirst = true;
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
    				
    				
    					if (mIsPlayerOneTurn)
    						setMove(Player2Game.PLAYER_ONE, location);
    					else
    						setMove(Player2Game.PLAYER_TWO, location);
 
	    				int winner = mGame.checkForWinner();
 
	    				if (winner == 0)
	    				{
	    					if (mIsPlayerOneTurn)
	    					{
	    						mInfoTextView.setText(R.string.turn_player_two);
	    						mIsPlayerOneTurn = false;
	    					}    					
	    					else
	    					{
	    						mInfoTextView.setText(R.string.turn_player_one);
	    						mIsPlayerOneTurn = true;
	    					}
	    				}
	    				else if (winner == 1)
	    				{
	    					mInfoTextView.setText(R.string.result_tie);
	    					mTieCounter++;
	    					mTieCount.setText(Integer.toString(mTieCounter));
	    					mGameOver = true;
	    					startNew();
	    				}
	    				else if (winner == 2)
	    				{
	    					mInfoTextView.setText(R.string.result_p1_wins); 
	    					mPlayerOneCounter++;
	    					mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
	    					mGameOver = true;
	    					mIsPlayerOneTurn = false;
	    				//	setHighScore();
	    					startNew();
	    					
	    				}
	    				else
	    				{
	    					mInfoTextView.setText(R.string.result_p2_wins); 
	    					mPlayerTwoCounter++;
	    					mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
	    					mGameOver = true;
	    					mIsPlayerOneTurn = true;
	    				//	setHighScore();
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
    			//setHighScore();
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
    // set move for the current player
    private void setMove(char player, int location)
    {
    	mGame.setMove(player, location);
         mBoardButtons[location].setEnabled(false);
         mBoardButtons[location].setText(String.valueOf(player));
    	if (player == Player2Game.PLAYER_ONE)
    	{   mGame.setMove(player, location);
            mBoardButtons[location].setEnabled(false);
    		mBoardButtons[location].setTextColor(Color.GREEN);
    	}
    	else
    	{   
    		mBoardButtons[location].setTextColor(Color.RED);
    	}
    }


}
