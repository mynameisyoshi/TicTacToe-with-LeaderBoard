package com.example.tictactoe;



import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SmsTwoPlayActivity extends Activity {
public boolean choice = Selection.Choice;
public String contact = GameScreen.contact;
IntentFilter intentFilter;
private BroadcastReceiver intentReceiver;
	private Player2Game mGame;
	 public String moveMessage="";
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
	private boolean mIsPlayerOneTurn = true; 
	private boolean mGameOver = false;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE); 
      setContentView(R.layout.activity_sms_two_play);
      
      
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
    protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
 
		intentFilter = new IntentFilter();
		intentFilter.addAction("SMS_RECEIVED_ACTION");
 
		intentReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
			//---display the SMS received in the TextView---
				//TextView SMSes = (TextView) findViewById(R.id.textView1);
				//SMSes.setText(intent.getExtras().getString("sms"));
				moveMessage=intent.getExtras().getString("sms");
			}
		};
		
		//registering our receiver
		this.registerReceiver(intentReceiver, intentFilter);
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.sms_two_play, menu);
 
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
 
    		mPlayerOneText.setText("Player One:"); 
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
    				
    				
    					if (choice)
    					{   if(mIsPlayerOneTurn)
    						setMove1(Player2Game.PLAYER_ONE, location);
    					   else
    					   {
    						   int move = getPlayerMove();
    					       setMove(Player2Game.PLAYER_TWO, move);
    					   }
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
	    				}
	    				else if (winner == 2)
	    				{
	    					mInfoTextView.setText(R.string.result_p1_wins); 
	    					mPlayerOneCounter++;
	    					mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
	    					mGameOver = true;
	    					mIsPlayerOneTurn = false;
	    					
	    				}
	    				else
	    				{
	    					mInfoTextView.setText(R.string.result_p2_wins); 
	    					mPlayerTwoCounter++;
	    					mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
	    					mGameOver = true;
	    					mIsPlayerOneTurn = true;
	    					
	    				}
    					}
    					else
    					{  if(mIsPlayerOneTurn)
    					   {
    						int move = getPlayerMove();
    						setMove(Player2Game.PLAYER_ONE, move);
    					    }
    					else 
    						setMove1(Player2Game.PLAYER_TWO, location);
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
	    				}
	    				else if (winner == 2)
	    				{
	    					mInfoTextView.setText(R.string.result_p1_wins); 
	    					mPlayerOneCounter++;
	    					mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
	    					mGameOver = true;
	    					mIsPlayerOneTurn = false;
	    					
	    				}
	    				else
	    				{
	    					mInfoTextView.setText(R.string.result_p2_wins); 
	    					mPlayerTwoCounter++;
	    					mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
	    					mGameOver = true;
	    					mIsPlayerOneTurn = true;
	    					
	    				}
    					}
	    				
    				
    			}
    		
    	}
    }
 
    // set move for the current player
    private void setMove(char player, int location)
    {
    	mGame.setMove(player, location);
         mBoardButtons[location].setEnabled(false);
         mBoardButtons[location].setText(String.valueOf(player));
    	if (player == Player2Game.PLAYER_ONE)
    		mBoardButtons[location].setTextColor(Color.GREEN);
    	else
    		mBoardButtons[location].setTextColor(Color.RED);
    }
}

	public void setMove1(char playerOne, int location) {
		mGame.setMove(playerOne, location);
		 mBoardButtons[location].setEnabled(false);
		 mBoardButtons[location].setText(String.valueOf(playerOne));
	    	if (playerOne == Player2Game.PLAYER_ONE)
	    	{   mGame.setMove(playerOne, location);
	            mBoardButtons[location].setEnabled(false);
	    		mBoardButtons[location].setTextColor(Color.GREEN);
	            sendSms(playerOne,location);
	    	}
	    	else
	    	{    
	    		mBoardButtons[location].setTextColor(Color.RED);
	    	}
		
	}
	public int getPlayerMove() {
		int location;
		location = Integer.parseInt(moveMessage);
		return location;
	}

	

	private void sendSms(char player, int location) {
		String locationMessage = Integer.toString(location);
		String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";
 
      PendingIntent sentPI = null;//PendingIntent.getBroadcast(this, 0,
          // new Intent(SENT), 0);
 
       PendingIntent deliveredPI = null;//PendingIntent.getBroadcast(this, 0,
   //         new Intent(DELIVERED), 0);
 
        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent", 
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure", 
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service", 
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU", 
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off", 
                                Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));
 
        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered", 
                                Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered", 
                                Toast.LENGTH_LONG).show();
                        break;                        
                }
            }
        }, new IntentFilter(DELIVERED));        
 
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(contact, null, locationMessage, sentPI, deliveredPI);  
		
	}

	public void setMove2(char playerTwo, int location) {
		// TODO Auto-generated method stub
		
	}
}

