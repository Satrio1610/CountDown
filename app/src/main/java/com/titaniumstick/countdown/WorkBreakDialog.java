package com.titaniumstick.countdown;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Tio on 8/2/2015.
 */
// java file to generate dialog to select work/break dialog
public class WorkBreakDialog extends DialogFragment {

    String[] number;
    String[] hiddenNumber;
    String newView;
    String newResult;

    public interface NoticeDialogListener {
        public void onWorkClick( DialogFragment dialog);
    }

    NoticeDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (NoticeDialogListener) activity;
        }catch ( ClassCastException e ) {
            throw new ClassCastException(activity.toString() + "must Implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        number = getResources().getStringArray(R.array.workTime);
        hiddenNumber = getResources().getStringArray(R.array.hiddenHour);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Decide On Your Work Duration!")
                .setItems(number,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newResult = hiddenNumber[which];
                        newView = number[which];
                        mListener.onWorkClick(WorkBreakDialog.this);
                    }
                });
        return builder.create();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
