package com.example.tictactoe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Settings extends Activity {
private SeekBar seekbar,seekbar1;
private AudioManager am = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.activity_settings);
		initControls();
		seekbar = (SeekBar) findViewById(R.id.sb);
		  seekbar.setMax(255);
		   float curBrightnessValue = 0;
		   try {
		    curBrightnessValue = android.provider.Settings.System.getInt(
		      getContentResolver(),
		      android.provider.Settings.System.SCREEN_BRIGHTNESS);
		   } catch (SettingNotFoundException e) {
		    e.printStackTrace();
		   }

		    int screen_brightness = (int) curBrightnessValue;
		    seekbar.setProgress(screen_brightness);
		    seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		    int progress = 0;

		     @Override
		    public void onProgressChanged(SeekBar seekBar, int progresValue,
		      boolean fromUser) {
		     progress = progresValue;
		    }

		     @Override
		    public void onStartTrackingTouch(SeekBar seekBar) {
		     // Do something here,
		     // if you want to do anything at the start of
		     // touching the seekbar
		    }

		     @Override
		    public void onStopTrackingTouch(SeekBar seekBar) {
		     android.provider.Settings.System.putInt(getContentResolver(),
		       android.provider.Settings.System.SCREEN_BRIGHTNESS,
		       progress);
		    }
		   });
		    
		    
	}

	private void initControls() {
		 try
	        {
			 seekbar1 = (SeekBar)findViewById(R.id.sb2);
			 am  = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
	            seekbar1.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
	            seekbar1.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));   


	            seekbar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
	            {
	                @Override
	                public void onStopTrackingTouch(SeekBar arg0) 
	                {
	                }

	                @Override
	                public void onStartTrackingTouch(SeekBar arg0) 
	                {
	                }

	                @Override
	                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) 
	                {
	                	am.setStreamVolume(AudioManager.STREAM_MUSIC,
	                            progress, 0);
	                }
	            });
	        }
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void SettingBack (View view){
    	Intent i = new Intent (getApplicationContext(), Checklist.class);
    	startActivity(i);
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
