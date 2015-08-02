package com.titaniumstick.countdown;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Tio on 8/2/2015.
 */
public class BreakDialog extends DialogFragment {
    String[] number;
    String[] hiddenNumber;
    String newResult;
    String newView;

    public interface NoticeDialogListener {
        public void onBreakClick( DialogFragment dialog);
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
        number = getResources().getStringArray(R.array.breakTime);
        hiddenNumber = getResources().getStringArray(R.array.hiddenHour);

        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Don't forget to take a Break!")
                .setItems(number,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newResult = hiddenNumber[which];
                        newView = number[which];
                        mListener.onBreakClick(BreakDialog.this);
                    }
                });
        return builder.create();
    }
}
