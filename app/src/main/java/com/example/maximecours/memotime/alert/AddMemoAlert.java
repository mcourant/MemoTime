package com.example.maximecours.memotime.alert;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maximecours.memotime.R;
import com.example.maximecours.memotime.metiers.Base;
import com.example.maximecours.memotime.metiers.dbhelpers.DatabaseHelper;
import com.example.maximecours.memotime.metiers.dto.MemoDTO;

import java.util.ArrayList;

/**
 * Created by MaximeCours on 14/02/2017.
 */

public class AddMemoAlert extends DialogFragment {

    TextView edittextMemo;
    MemoDTO memo;
    SQLiteDatabase db;
    String memoName;
    int position;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (getArguments() != null) {
            memo = (MemoDTO) getArguments().getParcelableArrayList("memo");
            position = getArguments().getInt("position");
            if(memo != null){
                memoName = memo.getName();
            }
        }

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        db = databaseHelper.getReadableDatabase();

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View alertView = inflater.inflate(R.layout.alertdialogcustom, null, false);
        edittextMemo = (TextView) alertView.findViewById(R.id.editTextMemo);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ContentValues values = new ContentValues();
                        if (memo == null && (!edittextMemo.getText().equals(""))) {
                            String value = edittextMemo.getText().toString();
                            values.put(Base.MemosContrat.COLONNE_NAME, value);
                            long id = db.insert(Base.MemosContrat.TABLE_MEMOS, null, values);
                            Toast.makeText(getContext(), "Valeur insérée :" + value, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent();
                            intent.putExtra("monMemoAdd",edittextMemo.getText().toString());
                            getTargetFragment().onActivityResult(getTargetRequestCode(),100,intent);

                        } else if(memo != null) {
                            values.put(Base.MemosContrat.COLONNE_NAME, edittextMemo.getText().toString());
                            String selection = Base.MemosContrat.COLONNE_NAME + " = ? ";
                            String[] selectionArgs = {memoName};
                            int count = db.update(Base.MemosContrat.TABLE_MEMOS, values, selection, selectionArgs);
                            Toast.makeText(getContext(), "Valeur mise a jour :" + edittextMemo.getText().toString(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent();
                            intent.putExtra("monMemoUpdate",edittextMemo.getText().toString());
                            intent.putExtra("position",position);
                            getTargetFragment().onActivityResult(getTargetRequestCode(),110,intent);
                        }else{
                            Toast.makeText(getContext(), "Veuillez renseigner un memo pour le sauvegarder !", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNeutralButton(R.string.annuler, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setView(alertView);

        if(memo != null){
            builder.setMessage(R.string.modifier)
                    .setNegativeButton(R.string.supprimer, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(memo != null){
                        String selection = Base.MemosContrat.COLONNE_NAME + " = ? ";
                        String[] selectionArgs = {memo.getName()};
                        db.delete(Base.MemosContrat.TABLE_MEMOS, selection, selectionArgs);
                        Toast.makeText(getContext(), "Valeur supprimée :" + memo.getName(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        intent.putExtra("position",position);
                        intent.putExtra("monMemoDelete",edittextMemo.getText().toString());
                        getTargetFragment().onActivityResult(getTargetRequestCode(),120,intent);
                    }
                }
            });
            edittextMemo.setText(memoName);
        }else{
            builder.setMessage(R.string.ajouter);
        }


        return builder.create();
    }



}
