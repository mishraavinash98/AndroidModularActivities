package com.mishraavinash98.pythontutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChapterActivity extends AppCompatActivity {

    TextView tvChapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        tvChapter=(TextView)findViewById(R.id.tvChapter);

        //get file index from main acti
        Intent intent=getIntent();
        int index=intent.getIntExtra("index",0);
        String filename="c"+(index+1)+".txt";
        String data="",line="";

        try {//reading file content
            InputStreamReader isr=new InputStreamReader(getAssets().open(filename));
            BufferedReader br=new BufferedReader(isr);

            while((line=br.readLine())!=null){
                data=data+line+"\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        tvChapter.setText(data);


    }
}
