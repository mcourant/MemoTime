package com.example.maximecours.memotime.metiers.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maximecours.memotime.metiers.Base;
import com.example.maximecours.memotime.metiers.dbhelpers.DatabaseHelper;
import com.example.maximecours.memotime.metiers.dto.MemoDTO;

import java.util.ArrayList;
import java.util.List;

public class MemoDAO
{

    /**
     * Retourne la liste de courses.
     * @param context Context
     * @return Liste de CourseDTO
     */
    public List<MemoDTO> getListeMemos(Context context)
    {
        // projection (colonnes utilisées après la requète) :
        String[] projection = {Base.MemosContrat._ID,
                Base.MemosContrat.COLONNE_NAME};

        // accès en lecture (query) :
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // requête :
        Cursor cursor = db.query(
                Base.MemosContrat.TABLE_MEMOS,	// table sur laquelle faire la requète
                projection,									// colonnes à retourner
                null,										// colonnes pour la clause WHERE (inactif)
                null,										// valeurs pour la clause WHERE (inactif)
                null,										// GROUP BY (inactif)
                null,										// HAVING (inactif)
                null,										// ordre de tri
                null);										// LIMIT (inactif)

        // construction de la liste de courses
        List<MemoDTO> listeMemos = new ArrayList<>();
        if (cursor != null)
        {
            try
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    // conversion des données remontées en un objet métier :
                    listeMemos.add(MemoDTO.getMemoDepuisCursor(cursor));
                    cursor.moveToNext();
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            finally
            {
                cursor.close();
            }
        }

        return listeMemos;
    }

}
