package com.titaniumstick.countdown;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by Tio on 6/26/2015.
 */
public class TimeDialog extends DialogFragment {

    String[] sixty =    {
                         "00","01","02","03","04","05",
                         "06","07","08","09","10","11",
                         "12","13","14","15","16","17",
                         "18","19","20","21","22","23",
                         "24","25","26","27","28","29",
                         "30","31","32","33","34","35",
                         "36","37","38","39","40","41",
                         "42","43","44","45","46","47",
                         "48","49","50","51","52","53",
                         "54","55","56","57","58","59",};





    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.minute);

            builder.setItems(sixty, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {



                }
            });

        return super.onCreateDialog(savedInstanceState);
    }
}
