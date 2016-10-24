package com.example.leonzhang.farmmaintenance;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver Maintenance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Maintenance = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent receiver) {
                if (receiver.getStringExtra("fan") != null) {
                    TextView fanStatus = (TextView) findViewById(R.id.Text_1);
                    int fan = Integer.valueOf(receiver.getStringExtra("fan"));
                    if(fan == -1){
                        fanStatus.setText("Fan Off");
                    }
                    else{
                        fanStatus.setText("Fan On");
                    }
                }
                if (receiver.getStringExtra("spr") != null) {
                    TextView fAndSStatus = (TextView) findViewById(R.id.Text_2);
                    int spr = Integer.valueOf(receiver.getStringExtra("spr"));
                    if(spr == -1){
                        fAndSStatus.setText("Fan & Sprinkler Off");
                    }else{
                        fAndSStatus.setText("Fan & Sprinkler On");
                    }
                }
                Toast.makeText(MainActivity.this, "Receive Broadcast", Toast.LENGTH_SHORT).show();
                abortBroadcast();
            }
        };
    }
    @Override
    protected void onResume() {
        // Register interest
        super.onResume();
        registerReceiver(Maintenance, new IntentFilter("MyIoTApp_Status"));
    }
}
