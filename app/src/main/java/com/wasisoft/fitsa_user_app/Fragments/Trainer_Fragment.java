package com.wasisoft.fitsa_user_app.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wasisoft.fitsa_user_app.Adaptors.Exercise_adaptor;
import com.wasisoft.fitsa_user_app.Model.Exercise_items;
import com.wasisoft.fitsa_user_app.R;

import java.util.List;

public class Trainer_Fragment extends Fragment {

    private RecyclerView mRecyclerView;
    private Exercise_adaptor mAdapter;

    private List<Exercise_items> myExItemList;
//     private LinearLayout lay;




    public Trainer_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.trainer_fragment, container, false);



//        mRecyclerView = view.findViewById(R.id.both_rv);
//        myItemsList = new ArrayList<>();
//
//        myItemsList.add(new MyItems("Ben 10",R.drawable.ben));
//        myItemsList.add(new MyItems("Boruto Uzumaki",R.drawable.boruto));
//        myItemsList.add(new MyItems("Jun Kazama",R.drawable.jun));
//        myItemsList.add(new MyItems("Micky",R.drawable.micky));
//        myItemsList.add(new MyItems("Tyson",R.drawable.tyson));
//        myItemsList.add(new MyItems("Spidy",R.drawable.spidy));
//        myItemsList.add(new MyItems("Pooh",R.drawable.pooh));
//        myItemsList.add(new MyItems("Toon",R.drawable.toon));
//
//        mAdapter = new MyRecyclerViewAdapter(getContext(), myItemsList);
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecyclerView.setAdapter(mAdapter);
        return view;

    }



}
