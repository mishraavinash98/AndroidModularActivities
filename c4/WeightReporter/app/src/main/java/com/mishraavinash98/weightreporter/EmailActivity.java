package com.mishraavinash98.weightreporter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity {

    TextView tvEmailMsg;
    EditText etEmail,etSubject;
    Button btnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        tvEmailMsg=(TextView)findViewById(R.id.tvEmailMsg);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etSubject=(EditText)findViewById(R.id.etSubject);
        btnEmail=(Button)findViewById(R.id.btnEmail);

        Intent intent=getIntent();
        String n=intent.getStringExtra("n");
        String w=intent.getStringExtra("w");
        tvEmailMsg.setText("Name :"+n+"\n Weight :"+w);

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e=etEmail.getText().toString();
                String s=etSubject.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
                    etEmail.setError("Invalid email");
                    etEmail.setText("");
                    etEmail.requestFocus();
                    return;
                }
                if(s.length()==0){
                    etSubject.setError("Subject Empty");
                    etSubject.requestFocus();
                    return;
                }
                //implicit intent
                Intent i1=new Intent(Intent.ACTION_SENDTO);
                i1.setData(Uri.parse("mailto:" +e));
                i1.putExtra(Intent.EXTRA_TEXT,tvEmailMsg.getText().toString());
                i1.putExtra(Intent.EXTRA_SUBJECT,s);
                startActivity(i1);

            }
        });


    }
}
