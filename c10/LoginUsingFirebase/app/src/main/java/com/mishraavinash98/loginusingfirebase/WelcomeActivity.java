package com.mishraavinash98.loginusingfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    TextView tvUserName;
    Button btnLogout;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvUserName=(TextView)findViewById(R.id.tvUserName);
        btnLogout=(Button)findViewById(R.id.btnLogout);
        firebaseAuth=FirebaseAuth.getInstance();

        String email=firebaseAuth.getCurrentUser().getEmail();
        tvUserName.setText("WELCOME "+email);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent i=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });

    }
}
