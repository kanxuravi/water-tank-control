package com.example.rantext;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class MainActivity extends AppCompatActivity
{
    Button send1, send0;
    TextView reserve, location1, location2, location3, location4;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("निलकण्ठ नगरपालिका वाटर पुम्प कन्ट्रोल");
        send1 = findViewById(R.id.btn1);
        send0 = findViewById(R.id.btn0);
        reserve = findViewById(R.id.reserve);
        location1 = findViewById(R.id.location1);
        location2 = findViewById(R.id.location2);
        location3 = findViewById(R.id.location3);
        location4 = findViewById(R.id.location4);

        if(receivedmno.equals("+9779816207311"))
        {
            reserve.setText(receivedChar);
        }

        try
        {
            send1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    send1();
                }
            });
            send0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    send0();
                }
            });
        }

        catch (java.lang.RuntimeException ex)
        {
            Toast.makeText(MainActivity.this,"Unknown Error Occurred",Toast.LENGTH_SHORT).show();
        }
    }



    public void send1()
    {
        try
        {
            RxPermissions rxPermissions;
            rxPermissions = new RxPermissions(this);
            rxPermissions.request(Manifest.permission.SEND_SMS).subscribe();
            Intent smsIntent = new Intent(getApplicationContext(),MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, smsIntent, 0);
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage("+9779843129845", null, "1", pi, null);
            Toast.makeText(MainActivity.this,"Started Successfully",Toast.LENGTH_SHORT).show();

        }

        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(MainActivity.this,"Failed, Please try again later",Toast.LENGTH_SHORT).show();
        }

        catch (java.lang.SecurityException ex)
        {
            Toast.makeText(MainActivity.this,"Can't Process, No Permission's Granted",Toast.LENGTH_SHORT).show();
        }


    }

    public void send0()
    {
        try{
        RxPermissions rxPermissions;
        rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.SEND_SMS).subscribe();
        Intent smsIntent = new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, smsIntent, 0);


            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage("+9779843129845", null, "0", pi, null);
            Toast.makeText(MainActivity.this,"Stopped Successfully",Toast.LENGTH_SHORT).show();

        } catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(MainActivity.this,"Failed, Please try again later",Toast.LENGTH_SHORT).show();
        }

        catch (java.lang.SecurityException ex){
            Toast.makeText(MainActivity.this,"Can't Process, No Permission's Granted",Toast.LENGTH_SHORT).show();
        }


    }

    public String charReceiver(){
        SmsBroadcastReceiver sms = new SmsBroadcastReceiver();
        return sms.characters;
    }
    String receivedChar = charReceiver();

    public String mnoReceiver(){
        SmsBroadcastReceiver no = new SmsBroadcastReceiver();
        return no.sender;
    }
    String receivedmno = mnoReceiver();



    }

