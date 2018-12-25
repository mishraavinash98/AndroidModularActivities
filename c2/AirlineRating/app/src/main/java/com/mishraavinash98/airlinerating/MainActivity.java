package com.mishraavinash98.airlinerating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgAirline; //declartion
    RatingBar rabRating;
    Button btnSubmit ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgAirline=(RadioGroup)findViewById(R.id.rdAirline); //binding
        rabRating=(RatingBar)findViewById(R.id.rabRating);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id=rgAirline.getCheckedRadioButtonId();
                RadioButton rb=(RadioButton)findViewById(id);
                String a=rb.getText().toString();

                String r=String.valueOf(rabRating.getRating());

                String msg="Airline "+ a +" rating "+r;

                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });


    }
}
