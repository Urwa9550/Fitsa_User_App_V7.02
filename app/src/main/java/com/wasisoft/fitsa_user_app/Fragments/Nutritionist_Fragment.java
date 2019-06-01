package com.wasisoft.fitsa_user_app.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.errorprone.annotations.DoNotCall;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wasisoft.fitsa_user_app.Adaptors.Exercise_adaptor;
import com.wasisoft.fitsa_user_app.Adaptors.Nutritionist_adaptor;
import com.wasisoft.fitsa_user_app.Config.Downloaddata;
import com.wasisoft.fitsa_user_app.Interfaces.FirestoreDataListener;
import com.wasisoft.fitsa_user_app.Model.Exercise_items;
import com.wasisoft.fitsa_user_app.Model.Nutritionist_items;
import com.wasisoft.fitsa_user_app.R;
import com.wasisoft.fitsa_user_app.Utils.Keys;

import java.util.List;

public class Nutritionist_Fragment extends Fragment
        implements FirestoreDataListener {

    private RecyclerView mRecyclerView;
    private Downloaddata mDownloadData;

    private List<Nutritionist_items> myNutritionistItemList;
    private final String TAG = "CompressActivity";
//     private LinearLayout lay;

    public Nutritionist_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.nutritionist_fragment, container, false);

        return view;
    }
//after onCreateView, onViewCreated is implemented (onViewCreated optional)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initRef();
    }

    @Override
    public void onStart() {
        super.onStart();

        mDownloadData.retrieveAllData(Keys.NUTRITIONIST_COLLECTION);
    }

    private void initViews(View view) {
        mRecyclerView = view.findViewById(R.id.nutritionist_recycler);
    }

    private void initRef() {
        mDownloadData = Downloaddata.getInstance();
        mDownloadData.setListener(this);
    }


    @Override
    public void onNutritionistDataFetched(List<Nutritionist_items> nutritionistList) {
        if(nutritionistList != null){
            myNutritionistItemList = nutritionistList;
            populateRecyclerView();
        }

    }

    private void populateRecyclerView() {
        Nutritionist_adaptor mAdapter = new Nutritionist_adaptor(getContext(),myNutritionistItemList);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mAdapter);
    }

}
