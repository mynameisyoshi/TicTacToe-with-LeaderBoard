package com.example.tictactoe;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Selection extends Activity {
	private RadioGroup radioGroup;
	public static boolean Choice;
    private RadioButton radioButton;  
    private Button b1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection);
		 addListenerOnButton();
	}
    public void setChoice(boolean Choice)
    {
    	 Selection.Choice=Choice;
    }
	private void addListenerOnButton() {
    	try{
       	 radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
            b1 = (Button) findViewById(R.id.okBut);

            b1.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    // get selected radio button from radioGroup
                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radio button by returned id
                    radioButton = (RadioButton) findViewById(selectedId);
                   
                    if(radioButton == null)
                    {
                   	 
                    }
                   else{
                    switch(radioButton.getId())
                    {
                    case R.id.play1X:
                      Choice=true;	
              		  setChoice(Choice);
              		  startActivity(new Intent(getApplicationContext(),SmsTwoPlayActivity.class));
              		  break;
              	     case R.id.play2O:
              	      Choice=false; 	 
              	    setChoice(Choice);
              	  startActivity(new Intent(getApplicationContext(),SmsTwoPlayActivity.class));
              		  break;
            		  
                    }}                   

                }

   		

            });
       }catch(NullPointerException e){e.printStackTrace();}
	         	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selection, menu);
		return true;
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
