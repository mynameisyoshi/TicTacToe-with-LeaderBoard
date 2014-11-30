package com.example.tictactoe;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class Register extends Activity {
	 public static String user1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        
        if (isTablet(getBaseContext()))
        	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            else
            {
            	checkOrientation();
            }
        

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.music);
		//turn up the volume
        mp.setVolume(20, 20);
        mp.start();

        //play ring tone for 45 seconds
        new Timer().schedule(new TimerTask() {
             @Override
             public void run() {
                 mp.stop();
             }
        }, 90000);

        //EditText editText = (EditText)findViewById(R.id.editText1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }
    
    public void checkOrientation( ){
    	
    	
    	if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
    	{
    	//	Toast.makeText(this, "portrait", Toast.LENGTH_LONG).show();
    	}
    	else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
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



    
    public void button1 (View view){
    	final EditText editText = (EditText)findViewById(R.id.editText1);
    	user1 = editText.getText().toString();
    	setValue(user1);
    	if(editText.getText().length()==0) 
    	{
    		editText.setError("Please Enter Your Name");
    	}
    	else if(editText.getText().length()<=2)editText.setError("Your name should be atleast 3 characters!");
    	else{
    	Intent i = new Intent (getApplicationContext(), Checklist.class);
    	i.putExtra("userName", editText.getText().toString());
    	startActivity(i);
    	
    }}

    private void setValue(String user1) {
		Register.user1=user1;
		}


	public static String sendValue(){
    	//final EditText tv = (EditText)findViewById(R.id.editText1);
       // user1  = tv.getText().toString();
		return user1;
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
        else if (id == R.id.leader) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
