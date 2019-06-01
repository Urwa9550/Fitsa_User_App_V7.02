package com.wasisoft.fitsa_user_app;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wasisoft.fitsa_user_app.Helpers.FirestoreHelper;
import com.wasisoft.fitsa_user_app.Interfaces.ImageListener;
import com.wasisoft.fitsa_user_app.Model.Nutritionist_items;
import com.wasisoft.fitsa_user_app.Utils.Keys;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class Nutritionist_Signup extends AppCompatActivity implements ImageListener {

    private EditText name;
    private EditText qualification;
    private EditText experience;
    private EditText institute;
    String imagePath;
    Uri uri;
    ImageView pro_pic;
    private Button submit;
    Nutritionist_items model;
    private String userId;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private String mCurrentUserId;


//    FirebaseFirestore fsdb;
//    StorageReference mStorageRef;
//    FirestoreDataListener mListener;
//

    // Database Reference
    private FirestoreHelper mFirestoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritionist__signup);
        //fsdb = FirebaseFirestore.getInstance();

        mFirestoreHelper = FirestoreHelper.getInstance();
        mFirestoreHelper.setImageListener(this);

        initViews();
        //initRef();
        onClick();

    }

    private void initRef(){
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        if(mCurrentUser != null){
            mCurrentUserId = mCurrentUser.getUid();
        }
    }

    private void initViews() {

        name=findViewById(R.id.nutri_name);
        qualification=findViewById(R.id.nutri_qlf);
        experience=findViewById(R.id.nutri_exp);
        institute=findViewById(R.id.nutri_inst);
        pro_pic=findViewById(R.id.nutri_pic);
        submit=findViewById(R.id.nutri_submit);
    }

    private void onClick() {
        pro_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromGallery();
                // uploadPicToFirebaseStorage(Nutritionist_Signup.this, model);

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!areFieldsEmpty(name,qualification,experience,institute)){

                    userId = UUID.randomUUID().toString();

                    if(uri != null){
                        mFirestoreHelper.uploadImage(Keys.PROFILE_IMAGE_KEY, uri, userId);
                    }

                    populateNutritionistObject();

                    mFirestoreHelper.updateFirestoreData(Keys.NUTRITIONIST_COLLECTION,userId, model);
                }
            }
        });
    }

    private void populateNutritionistObject() {
        String nutri_name = name.getText().toString().trim();
        String nutri_qualification = qualification.getText().toString().trim();
        String nutri_institute = institute.getText().toString().trim();
        String nutri_experience = experience.getText().toString();
        String uid=UUID.randomUUID().toString();

        String pic = uri.toString();

        model = new Nutritionist_items(nutri_name, nutri_qualification, nutri_institute, nutri_experience, pic,uid);
    }

    private boolean areFieldsEmpty(EditText... fields) {

        boolean yesAllEmpty = true;

        for(EditText field : fields){
            if(!TextUtils.isEmpty(field.getText().toString())){
                yesAllEmpty = false;
            }else {
                yesAllEmpty = true;
                field.setError("Field is required!");
                break;
            }
        }

        return yesAllEmpty;
    }

//
//    private void uploadPicToFirebaseStorage(Uri uri,String filename) {
//
//        if (uri != null) {
//            String uid=UUID.randomUUID().toString();
//            mStorageRef = FirebaseStorage.getInstance().getReference();
//            //        final String filename=System.currentTimeMillis()+"";
//            //show file name as a string
//            //  final String filename= UUID.randomUUID().toString();
//            //path to the Storage Reference
//            StorageReference nutri_img_ref = mStorageRef.child("nutritionist/images").child(uid);
//
//            nutri_img_ref
//
//                    .putFile(uri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            //download the image from Firebase database
//                              getDownloadUri(taskSnapshot);
//
//                            Toast.makeText(Nutritionist_Signup.this, "Image Uploaded Successfully", Toast.LENGTH_LONG).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception exception) {
//                            // Handle unsuccessful uploads
//                            // ...
//                            Toast.makeText(Nutritionist_Signup.this, "Exception occured : " + exception, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
//
//        else{
//        Toast.makeText(Nutritionist_Signup.this, "File not found", Toast.LENGTH_SHORT).show();
//    }
//}
//
//    private void getDownloadUri(UploadTask.TaskSnapshot taskSnapshot) {
//        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                mListener.onNutritionistDataFetched(uri);
//
//    }


//    private void uploadPicToFirebaseStorage(Nutritionist_Signup nutritionist_signup, Nutritionist_items model) {
//
//
//
//    }
//
//    private void uploadToFirestore(Nutritionist_items model) {
//
//        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("Name",model.getmItemName() );
//        user.put("Qualification",model.getmQualification() );
//        user.put("Experience", model.getmExperience());
//        user.put("Institute", model.getmInstitute());
//        user.put("Image", model.getmImageUriStrl());
//        user.put("uid", model.getUid());
//
////CollectionReference cref = fsdb.collection("users");
//// Add a new document with a generated ID
//
//        fsdb
//                .collection("nutritionists")
//                .document()
//                .set(user)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(Nutritionist_Signup.this, "upload ", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(Nutritionist_Signup.this, "Failed: "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }


    private void getImageFromGallery() {
        // open gallery
        // select an image
        // set Image to the image view
        // then get the uri of image and upload the image to firebase storage
        // then pass the url of image from the storage to firestore

        new ImagePicker
                .Builder(Nutritionist_Signup.this)
                .allowMultipleImages(false)
                .allowOnlineImages(false)
                .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
                .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                .scale(100,100)
                .directory(ImagePicker.Directory.DEFAULT)
                .enableDebuggingMode(true)
                .extension(ImagePicker.Extension.JPG)
                .build();
        Toast.makeText(this, "Pick Image", Toast.LENGTH_SHORT).show();

    }

    private void loadImageIntoimageview() {
        Glide
                .with(Nutritionist_Signup.this)
                .load(uri)
                .into(pro_pic);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && data != null){

                List<String> selectedImagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);

                imagePath = selectedImagePaths.get(0);
               // pro_pic.setText(imagePath);
                File file = new File(imagePath);
                uri = Uri.fromFile(file);
                loadImageIntoimageview();
            }
        }

    }

    @Override
    public void onImageUploaded(Uri downloadUri) {
        if(downloadUri != null)
            uri = downloadUri;
    }

}
