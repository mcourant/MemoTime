package com.example.maximecours.memotime.fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.maximecours.memotime.R;
import com.example.maximecours.memotime.activities.MainActivity;
import com.example.maximecours.memotime.alert.AddMemoAlert;
import com.example.maximecours.memotime.metiers.dao.MemoDAO;
import com.example.maximecours.memotime.metiers.dto.MemoDTO;
import com.example.maximecours.memotime.recyclerview.adapter.MemoAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListeFragment extends Fragment implements View.OnClickListener, RecyclerView.OnItemTouchListener {

    Button addMemoButton;
    GestureDetector gestureDetector;

    List<MemoDTO> listeCourseDTOs;
    RecyclerView.Adapter mAdapter;
    RecyclerView mRecyclerView;

    public final static int ADD_MEMO = 100;
    public final static int UPDATE_MEMO = 110;
    public final static int DELETE_MEMO = 120;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste, container, false);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addMemoButton = (Button) getActivity().findViewById(R.id.ajoutermemo);

        addMemoButton.setOnClickListener(this);

        // accès à la base de données :
        MemoDAO memosDAO = new MemoDAO();
        listeCourseDTOs = memosDAO.getListeMemos(getContext());

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerviewlistmemo);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MemoAdapter(listeCourseDTOs);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(this);

        mRecyclerView.addItemDecoration(new
                DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));

        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent event) {
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (getActivity() instanceof MainActivity) {
            AddMemoAlert addMemo = new AddMemoAlert();
            Bundle bundle = new Bundle();
            addMemo.setArguments(bundle);
            addMemo.setTargetFragment(this,ADD_MEMO);
            addMemo.show(getActivity().getSupportFragmentManager(), "exemple");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == ADD_MEMO){
            listeCourseDTOs.add(new MemoDTO(data.getStringExtra("monMemoAdd")));
            mAdapter.notifyDataSetChanged();
        }else if(resultCode == UPDATE_MEMO){
            int position = data.getIntExtra("position",0);
            MemoDTO memoTmp = new MemoDTO(data.getStringExtra("monMemoUpdate"));
            listeCourseDTOs.set(position,memoTmp);
            mAdapter.notifyDataSetChanged();
        }else if(resultCode == DELETE_MEMO){
            int position = data.getIntExtra("position",0);
            listeCourseDTOs.remove(position);
            mAdapter.notifyDataSetChanged();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        if (gestureDetector.onTouchEvent(e)) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                int position = rv.getChildAdapterPosition(child);
                AddMemoAlert addMemo = new AddMemoAlert();
                Bundle argument = new Bundle();
                argument.putParcelableArrayList("memo", listeCourseDTOs.get(position));
                argument.putInt("position",position);
                addMemo.setArguments(argument);
                addMemo.setTargetFragment(this,UPDATE_MEMO);
                addMemo.show(getActivity().getSupportFragmentManager(), "exemple");
                return true;
            }
        }
        return false;

    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
