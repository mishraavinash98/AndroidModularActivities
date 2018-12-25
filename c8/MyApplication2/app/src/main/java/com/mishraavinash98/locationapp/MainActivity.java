package com.mishraavinash98.locationapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks ,GoogleApiClient.OnConnectionFailedListener{

    TextView tvLocation;
    Button btnShare;
    GoogleApiClient gac;
    Location location;

    Button btnTakePicture,btnSendPicture;
    ImageView iv1;
    Bitmap photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Binding
        tvLocation=(TextView)findViewById(R.id.tvLocation);
        btnShare=(Button)findViewById(R.id.btnShare);
        btnShare.setEnabled(false);

        btnSendPicture=(Button)findViewById(R.id.btnSharePicture);
        btnTakePicture=(Button)findViewById(R.id.btnTakePicture);
        iv1=(ImageView)findViewById(R.id.iv1);

        //BuildingAPI

        GoogleApiClient.Builder builder=new  GoogleApiClient.Builder(this);
        builder.addApi(LocationServices.API);
        builder.addConnectionCallbacks(this);
        builder.addOnConnectionFailedListener(this);
        gac=builder.build();


        //sharing

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,"my Location"+tvLocation.getText().toString());
                startActivity(i);
            }
        });

        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,123);

            }
        });

        btnSendPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File f=new File(getExternalCacheDir(),"p1.png");
                try {
                    FileOutputStream fos=new FileOutputStream(f);
                    photo.compress(Bitmap.CompressFormat.PNG,90,fos);
                    fos.flush();
                    fos.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent i=new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_TEXT,tvLocation.getText().toString());
                i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
                i.setType("image/*");
                startActivity(i);

            }
        });

    }

    @Override
    public void onConnected(Bundle bundle) {

        //getting long and lat

        location=LocationServices.FusedLocationApi.getLastLocation(gac);
        if(location!=null){
            double lat=location.getLatitude();
            double lon=location.getLongitude();
            Geocoder g =new Geocoder(this, Locale.ENGLISH);

            try {
                List<android.location.Address> address=g.getFromLocation(lat,lon,1);
                android.location.Address add=address.get(0);

                String msg=add.getCountryName()+" "+add.getAdminArea()+" "+add.getSubAdminArea()
                        +" "+add.getLocality()+" "+add.getSubLocality()+" "+add.getPostalCode();

                tvLocation.setText(msg);

            } catch (IOException e) {
                e.printStackTrace();
            }
            btnShare.setEnabled(true);
        }
        else {
            Toast.makeText(this, "PLEASE ENABLE YOUR GPS", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(gac!=null) gac.connect();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(gac!=null) gac.disconnect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==123){
            photo=(Bitmap)data.getExtras().get("data");
            iv1.setImageBitmap(photo);
        }
    }
}
