package com.wasisoft.fitsa_user_app.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wasisoft.fitsa_user_app.Adaptors.Exercise_adaptor;
import com.wasisoft.fitsa_user_app.Model.Exercise_items;
import com.wasisoft.fitsa_user_app.R;

import java.util.ArrayList;
import java.util.List;

public class Exercise_fragment extends Fragment {

    private RecyclerView mRecyclerView;
    private Exercise_adaptor mAdapter;

    private List<Exercise_items> myExItemList;
//     private LinearLayout lay;


    public Exercise_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.exercise_fragment, container, false);


        mRecyclerView = view.findViewById(R.id.exercise_recycler);
        myExItemList = new ArrayList<>();

        myExItemList.add(new Exercise_items("Ben 10","aerobics",R.drawable.jun));
        myExItemList.add(new Exercise_items("Boruto Uzumaki","aerobics",R.drawable.boruto));
        myExItemList.add(new Exercise_items("Jun Kazama","aerobicsdfdg",R.drawable.jun));
        myExItemList.add(new Exercise_items("Micky","aerobicscfdv",R.drawable.jun));
        myExItemList.add(new Exercise_items("Tyson","aerobics13234",R.drawable.boruto));
        myExItemList.add(new Exercise_items("Spidy","aerobics",R.drawable.jun));
        myExItemList.add(new Exercise_items("Pooh","aerobics",R.drawable.boruto));
        myExItemList.add(new Exercise_items("Toon","aerobics",R.drawable.jun));
        myExItemList.add(new Exercise_items("Toon","aerobics",R.drawable.jun));
        myExItemList.add(new Exercise_items("Toon","aerobics",R.drawable.jun));
        myExItemList.add(new Exercise_items("shoon","aerobics",R.drawable.jun));
        myExItemList.add(new Exercise_items("Toon","aerobics",R.drawable.jun));
        myExItemList.add(new Exercise_items("loon","aerobics",R.drawable.jun));

        mAdapter = new Exercise_adaptor(getContext(), myExItemList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
       return view;

    }


}
