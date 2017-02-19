package com.example.maximecours.memotime.alert;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.maximecours.memotime.R;

/**
 * Created by MaximeCours on 14/02/2017.
 */

public class AddMemoAlert extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        String test = getArguments().getString("test");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.ajouter)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.supprimer, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setView(R.layout.alertdialogcustom);
        return builder.create();
    }


}
