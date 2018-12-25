package com.mishraavinash98.waterreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStartAlarm,btnStopAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartAlarm=(Button)findViewById(R.id.btnStartAlarm);
        btnStopAlarm=(Button)findViewById(R.id.btnStopAlarm);

        Intent i=new Intent(MainActivity.this,MeraReceiver.class);
        final PendingIntent pi=PendingIntent.getBroadcast(this,1234,i,0);
        final AlarmManager am=(AlarmManager)getSystemService(ALARM_SERVICE);
        final long at=System.currentTimeMillis()+10000;

        btnStartAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                am.set(AlarmManager.RTC_WAKEUP,at,pi);
            }
        });

        btnStopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                am.cancel(pi);
            }
        });

    }
}
