package com.mishraavinash98.studentinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText etAddRno,etAddName;
    Button btnAddSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etAddRno=(EditText)findViewById(R.id.etAddRno);
        etAddName=(EditText)findViewById(R.id.etAddName);
        btnAddSave=(Button)findViewById(R.id.btnAddSave);

        btnAddSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String rno=etAddRno.getText().toString();
                if(rno.length()==0){//validation
                    etAddRno.setError("Roll No is empty");
                    etAddRno.requestFocus();return;
                }
                String name=etAddName.getText().toString();
                //validation
                if(name.length()==0){
                    etAddName.setError("Name is empty");
                    etAddName.requestFocus();return;
                }


                //insert call
                MainActivity.db.addRecord(Integer.parseInt(rno),name);
                //clearing data
                etAddRno.setText("");
                etAddName.setText("");
                etAddRno.requestFocus();
            }
        });



    }
}
