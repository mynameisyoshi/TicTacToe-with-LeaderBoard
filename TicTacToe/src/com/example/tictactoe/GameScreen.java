package com.example.tictactoe;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class GameScreen extends Activity {
	AlertDialog.Builder dialog, builder;
	public static String contact;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen);
		
		if (isTablet(getBaseContext()))
	    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	        else
	        {
	        	checkOrientation();
	        }

		
		((Button) findViewById(R.id.button2)).setOnClickListener(new OnClickListener() {
			public void onClick(View V) {
				Log.d("DEBUG", "One Player Button Pressed!");
				Intent intent = new Intent(GameScreen.this, PlayGame.class);
				
				startActivity(intent);
			}
		});
 
		((Button) findViewById(R.id.button3)).setOnClickListener(new OnClickListener() {
			public void onClick(View V) {
				Log.d("DEBUG", "Two Player Button Pressed!");
				Intent intent = new Intent(GameScreen.this, TwoPlayerActivity.class);
				startActivity(intent);
			}
		});
		
		((Button) findViewById(R.id.smsBut)).setOnClickListener(new OnClickListener() {
			public void onClick(View V) {
				Log.d("DEBUG", "Two Player Button Pressed!");
				alert();
		
				//Intent intent = new Intent(GameScreen.this, TwoPlayerActivity.class);
				//startActivity(intent);
			}

			
		});
	
		
			}
	private void alert() {
	dialog = new AlertDialog.Builder(this);
	dialog.setTitle("Warning! This mode is using your SMS service!");
	dialog.setMessage("Enter Contact Number");
	final EditText input =new EditText(this);
    dialog.setView(input);
	dialog.setPositiveButton("Continue", new DialogInterface.OnClickListener(){

		@Override
		public void onClick(DialogInterface dialog, int which) {
			//final EditText input = (EditText)findViewById(R.id.contact);
		//	input.setVisibility(View.VISIBLE);
			input.getText();
			contact = input.getText().toString();
			 setContact(contact);
			startActivity(new Intent(getApplicationContext(), Selection.class));
			//dialog.setView(inflater.inflate(R.layout.dialog_contact, null))
		}
	});
	dialog.setNegativeButton("Exit Game", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			//startActivity(new Intent(getApplicationContext(), Checklist.class));
			finish();
		}
	});
dialog.show();
	
}
	public void setContact(String contact)
    {
    	 GameScreen.contact=contact;
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_screen, menu);
		return true;
	}
	/**public void player1 (View view)
	{Log.d("DEBUG", "One Player Button Pressed!");
		Intent i = new Intent (getApplicationContext(), PlayGame.class);
			i.putExtra("gameType", true);
				startActivity(i);
		
	}
	public void button2 (View view)
	{
	    	/**final EditText editText = (EditText)findViewById(R.id.editText1);
	    		editText.setVisibility(view.VISIBLE);
	    	if(editText.getText().length()==0) 
	    	{
	    		editText.setError("Please Enter Player2 Name");
	    	}
	    	else{
	    	//Intent i = new Intent (getApplicationContext(), Checklist.class);
	    	//i.putExtra("userName", editText.getText().toString());
	    	//startActivity(i);
	    	
	    }*/
		/**Log.d("DEBUG", "Two Player Button Pressed!");
		Intent intent = new Intent(getApplicationContext(), PlayGame.class);
		intent.putExtra("gameType", false);
		startActivity(intent);
	}*/
	public void onBackPressed (View view ){
    	Intent i = new Intent (GameScreen.this, Checklist.class);
    	startActivity(i);
    	super.onBackPressed();
    }
	
	public void checkOrientation( ){
    	/**
    	Display getOrient = getWindowManager().getDefaultDisplay();
        @SuppressWarnings("unused")
		int orientation = Configuration.ORIENTATION_UNDEFINED;
        if(getOrient.getWidth()==getOrient.getHeight()){
            orientation = Configuration.ORIENTATION_SQUARE;
        } else{ 
            if(getOrient.getWidth() < getOrient.getHeight()){
                orientation = Configuration.ORIENTATION_PORTRAIT;
            }else { 
                 orientation = Configuration.ORIENTATION_LANDSCAPE;
            }
        }
        return;
**/
    	
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





	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		else if (id == R.id.item1)
		{Intent n = new Intent (getApplicationContext(), PlayGame.class);
    	
    	startActivity(n);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
