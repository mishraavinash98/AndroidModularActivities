package com.mishraavinash98.loginusingfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText etSignUpEmailId,etSignUpPassword;
    Button btnSignUpRegister;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etSignUpEmailId=(EditText)findViewById(R.id.etSignUpEmail);
        etSignUpPassword=(EditText)findViewById(R.id.etSignUpPassword);
        btnSignUpRegister=(Button)findViewById(R.id.btnSignUpRegister);

        firebaseAuth=FirebaseAuth.getInstance();

        btnSignUpRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e=etSignUpEmailId.getText().toString();
                String p=etSignUpPassword.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(SignUpActivity.this,MainActivity.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(SignUpActivity.this, " issue"+task.getException(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        });


    }
}
