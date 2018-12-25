package com.mishraavinash98.searchmymovie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    //declare
    EditText etMovie;
    Button btnSubmit;
    TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding
        etMovie=(EditText)findViewById(R.id.etMovie);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);
        tvResult=(TextView)findViewById(R.id.tvResult);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mn=etMovie.getText().toString();//movie name

                MyTask t=new MyTask();//Asyc Task

                String a1="http://www.omdbapi.com/?"; //webiste
                String a2="&apikey=499335bf"; //API
                String a3="&s="+mn; //searching
                String url=a1+a2+a3;
                t.execute(url);


            }
        });

    }

    class MyTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {

            String json="",line="",sr="";

            try{

                //connect to website
                URL url =new URL(strings[0]);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                con.connect();

                //Downloading JSON data
                InputStreamReader isr=new InputStreamReader(con.getInputStream());
                BufferedReader br=new BufferedReader(isr);

                while((line=br.readLine())!=null){
                    json=json+line;
                }

                JSONObject jo=new JSONObject(json);
                JSONArray ja=jo.getJSONArray("Search");//name of the JSON array is Search which contains search results

                for(int i=0;i<ja.length();i++){

                    JSONObject m=ja.getJSONObject(i);

                    String t=m.getString("Title");
                    String y=m.getString("Year");
                    sr=sr+" Title "+t+" Year "+y+"\n";

                }

            }
            catch (Exception e){
                Log.d("AVINASH",e+" ");
            }
            return sr;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvResult.setText(s);

        }
    }
}
