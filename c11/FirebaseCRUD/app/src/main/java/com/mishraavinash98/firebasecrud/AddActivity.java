package com.mishraavinash98.firebasecrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    EditText etAddRollNo,etAddName;
    Button btnAddSave;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etAddRollNo=(EditText)findViewById(R.id.etAddRollNo);
        etAddName=(EditText)findViewById(R.id.etAddName);
        btnAddSave=(Button)findViewById(R.id.btnAddSave);
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("student");


        btnAddSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String r=etAddRollNo.getText().toString();
                String n=etAddName.getText().toString();

                Student s=new Student(Integer.parseInt(r),n);
                myRef.push().setValue(s);
                Toast.makeText(AddActivity.this, "Record Added", Toast.LENGTH_SHORT).show();

                etAddRollNo.setText("");
                etAddName.setText("");
                etAddRollNo.requestFocus();

            }
        });


    }
}
