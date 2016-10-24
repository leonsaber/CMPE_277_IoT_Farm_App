package com.example.leonzhang.iotfarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver Maker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Maker = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent receiver){
                //Receiver data by tags "tem" and "hum"
                String tem = receiver.getStringExtra("tem");
                String hum= receiver.getStringExtra("hum");

                TextView temperature = (TextView) findViewById(R.id.text_1);
                TextView humidity = (TextView) findViewById(R.id.text_2);
                //Set Text
                temperature.setText("Temperature: " + tem);
                humidity.setText("Humidity: " + hum);
                Toast.makeText(MainActivity.this, "Receive Broadcast"+tem+" "+hum, Toast.LENGTH_SHORT).show();
                abortBroadcast();
            }
        };
        Button btn_fan = (Button) findViewById(R.id.button_1);
        btn_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fanSender();
            }
        });
        Button btn_fAndS = (Button) findViewById(R.id.button_2);
        btn_fAndS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fanAndSprSender();
            }
        });
    }

    @Override
    protected void onResume() {
        // Register interest
        super.onResume();
        registerReceiver(Maker, new IntentFilter("MyIoTApp"));
    }

    private int x = -1;
    private int y = -1;
    //Send Fan status by Broadcast
    private void fanSender(){
        String data = String.valueOf(x = x * -1);
        Intent sender_1= new Intent();
        sender_1.putExtra("fan", data);
        sender_1.setAction("MyIoTApp_Status");
        sendOrderedBroadcast(sender_1, null);
    }
    //Send Fan & status by Broadcast
    private void fanAndSprSender(){
        String data = String.valueOf(y = y * -1);
        Intent sender_2= new Intent();
        sender_2.putExtra("spr", data);
        sender_2.setAction("MyIoTApp_Status");
        sendOrderedBroadcast(sender_2, null);
    }

}


