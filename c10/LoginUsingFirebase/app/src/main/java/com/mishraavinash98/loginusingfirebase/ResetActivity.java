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
import com.google.firebase.auth.FirebaseAuth;

public class ResetActivity extends AppCompatActivity {

    EditText etResetEmail;
    Button btnResetSend;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        etResetEmail=(EditText)findViewById(R.id.etResetEmail);
        btnResetSend=(Button)findViewById(R.id.btnResetSend);
        firebaseAuth=firebaseAuth.getInstance();

        btnResetSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e=etResetEmail.getText().toString();

                firebaseAuth.sendPasswordResetEmail(e).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(ResetActivity.this, "check email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ResetActivity.this,MainActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(ResetActivity.this, "bass yahi toh galat baat hai", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }
}
