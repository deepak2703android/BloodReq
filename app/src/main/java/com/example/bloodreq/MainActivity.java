package com.example.bloodreq;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import static android.provider.ContactsContract.Intents.Insert.ACTION;

public class MainActivity extends AppCompatActivity implements   AdapterView.OnItemSelectedListener {

    EditText name,location,hospital,contact,message;
    Button Submit;
    Spinner bloodgrp,units;
    boolean connected = false;
    private BroadcastReceiver mReceiver = new BroadcastReceiver();

        String  name1,location1,hospital1,contact1,message1,bloodgrp1,units1;

    String[] Bloodgrp = { "Select Blood Group","A+", "A-", "B+", "B-", "O+","O-","AB+","AB-","If others mention in Message Box" };
    String[] Units = { "Select Units","1", "2", "3", "4", "5","More Than 5"};

    PendingIntent pendingIntent;




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.entername);
        location = findViewById(R.id.locedittext);
        hospital = findViewById(R.id.Hospitaledittext);
        contact = findViewById(R.id.conactedittext);
        message = findViewById(R.id.messageedittext);
        Submit = findViewById(R.id.SubmitBUtton);
        bloodgrp = findViewById(R.id.bloodgrpspinner);
        units = findViewById(R.id.noofunitsspinner);

        bloodgrp.setOnItemSelectedListener(this);
        units.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Bloodgrp);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodgrp.setAdapter(aa);

        ArrayAdapter bb = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Units);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        units.setAdapter(bb);


        //final Intent intent = new Intent(this, BroadcastReceiver.class);
   //     pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, intent, 0);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              name1=  name.getText().toString().trim();
                location1 =   location.getText().toString().trim();
                hospital1= hospital.getText().toString().trim();
                contact1= contact.getText().toString().trim();
                message1=message.getText().toString().trim();
                 bloodgrp1 = bloodgrp.getSelectedItem().toString();
                units1=units.getSelectedItem().toString();

                System.out.println("blood"+bloodgrp1+" "+units1);


                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                }
                else
                    connected = false;




                if (connected==true) {

                    if (name1.isEmpty() || location1.isEmpty() || hospital1.isEmpty() || contact1.isEmpty() || message1.isEmpty() || bloodgrp1.equals("Select Blood Group") || units1.equals("Select Units")) {
                        Toast.makeText(MainActivity.this, "Fill All Details", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_SHORT).show();

                        Submit.setEnabled(false);

                       AlertDialog.Builder builder
                                = new AlertDialog
                                .Builder(MainActivity.this);
                        builder.setMessage("Did you recieve any update on your request?");
                        builder.setTitle("Confirmtion !");
                        builder.setCancelable(false);
                        builder
                                .setPositiveButton(
                                        "Yes",
                                        new DialogInterface
                                                .OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                Toast.makeText(MainActivity.this, "Get Well soon", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                        builder
                                .setNegativeButton(
                                        "No",
                                        new DialogInterface
                                                .OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which) {

                                                dialog.cancel();
                                                seconddialog();
                                            }
                                        });
                     final AlertDialog alertDialog = builder.create();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                           //     sendBroadcast(new Intent(MainActivity.this, BroadcastReceiver.class));

                                alertDialog.show();


                            }
                        }, 7000);


                    }

                }
                else if (connected==false){
                    Toast.makeText(MainActivity.this, "Make sure your internet Connection", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void seconddialog() {

        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);
        builder.setMessage("Did you recieve any update on your request?");
        builder.setTitle("Confirmtion !");
        builder.setCancelable(false);
        builder
                .setPositiveButton(
                        "Yes",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast.makeText(MainActivity.this, "Get Well soon", Toast.LENGTH_SHORT).show();
                            }
                        });

        builder
                .setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                dialog.cancel();
                                thirddialog();
                            }
                        });
        final AlertDialog alertDialog = builder.create();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
            //    sendBroadcast(new Intent(MainActivity.this, BroadcastReceiver.class));

                alertDialog.show();


            }
        }, 7000);

    }

    private void thirddialog() {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);
        builder.setMessage("Did you recieve any update on your request?");
        builder.setTitle("Confirmtion !");
        builder.setCancelable(false);
        builder
                .setPositiveButton(
                        "Yes",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast.makeText(MainActivity.this, "Get Well soon", Toast.LENGTH_SHORT).show();
                            }
                        });

        builder
                .setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                dialog.cancel();
                                Toast.makeText(MainActivity.this, "Send request again", Toast.LENGTH_SHORT).show();
                                //seconddialog();
                                Submit.setEnabled(true);
                            }
                        });
        final AlertDialog alertDialog = builder.create();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
             //   sendBroadcast(new Intent(MainActivity.this, BroadcastReceiver.class));

                alertDialog.show();


            }
        }, 7000);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Select Blood Group & Units", Toast.LENGTH_SHORT).show();

    }




}
