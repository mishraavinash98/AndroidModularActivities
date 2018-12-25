package com.mishraavinash98.evenodd;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etNumber; //declare
    Button btnFind;
    TextView tvResult;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int o= ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT; //screen lock
        setRequestedOrientation(o);

        etNumber=(EditText)findViewById(R.id.etNumber); //binding
        btnFind=(Button)findViewById(R.id.btnFind);
        tvResult=(TextView) findViewById(R.id.tvResult);

        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {  //for error handling
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR)
                    tts.setLanguage(Locale.ENGLISH);
                else
                    Toast.makeText(MainActivity.this, "tts issue", Toast.LENGTH_SHORT).show();

            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=etNumber.getText().toString();
                if(n.length()==0){  //validation
                    Toast.makeText(MainActivity.this, "Number is empty", Toast.LENGTH_SHORT).show();
                    etNumber.requestFocus();
                    return;
                }

                int num=Integer.parseInt(n);
                String res= num%2==0?"Even":"Odd" ;
                String msg="Number "+num+" is "+res;

                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                tvResult.setText(msg);
                tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Tussi jaa rahe ho?..Tusi naa jaoo");
        builder.setCancelable(false);

        builder.setPositiveButton("haa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNegativeButton("na na", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert =builder.create();
        alert.setTitle("Sachi mein");
        alert.show();

    }
}
