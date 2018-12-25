package com.mishraavinash98.daynight;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Switch swN1;
    LinearLayout lay1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swN1=(Switch)findViewById(R.id.swN1);
        lay1=(LinearLayout)findViewById(R.id.lay1);

        swN1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b)
                    lay1.setBackgroundColor(Color.BLACK);
                else
                    lay1.setBackgroundColor(Color.WHITE);
            }
        });

    }
}
