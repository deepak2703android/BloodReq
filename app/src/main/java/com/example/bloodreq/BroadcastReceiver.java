package com.example.bloodreq;

import android.app.AlertDialog;
import android.app.job.JobScheduler;
import android.app.job.JobService;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;

public class BroadcastReceiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {


        Intent mIntent = new Intent(context,MainActivity.class);
       mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mIntent);
        System.out.println("heyhi");


    }}