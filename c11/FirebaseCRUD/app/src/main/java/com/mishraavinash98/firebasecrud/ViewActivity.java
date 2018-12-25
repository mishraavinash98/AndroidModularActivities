package com.mishraavinash98.firebasecrud;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ListView lvData;
    ArrayList<Student> s=new ArrayList<Student>();
    ArrayAdapter<Student> ad;
    FirebaseDatabase database;
    DatabaseReference myRef;

    ArrayList<String> k =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        lvData=(ListView)findViewById(R.id.lvData);
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("student");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                s.clear();
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    Student data=d.getValue(Student.class);
                    s.add(data);

                    k.add(d.getKey());//gets unique key from firebase
                }

                ad=new ArrayAdapter<Student>(ViewActivity.this,android.R.layout.simple_list_item_1,s);

                lvData.setAdapter(ad);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lvData.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Student st=s.get(i);
                showUpdateDeleteDialog(k.get(i),st.getRno(),st.getName());

                return true;
            }
        });
    }
    public void showUpdateDeleteDialog(final String k,int rollno,String name){
        AlertDialog.Builder ab=new AlertDialog.Builder(this);
        LayoutInflater inflater=getLayoutInflater();
        View dialogView=inflater.inflate(R.layout.uddialog,null);
        ab.setView(dialogView);

        final EditText etUDRollNo=(EditText)dialogView.findViewById(R.id.etUDRollNo);
        final EditText etUDName=(EditText)dialogView.findViewById(R.id.etUDName);
        Button btnUDUpdate=(Button)dialogView.findViewById(R.id.btnUDUpdate);
        Button btnUDDelete=(Button)dialogView.findViewById(R.id.btnUDDelete);

        ab.setTitle(" "+rollno);
        final AlertDialog a=ab.create();
        a.show();

        btnUDUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference dl=FirebaseDatabase.getInstance().getReference("student").child(k);

                String rollno=etUDRollNo.getText().toString();
                String name=etUDName.getText().toString();

                Student s=new Student(Integer.parseInt(rollno),name);
                dl.setValue(s);
                Toast.makeText(ViewActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
                a.dismiss();
            }
        });

        btnUDDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference dl=FirebaseDatabase.getInstance().getReference("student").child(k);
                dl.removeValue();
                Toast.makeText(ViewActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
