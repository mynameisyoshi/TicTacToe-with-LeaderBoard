package com.example.tictactoe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
 
public class SmsReciever extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) 
    {
        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();        
        SmsMessage[] msgs = null;
        String str = "";  
        String message="";
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];            
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]); 
                message = msgs[i].getMessageBody().toString();
                str += "SMS from " + msgs[i].getOriginatingAddress();                     
                str += " :";
                str += msgs[i].getMessageBody().toString();
                str += "\n";        
            }
            //---display the new SMS message---
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
          //---stop the SMS message from being broadcasted---
           this.abortBroadcast();
           
           // Send the message to the Main Activity
         //---send a broadcast intent to update the SMS received in this activity---
           Intent broadcastIntent = new Intent();
           broadcastIntent.setAction("SMS_RECEIVED_ACTION");
           broadcastIntent.putExtra("sms", message);
           context.sendBroadcast(broadcastIntent);



        }                         
    }
}
