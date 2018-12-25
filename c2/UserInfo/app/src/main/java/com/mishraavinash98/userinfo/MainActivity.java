package com.mishraavinash98.userinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName,etPhoneNumber,etEmailAddress,etHomeAddress; //declare
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=(EditText)findViewById(R.id.etName);
        etPhoneNumber=(EditText)findViewById(R.id.etPhoneNumber);
        etEmailAddress=(EditText)findViewById(R.id.etEmailAddress);
        etHomeAddress=(EditText)findViewById(R.id.etHomeAddress);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=etName.getText().toString();
                String phoneNo=etPhoneNumber.getText().toString();
                String email=etEmailAddress.getText().toString();
                String homeadd=etHomeAddress.getText().toString();

                if(name.length()==0){
                    Toast.makeText(getApplicationContext(), "INVALID NAME", Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                    return;
                }
                if(phoneNo.length()==0){
                    Toast.makeText(getApplicationContext(), "INVALID PHONE NO", Toast.LENGTH_SHORT).show();
                    etPhoneNumber.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(getApplicationContext(), "INVALID EMAIL ID", Toast.LENGTH_SHORT).show();
                    etEmailAddress.requestFocus();
                    return;
                }

                if(homeadd.length()==0){
                    Toast.makeText(getApplicationContext(), "INVALID HOME ADDRESS", Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                    return;
                }

                String msg="NAME"+name+"\nPHONE NO"+phoneNo+"\nEMAIL ID"+email+"\nHOME ADDRESS"+homeadd;
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });



    }
}
