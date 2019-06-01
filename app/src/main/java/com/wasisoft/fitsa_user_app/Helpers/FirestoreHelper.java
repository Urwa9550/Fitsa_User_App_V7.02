package com.wasisoft.fitsa_user_app.Helpers;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.wasisoft.fitsa_user_app.Interfaces.FetchUserDataListener;
import com.wasisoft.fitsa_user_app.Interfaces.ImageListener;
import com.wasisoft.fitsa_user_app.Model.GeneralUser;
import com.wasisoft.fitsa_user_app.Model.Nutritionist_items;
import com.wasisoft.fitsa_user_app.Utils.Keys;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirestoreHelper {
// this is the helper class ,steps to make a helper class ...
//a helper class should be instancetiated in itself
    // A listener should be set in it
    // The listener will have all the details that were entered by user
    //when user enter the information ...and  it get uploaded to firebase then helper class is needed to retrieve the data
    // Firestore References
    private FirebaseFirestore mRootDatabase;

    // Firebase Storage Reference
    private FirebaseStorage mRootFirebaseStorage;
    private StorageReference mRootStorageRef;

    private static FirestoreHelper firestoreHelperInstance;

    public static FirestoreHelper getInstance(){
        if(firestoreHelperInstance == null){
            firestoreHelperInstance = new FirestoreHelper();
        }

        return firestoreHelperInstance;
    }

    // Listeners Reference
    private ImageListener mImageListener;
    private FetchUserDataListener mFetchUserDataListener;

    public void setImageListener(ImageListener listener){
        mImageListener = listener;
    }

    public void setFetchUserDataListener(FetchUserDataListener listener){
        mFetchUserDataListener = listener;
    }


    private FirestoreHelper(){
        mRootDatabase = FirebaseFirestore.getInstance();
        mRootFirebaseStorage = FirebaseStorage.getInstance();
        mRootStorageRef = mRootFirebaseStorage.getReference();
    }

    // TODO: Reminder Change Nutritionist_items with general object
    public void updateFirestoreData(String collection, String document, final GeneralUser obj){

        Map<String, Object> mapObj = new HashMap<>();

        mapObj.put(Keys.NAME_KEY, obj.getmItemName());
        mapObj.put(Keys.QUALIFICATION_KEY, obj.getmQualification());
        mapObj.put(Keys.EXPERIENCE_KEY, obj.getmExperience());
        mapObj.put(Keys.INSTITUTE_KEY, obj.getmInstitute());
        mapObj.put(Keys.IMAGE_KEY, obj.getmImageUriStrl());

        mRootDatabase
                .collection(collection)
                .document(document)
                .set(mapObj)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.d("UploadSuccess", "onComplete: "+obj.getmItemName()+" updated Successfully");
                        }else {
                            Log.d("UploadFailed", "onFailure: "+obj.getmItemName()+" Failed "+ Objects.requireNonNull(task.getException()).getMessage());
                        }
                    }
                });
    }


    public void retrieveFirestoreNutritionistData(String collection, final String document){

        mRootDatabase
                .collection(collection)
                .document(document)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            Map<String,Object> map = documentSnapshot.getData();

                            // Save Retrived Data
                            String name = (String) map.get(Keys.NAME_KEY);
                            String qualification = (String) map.get(Keys.QUALIFICATION_KEY);
                            String exp = (String) map.get(Keys.EXPERIENCE_KEY);
                            String institute = (String) map.get(Keys.INSTITUTE_KEY);
                            String imageUriStr = (String) map.get(Keys.IMAGE_KEY);

                            Nutritionist_items nit = new Nutritionist_items();

                            nit.setmItemName(name);
                            nit.setmQualification(qualification);
                            nit.setmInstitute(institute);
                            nit.setmExperience(exp);
                            nit.setmImageUriStrl(imageUriStr);

                            mFetchUserDataListener.onNutritionistDataFetched(nit);
                        }
                    }
                });


    }

    public void retrieveFirestoreNutritionistNameImage(String collection, final String document){

        mRootDatabase
                .collection(collection)
                .document(document)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            Map<String,Object> map = documentSnapshot.getData();

                            // Save Retrived Data
                            String name = (String) map.get(Keys.NAME_KEY);
//                            String qualification = (String) map.get(Keys.QUALIFICATION_KEY);
//                            String exp = (String) map.get(Keys.EXPERIENCE_KEY);
//                            String institute = (String) map.get(Keys.INSTITUTE_KEY);
                            String imageUriStr = (String) map.get(Keys.IMAGE_KEY);

                            Nutritionist_items nit = new Nutritionist_items();

                            nit.setmItemName(name);
//                            nit.setmQualification(qualification);
//                            nit.setmInstitute(institute);
//                            nit.setmExperience(exp);
                            nit.setmImageUriStrl(imageUriStr);

                            mFetchUserDataListener.onNutritionistDataFetched(nit);
                        }
                    }
                });


    }




    public void uploadImage(String folder, Uri imageUri, String userid){
        final String imageName = userid+".jpg";

        mRootStorageRef
                .child(folder)
                .child(imageName)
                .putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.d("Uploaded", "onSuccess: "+imageName+" uploaded Successfully");

                        getDownloadUri(taskSnapshot);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("UploadFailed", "onFailed: "+imageName+" Upload Failed");
                    }
                });


    }

    private void getDownloadUri(UploadTask.TaskSnapshot taskSnapshot) {
        Objects.requireNonNull(Objects.requireNonNull(taskSnapshot
                .getMetadata())
                .getReference())
                .getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        mImageListener.onImageUploaded(uri);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mImageListener.onImageUploaded(null);
                    }
                });
    }


}
