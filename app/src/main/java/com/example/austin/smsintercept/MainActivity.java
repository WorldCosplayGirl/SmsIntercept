package com.example.austin.smsintercept;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static TextView textView;
    private SmsReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView2);
        receiver = new SmsReceiver(this);
        registerReceiver(receiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));

    }

    public void setText(String message) {
        textView.setText(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
