package com.mishraavinash98.weightreporter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SmsActivity extends AppCompatActivity {

    TextView tvSmsMsg;
    EditText etPhone;
    Button btnSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        tvSmsMsg=(TextView)findViewById(R.id.tvSmsMsg);
        etPhone=(EditText)findViewById(R.id.etPhone);
        btnSms=(Button)findViewById(R.id.btnSms);

        Intent intent=getIntent();
        String n=intent.getStringExtra("n");
        String w=intent.getStringExtra("w");
        tvSmsMsg.setText("Name :"+n+"\n Weight :"+w);

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String p=etPhone.getText().toString();

                if(p.length()!=10){
                    etPhone.setError("invalid Phone No");
                    etPhone.requestFocus();
                    return;
                }
                //implicit intent
                Intent i1=new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse("sms:" +p));
                i1.putExtra("sms_body",tvSmsMsg.getText().toString());
                startActivity(i1);

            }
        });


    }
}
