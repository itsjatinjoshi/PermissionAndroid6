package com.example.permissionandroid6;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final int UNIQUE_REQUEST_CODE=383;
    Button btnPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPermission= findViewById(R.id.btnPermission);
        btnPermission.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    UNIQUE_REQUEST_CODE);
        }
        else{
            Toast.makeText(MainActivity.this, "Permission Granted !! Thank You", Toast.LENGTH_SHORT).show();
        }


            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode== UNIQUE_REQUEST_CODE){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED ){
                Toast.makeText(MainActivity.this, "Thank You! Permission Granted", Toast.LENGTH_LONG).show();
            }
            else if(grantResults[0]== PackageManager.PERMISSION_DENIED)
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setMessage("Permission is IMPORTANT to save messages!!").setTitle("IMPORTANT MESSAGE");

                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    UNIQUE_REQUEST_CODE);

                        }
                    });
                    alert.setNegativeButton("No Thank You", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Cannot Granted", Toast.LENGTH_LONG).show();
                        }
                    });
                    alert.show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Will never ask again !!", Toast.LENGTH_LONG).show();
                }
            }


        }
    }
}
