package com.example.maximecours.memotime.metiers;

import android.provider.BaseColumns;


public final class Base{

    private Base() {};

    public static class MemosContrat implements BaseColumns
    {
        public static final String TABLE_MEMOS = "memos";
        public static final String COLONNE_NAME = "name";
    }

}
