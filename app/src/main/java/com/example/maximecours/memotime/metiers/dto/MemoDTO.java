package com.example.maximecours.memotime.metiers.dto;

import android.database.Cursor;

import com.example.maximecours.memotime.metiers.Base;


public class MemoDTO
{

    // Attributs :
    private String name;

    public MemoDTO(String name)
    {
        this.name = name;
    }

    public static MemoDTO getMemoDepuisCursor(Cursor cursor)
    {
        return new MemoDTO(
                cursor.getString(cursor.getColumnIndex(Base.MemosContrat.COLONNE_NAME))
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
