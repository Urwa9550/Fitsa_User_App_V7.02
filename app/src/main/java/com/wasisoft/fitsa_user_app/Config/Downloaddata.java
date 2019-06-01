package com.wasisoft.fitsa_user_app.Config;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wasisoft.fitsa_user_app.Interfaces.FirestoreDataListener;
import com.wasisoft.fitsa_user_app.Model.GeneralUser;
import com.wasisoft.fitsa_user_app.Model.Nutritionist_items;
import com.wasisoft.fitsa_user_app.Utils.Keys;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Downloaddata {
//singleton to make only one instance of this data "mInstance"
    //I think its an extra helper
    private static Downloaddata mInstance;
    private FirebaseFirestore mFirestoreRef;

    public static Downloaddata getInstance(){
        if(mInstance == null){
            //if instance is null then make new instance
            mInstance = new Downloaddata();
        }
        return mInstance;
    }

    private FirestoreDataListener mListener;

    public void setListener(FirestoreDataListener listener){
        mListener = listener;
    }

    private Downloaddata(){
        mFirestoreRef = FirebaseFirestore.getInstance();
    }

    public void retrieveSingleData(String collection, String document){

    }

    public void retrieveAllData(String collection){

        mFirestoreRef.collection(collection)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                        }
                        else{
                            saveFetchedData(queryDocumentSnapshots);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void addSingleData(String collection, String document, GeneralUser userObj){

        Map<String, Object> obj = new HashMap<>();

        mFirestoreRef.collection(collection)
                .document(document)
                /* .add(obj) */
                .set(obj)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private void saveFetchedData(QuerySnapshot result){

        final List<Nutritionist_items> list = new ArrayList<>();

        for(QueryDocumentSnapshot qdss : result){

            if(qdss.exists()){
                // Data Retrieved

                Nutritionist_items nit = qdss.toObject(Nutritionist_items.class);

//                Map retrievedMapData = qss.getData();
//
//                Nutritionist_items nit = new Nutritionist_items();
//
//                String name = (String) retrievedMapData.get(Keys.NAME_KEY);
//                String exp = (String) retrievedMapData.get(Keys.EXPERIENCE_KEY);
//                String qualification = (String) retrievedMapData.get(Keys.QUALIFICATION_KEY);
//                String image = (String) retrievedMapData.get(Keys.IMAGE_KEY);
//                String institute = (String) retrievedMapData.get(Keys.INSTITUTE_KEY);
//
//                nit.setmItemName(name);
//                nit.setmExperience(Integer.parseInt(exp));
//                nit.setmQualification(qualification);
//                nit.setmImageUriStrl(image);
//                nit.setmInstitute(institute);

                list.add(nit);
            }
        }

        mListener.onNutritionistDataFetched(list);
    }

}
