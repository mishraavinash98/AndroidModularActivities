package com.mishraavinash98.callapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText etPhone;
    Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhone=(EditText)findViewById(R.id.etPhone);
        btnCall=(Button)findViewById(R.id.btnCall);


        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String p=etPhone.getText().toString();
                //validation
                if(p.length()!=10){

                    Toast.makeText(MainActivity.this, "Enter Valid Phone No", Toast.LENGTH_SHORT).show();
                    etPhone.setText("");
                    etPhone.requestFocus();
                    return;

                }
                //permission
                int res= ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);

                if(res== PackageManager.PERMISSION_GRANTED){
                    Intent i=new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+p));
                    startActivity(i);
                }
                else{
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},123);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==123&&grantResults[0]    ==PackageManager.PERMISSION_GRANTED){

            String p=etPhone.getText().toString();
            //validation
            if(p.length()!=10){

                Toast.makeText(MainActivity.this, "Enter Valid Phone No", Toast.LENGTH_SHORT).show();
                etPhone.setText("");
                etPhone.requestFocus();
                return;

            }

            Intent i=new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:"+p));
            startActivity(i);
        }
    }
}
