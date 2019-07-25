package com.example.rantext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsBroadcastReceiver extends BroadcastReceiver
{
    public static final String SMS_BUNDLE = "pdus";
    public String smsMessageView = "";
    public String sender = "";
    public String characters = "";
    public String part1,part2,part3,part4,part5,time1;
    public String tank1, tank2, tank3, tank4, reservetank;

    @Override

    public void onReceive(Context context, Intent intent)
    {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);

            assert sms != null;
            for (Object sm : sms) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sm);
                String smsBody = smsMessage.getMessageBody();
                String address = smsMessage.getOriginatingAddress();

                smsMessageView += "SMS From: " + address + "\n";
                smsMessageView += smsBody + "\n";
                characters = smsBody.toUpperCase();
                sender = address;

                try {
                    //Code for Tanks value input
                    String breaks[] = characters.split("L");
                    part1 = breaks[0];
                    reservetank = part1.substring(part1.indexOf(':') + 3) + "L";
                    part2 = breaks[1];
                    tank1 = part2.substring(part2.indexOf(':') + 3) + "L";
                    part3 = breaks[2];
                    tank2 = part3.substring(part3.indexOf(':') + 3) + "L";
                    part4 = breaks[3];
                    tank3 = part4.substring(part4.indexOf(':') + 3) + "L";
                    part5 = breaks[4];
                    tank4 = part5.substring(part5.indexOf(':') + 3) + "L";

                    //Code for time input
                    String timer[] = characters.split("M");
                    time1 = timer[1] + "M";

                    Toast.makeText(context,"Correct Pattern Received \n Refreshing...", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex){
                    Toast.makeText(context,"Incorrect Pattern Received",Toast.LENGTH_SHORT).show();
                }

            }


            }

        }


}

