package com.mishraavinash98.pizzaordering;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //declare
    Spinner spnSize;
    CheckBox cbCorn,cbCheese,cbTomato,cbOnion;
    Button btnSend,btnWa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding total 7 items
        spnSize=(Spinner)findViewById(R.id.spnSize);

        cbCorn=(CheckBox)findViewById(R.id.cbCorn);
        cbCheese=(CheckBox)findViewById(R.id.cbCheese);
        cbTomato=(CheckBox)findViewById(R.id.cbTomato);
        cbOnion=(CheckBox)findViewById(R.id.cbOnion);

        btnSend=(Button)findViewById(R.id.btnSend);
        btnWa=(Button)findViewById(R.id.btnWa);

        final ArrayList<String> s =new ArrayList<>(); //create array list
        s.add("Small");
        s.add("Medium");
        s.add("Large");

        //create array adapter
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s);

        //set array adapter
        spnSize.setAdapter(ad);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sizeof pizza
                int i=spnSize.getSelectedItemPosition();
                String size=s.get(i);
                String top ="";

                //toppings of pizza
                if(cbCorn.isChecked()) top+="Corn";
                if(cbCheese.isChecked()) top+="Cheese";
                if(cbTomato.isChecked()) top+="Tomato";
                if(cbOnion.isChecked()) top+="Onion";

                //toast
                String msg="Size"+size+"Toppings"+top;
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();


                //intents
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,msg);

                startActivity(intent);


            }
        });
        //copypasteabove
        btnWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sizeof pizza
                int i = spnSize.getSelectedItemPosition();
                String size = s.get(i);
                String top = "";

                //toppings of pizza
                if (cbCorn.isChecked()) top += "Corn";
                if (cbCheese.isChecked()) top += "Cheese";
                if (cbTomato.isChecked()) top += "Tomato";
                if (cbOnion.isChecked()) top += "Onion";

                //toast
                String msg = "Size" + size + "Toppings" + top;
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();


                //intents
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                intent.putExtra(Intent.EXTRA_TEXT, msg);

                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "BRO tere pass WHATSAPP nahi hai", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
