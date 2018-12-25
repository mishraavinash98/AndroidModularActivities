package com.mishraavinash98.squarecalc;

import android.content.pm.ActivityInfo;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etNumber; //DECLARATION
    Button btnSquare,btnCube,btnSqroot;
    TextView tvNumber;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int o= ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT; //to keep app in potrait mode(orientation lock)
        setRequestedOrientation(o);

        etNumber=(EditText)findViewById(R.id.etNumber);  // BINDING
        btnSquare=(Button)findViewById(R.id.btnSquare);
        btnCube=(Button)findViewById(R.id.btnCube);
        btnSqroot=(Button)findViewById(R.id.btnSqroot);
        tvNumber=(TextView)findViewById(R.id.tvNumber);

        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR)
                    tts.setLanguage(Locale.ENGLISH);
            }
        });


        btnSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s=etNumber.getText().toString();

                if(s.length()==0){ //validation to aviod app crash
                    Toast.makeText(MainActivity.this, "Number is empty", Toast.LENGTH_SHORT).show();
                    etNumber.requestFocus();
                    return;
                }

                double number=Double.parseDouble(s);
                double square=number*number;

                Toast.makeText(MainActivity.this, "Square="+square, Toast.LENGTH_SHORT).show(); //n1
                tvNumber.setText("Square="+square); //n2

                String msg="Square of "+number+"is"+square; //n3
                tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null);

            }
        });

        btnCube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s=etNumber.getText().toString();

                if(s.length()==0){ //validation to aviod app crash
                    Toast.makeText(MainActivity.this, "Number is empty", Toast.LENGTH_SHORT).show();
                    etNumber.requestFocus();
                    return;
                }

                double number=Double.parseDouble(s);
                double cube=number*number*number;

                Toast.makeText(MainActivity.this, "Cube="+cube, Toast.LENGTH_SHORT).show(); //n1
                tvNumber.setText("Cube="+cube); //n2

                String msg="Cube of"+number+"is"+cube; //n3
                tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null);

            }
        });

        btnSqroot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s=etNumber.getText().toString();

                if(s.length()==0){ //validation to aviod app crash
                    Toast.makeText(MainActivity.this, "Number is empty", Toast.LENGTH_SHORT).show();
                    etNumber.requestFocus();
                    return;
                }

                double number=Double.parseDouble(s);
                double sqroot=Math.sqrt(number);

                Toast.makeText(MainActivity.this, "Squareroot="+sqroot, Toast.LENGTH_SHORT).show(); //n1
                tvNumber.setText("Squareroot="+sqroot); //n2

                String msg="Squareroot of"+number+"is"+sqroot; //n3
                tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null);

            }
        });




    }
}
