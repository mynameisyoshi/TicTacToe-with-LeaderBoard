package com.example.tictactoe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class InfoScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_screen);
		
		if (isTablet(getBaseContext()))
	    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	        else
	        {
	        	checkOrientation();
	        }
	    

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info_screen, menu);
		return true;
	}
	
	public void Infoback (View view){
    	Intent i = new Intent (getApplicationContext(), Checklist.class);
    	startActivity(i);
    }
	
	
	//normal method
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
		return super.onOptionsItemSelected(item);
	}
}
