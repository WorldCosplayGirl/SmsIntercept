package com.example.austin.smsintercept;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Austin on 2016/11/9.
 */

public class SmsReceiver extends BroadcastReceiver {

    private Context context;

    public SmsReceiver(){

    }

    public SmsReceiver(Context context) {

        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received", Toast.LENGTH_SHORT).show();
        String action = intent.getAction();
        if (action.equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle extras = intent.getExtras();
            Log.e("bundle", extras.toString());
            Object[] pdus = (Object[]) extras.get("pdus");
            for (Object pdu : pdus) {
                SmsMessage mess = SmsMessage.createFromPdu((byte[]) pdu);
                String messageBody = mess.getMessageBody();
                String sender = mess.getOriginatingAddress();
                ((MainActivity)context).setText(messageBody);
            }

        }
    }


}
