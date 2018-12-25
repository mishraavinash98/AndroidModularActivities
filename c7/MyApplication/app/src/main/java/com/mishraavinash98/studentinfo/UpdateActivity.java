package com.mishraavinash98.studentinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    EditText etUpdateName,etUpdateRno;
    Button btnUpdateSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etUpdateName=(EditText)findViewById(R.id.etUpdateName);
        etUpdateRno=(EditText)findViewById(R.id.etUpdateRno);
        btnUpdateSave=(Button)findViewById(R.id.btnUpdateSave) ;

        btnUpdateSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String rno=etUpdateRno.getText().toString();
                if(rno.length()==0){//validation
                    etUpdateRno.setError("Roll No is empty");
                    etUpdateRno.requestFocus();return;
                }
                String name=etUpdateName.getText().toString();
                //validation
                if(name.length()==0){
                    etUpdateName.setError("Name is empty");
                    etUpdateName.requestFocus();return;
                }


                //update call
                MainActivity.db.updateRecord(Integer.parseInt(rno),name);
                //clearing data
                etUpdateName.setText("");
                etUpdateRno.setText("");
                etUpdateRno.requestFocus();

            }
        });
    }
}
