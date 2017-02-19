package com.example.maximecours.memotime.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maximecours.memotime.R;
import com.example.maximecours.memotime.metiers.dto.MemoDTO;
import com.example.maximecours.memotime.recyclerview.viewholder.ViewHolderMemo;

import java.util.List;

/**
 * Created by MaximeCours on 07/02/2017.
 */

public class MemoAdapter extends RecyclerView.Adapter<ViewHolderMemo> {

    private List<MemoDTO> allMemos;

    public MemoAdapter(List<MemoDTO> allMemos){
        this.allMemos = allMemos;
    }

    @Override
    public ViewHolderMemo onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewName = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_memo, parent, false);
        return new ViewHolderMemo(viewName);
    }

    @Override
    public void onBindViewHolder(ViewHolderMemo holder, int position) {
        holder.getTextmemo().setText(allMemos.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return allMemos.size();
    }
}
