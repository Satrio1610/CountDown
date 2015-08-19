package com.titaniumstick.countdown;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Tio on 7/28/2015.
 */
public class MinuteDialog extends DialogFragment {
    String[] number ;
    String result;
    public interface NoticeDialogListener {
        public void onClick( DialogFragment dialog);
    }

    NoticeDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // verify that the host activity implements the callback interface

        try {
            mListener = (NoticeDialogListener) activity;
        }catch ( ClassCastException e ) {
            throw new ClassCastException(activity.toString() + "must Implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        number = getResources().getStringArray(R.array.cycle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Number of Cycle")
                .setItems(number, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result = number[which];
                        mListener.onClick(MinuteDialog.this);
                    }
                });
        return builder.create();
    }




}
