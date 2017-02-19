package com.example.maximecours.memotime.metiers.dbhelpers;

/**
 * Created by MaximeCours on 14/02/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maximecours.memotime.metiers.Base;

public class DatabaseHelper extends SQLiteOpenHelper
{

    // Constantes :
    private static final String NOM_DATABASE = "memos.db";
    private static final int VERSION_DATABASE = 2;

    public DatabaseHelper(Context context)
    {
        super(context, NOM_DATABASE, null, VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE memos (" +
                "    _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    name TEXT" +
                ")");
        db.execSQL("INSERT INTO " + Base.MemosContrat.TABLE_MEMOS + " VALUES (NULL,'Manger le goûter')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Base.MemosContrat.TABLE_MEMOS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

}
