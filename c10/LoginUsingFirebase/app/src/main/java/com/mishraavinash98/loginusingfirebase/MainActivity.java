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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText etUserName,etPassword;
    Button btnSignIn,btnSignUp;
    FirebaseAuth firebaseAuth;

    FirebaseUser user;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        firebaseAuth=FirebaseAuth.getInstance();
        btnReset=(Button)findViewById(R.id.btnReset);


        user=firebaseAuth.getCurrentUser();

        if(user!=null){

            Intent i=new Intent(MainActivity.this,WelcomeActivity.class);
            startActivity(i);
            finish();

        }


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e=etUserName.getText().toString();
                String p=etPassword.getText().toString();

               firebaseAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {

                       if(task.isSuccessful()){
                           Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                           Intent i=new Intent(MainActivity.this,WelcomeActivity.class);
                           startActivity(i);
                           finish();
                       }
                       else{
                           Toast.makeText(MainActivity.this, "Login issue"+task.getException(), Toast.LENGTH_SHORT).show();
                       }

                   }
               });

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(i);

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(MainActivity.this,ResetActivity.class);
                startActivity(i);

            }
        });

    }
}
