package com.example.maximecours.memotime.metiers.dto;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.maximecours.memotime.metiers.Base;

import java.util.ArrayList;


public class MemoDTO extends ArrayList<Parcelable> implements Parcelable
{

    // Attributs :
    private String name;

    public MemoDTO(String name)
    {
        this.name = name;
    }

    protected MemoDTO(Parcel in) {
        name = in.readString();
    }

    public static final Creator<MemoDTO> CREATOR = new Creator<MemoDTO>() {
        @Override
        public MemoDTO createFromParcel(Parcel in) {
            return new MemoDTO(in);
        }

        @Override
        public MemoDTO[] newArray(int size) {
            return new MemoDTO[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
