package com.mishraavinash98.studentinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteActivity extends AppCompatActivity {

    EditText etDeleteRno;
    Button btnDeleteSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        etDeleteRno=(EditText)findViewById(R.id.etDeleteRno);
        btnDeleteSave=(Button)findViewById(R.id.btnDeleteSave);

        btnDeleteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String rno=etDeleteRno.getText().toString();
                if(rno.length()==0){//validation
                    etDeleteRno.setError("Roll No is empty");
                    etDeleteRno.requestFocus();return;
                }

                //delete call
                MainActivity.db.deleteRecord(Integer.parseInt(rno));
                //clearing data
                etDeleteRno.setText("");
                etDeleteRno.requestFocus();

            }
        });

    }
}
