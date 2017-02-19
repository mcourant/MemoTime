package com.example.maximecours.memotime.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.maximecours.memotime.R;


/**
 * Created by MaximeCours on 07/02/2017.
 */

public class ViewHolderMemo extends RecyclerView.ViewHolder {

    private TextView textmemo;

    public ViewHolderMemo(View itemView) {
        super(itemView);
        textmemo = (TextView) itemView.findViewById(R.id.item_text_memo);
    }

    public TextView getTextmemo(){
        return textmemo;
    }
}
