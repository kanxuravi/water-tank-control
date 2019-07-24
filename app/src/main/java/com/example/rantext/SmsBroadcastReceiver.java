package com.example.rantext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SmsBroadcastReceiver extends BroadcastReceiver
{
    public static final String SMS_BUNDLE = "pdus";
    public String smsMessageView = "";
    public String sender = "";
    public String characters = "";

    @Override

    public void onReceive(Context context, Intent intent)
    {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);

            for (Object sm : sms) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sm);
                String smsBody = smsMessage.getMessageBody();
                String address = smsMessage.getOriginatingAddress();

                smsMessageView += "SMS From: " + address + "\n";
                smsMessageView += smsBody + "\n";
                characters = smsBody.toUpperCase();
                sender = address;

            }

            Toast.makeText(context, smsMessageView, Toast.LENGTH_SHORT).show();

            MainActivity main = new MainActivity();
            TextView all = main.reserve;
            CharSequence charac = characters;
            try{
            all.setText(charac);
            }
            catch (RuntimeException ex){
                Toast.makeText(context,"Error Occurred",Toast.LENGTH_SHORT).show();
            }

            }
        }
    }

