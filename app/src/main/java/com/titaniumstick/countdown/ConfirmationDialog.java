package com.titaniumstick.countdown;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Tio on 8/4/2015.
 */
public class ConfirmationDialog extends DialogFragment {

    public interface NoticeDialogListener {
        public void onNoticeClick( DialogFragment dialog);
    }

    NoticeDialogListener mListener  ;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (NoticeDialogListener) activity;
        }catch ( ClassCastException e ) {
            throw new ClassCastException(activity.toString() + "must Implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


       AlertDialog.Builder builder =new  AlertDialog.Builder(getActivity());
        builder.setTitle("No Break?")
                .setMessage("Are you sure?")
                .setPositiveButton("I AM!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onNoticeClick(ConfirmationDialog.this);

                    }
                })
                .setNegativeButton("IT'S A MISTAKE!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ;

                    }
                });

        return builder.create();

        }
    }

