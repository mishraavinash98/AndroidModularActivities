package com.mishraavinash98.playerinfo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Declare
    EditText etName;
    Button btnSave;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind
        etName=(EditText)findViewById(R.id.etName);
        btnSave=(Button) findViewById(R.id.btnSave);
        sp=getSharedPreferences("f1",MODE_PRIVATE);


        //perform

        String n=sp.getString("n","");
        //if user has saved the name before...can clear the data from app for reuse
        if(n.length()!=0){

            Intent intent=new Intent(MainActivity.this,Welcome.class);
            startActivity(intent);
            finish();
        }
        else{

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String n=etName.getText().toString();
                    if(n.length()==0){
                        etName.setError("NAME IS EMPTY");
                        etName.requestFocus();return;
                    }

                    //SP commit
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("n",n);
                    editor.commit();

                    //MAIN_ACTI to WELCOME ACTI
                    Intent intent=new Intent(MainActivity.this,Welcome.class);
                    startActivity(intent);
                    finish();


                }
            });

        }

    }
}
