package com.mishraavinash98.weightreporter;

import android.content.Intent;
import android.net.Uri;
import android.provider.Telephony;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSendEmail,btnSendSms;
    EditText etName,etWeight;
    FloatingActionButton fab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=(EditText)findViewById(R.id.etName);
        etWeight=(EditText)findViewById(R.id.etWeight);
        btnSendEmail=(Button)findViewById(R.id.btnSendEmail);
        btnSendSms=(Button)findViewById(R.id.btnSendSms);
        fab1=(FloatingActionButton)findViewById(R.id.fab1);

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n=etName.getText().toString();
                String w=etWeight.getText().toString();

                //validtion
                if(n.length()==0){
                    etName.setError("Name Empty");
                    Snackbar.make(view,"Name Empty",Snackbar.LENGTH_LONG).show();
                    etName.requestFocus();
                    return;
                }
                if(w.length()==0){
                    etWeight.setError("Weight Empty");
                    Snackbar.make(view,"Weight Empty",Snackbar.LENGTH_LONG).show();
                    etWeight.requestFocus();
                    return;
                }

                //explicit intent
                Intent intent=new Intent(MainActivity.this,EmailActivity.class);
                intent.putExtra("n",n);
                intent.putExtra("w",w);
                startActivity(intent);


            }
        });

        btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n=etName.getText().toString();
                String w=etWeight.getText().toString();

                //validtion
                if(n.length()==0){
                    etName.setError("Name Empty");
                    etName.requestFocus();
                    return;
                }
                if(w.length()==0){
                    etWeight.setError("Name Empty");
                    etWeight.requestFocus();
                    return;
                }

                //explicit intent
                Intent intent=new Intent(MainActivity.this,SmsActivity.class);
                intent.putExtra("n",n);
                intent.putExtra("w",w);
                startActivity(intent);

            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel" + "9967605390"));
                startActivity(intent);
            }
        });


    }
//inflation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m1,menu);
        return super.onCreateOptionsMenu(menu);
    }
//action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.about){
            Toast.makeText(this, "APP MADE BY AVINASH", Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId()==R.id.website){

            Intent i=new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http//"+"www.avinash.com"));
            startActivity(i);
        }




        return super.onOptionsItemSelected(item);
    }
}
