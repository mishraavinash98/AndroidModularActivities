package com.mishraavinash98.playerinfo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Welcome extends AppCompatActivity {

    //D
    TextView tvName;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //B
        tvName=(TextView)findViewById(R.id.tvName);
        sp=getSharedPreferences("f1",MODE_PRIVATE);

        //getnamefromSP
        String n=sp.getString("n","");
        tvName.setText("Welcome"+n);

    }
}
