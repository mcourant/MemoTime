package com.example.maximecours.memotime.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.maximecours.memotime.R;
import com.example.maximecours.memotime.activities.MainActivity;
import com.example.maximecours.memotime.alert.AddMemoAlert;
import com.example.maximecours.memotime.metiers.dao.MemoDAO;
import com.example.maximecours.memotime.metiers.dto.MemoDTO;
import com.example.maximecours.memotime.recyclerview.adapter.MemoAdapter;

import java.util.List;

public class ListeFragment extends Fragment implements View.OnClickListener {

    Button addMemoButton;

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

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        addMemoButton = (Button) getActivity().findViewById(R.id.ajoutermemo);

        addMemoButton.setOnClickListener(this);

        // accès à la base de données :
        MemoDAO memosDAO = new MemoDAO();
        List<MemoDTO> listeCourseDTOs = memosDAO.getListeMemos(getContext());

        RecyclerView mRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerviewlistmemo);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        final RecyclerView.Adapter mAdapter = new MemoAdapter(listeCourseDTOs);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        if (getActivity() instanceof MainActivity){
            AddMemoAlert addMemo = new AddMemoAlert();
            Bundle argument = new Bundle();
            argument.putString("test","test");
            addMemo.setArguments(argument);
            addMemo.show(getActivity().getSupportFragmentManager(), "exemple");
        }
    }
}
