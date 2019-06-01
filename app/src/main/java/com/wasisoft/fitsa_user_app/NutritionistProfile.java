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
import com.wasisoft.fitsa_user_app.Helpers.FieldHelper;
import com.wasisoft.fitsa_user_app.Helpers.FirestoreHelper;
import com.wasisoft.fitsa_user_app.Interfaces.FetchUserDataListener;
import com.wasisoft.fitsa_user_app.Interfaces.ImageListener;
import com.wasisoft.fitsa_user_app.Model.Nutritionist_items;
import com.wasisoft.fitsa_user_app.Model.Trainer;
import com.wasisoft.fitsa_user_app.Utils.Keys;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class NutritionistProfile extends AppCompatActivity
        implements FetchUserDataListener, ImageListener {

    private EditText name;
    private EditText qualification;
    private EditText experience;
    private EditText institute;
    String imagePath;
    Uri uri,duri; /*duri= download uri*/
    ImageView pro_pic;
    private Button submit;
    Nutritionist_items model;
    private String userId;

    // Firebase References
    FirestoreHelper mFirestoreHelper;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private String mCurrentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritionist_profile);

        initViews();
        initRef();
        onClick();
    }

    private void initRef() {
        mFirestoreHelper = FirestoreHelper.getInstance();
        mFirestoreHelper.setImageListener(this);
        mFirestoreHelper.setFetchUserDataListener(this);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        if(mCurrentUser != null)
            mCurrentUserId = mCurrentUser.getUid();
    }

    @Override
    public void onStart() {
        super.onStart();

        if(mCurrentUser != null){
            retrieveNutritionistData();
        }
    }

    private void retrieveNutritionistData() {
        //Retrieving All data from the method declared in helper class
        mFirestoreHelper.retrieveFirestoreNutritionistData(Keys.NUTRITIONIST_COLLECTION, mCurrentUserId);
    }

    private void onClick() {
//change profile image
        pro_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromGallery();
                // uploadPicToFirebaseStorage(Nutritionist_Signup.this, model);

            }
        });
 // update the data
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uri != null){

                    if(!areFieldsEmpty(name,qualification,experience,institute)){

                        if(uri != null){
                            mFirestoreHelper.uploadImage(Keys.PROFILE_IMAGE_KEY, uri, mCurrentUserId);
                        }
//after new data upload to firestore , populate nutritionist objects with new data
                        populateNutritionistObject();

                        mFirestoreHelper.updateFirestoreData(Keys.NUTRITIONIST_COLLECTION, mCurrentUserId, model);
                    }

                    //uploadToFirestore(model);
                    //     uploadPicToFirebaseStorage(Nutritionist_Signup.this, model);
                    //uploadPicToFirebaseStorage(uri,"filename");
                }else {
                    FieldHelper.displayToast(NutritionistProfile.this,"Please upload profile image");

                }
            }
        });


    }

    private void populateNutritionistObject() {
        /*storing new data (the data in the edit text fields) to variables  and pass it to model class*/
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

    private void initViews() {

        name=findViewById(R.id.nutri_name);
        qualification=findViewById(R.id.nutri_qlf);
        experience=findViewById(R.id.nutri_exp);
        institute=findViewById(R.id.nutri_inst);
        pro_pic=findViewById(R.id.nutri_pic);
        submit=findViewById(R.id.nutri_update);
    }

    private void getImageFromGallery() {
        // open gallery
        // select an image
        // set Image to the image view
        // then get the uri of image and upload the image to firebase storage
        // then pass the url of image from the storage to firestore

        new ImagePicker
                .Builder(NutritionistProfile.this)
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

    private void loadImageIntoimageview(Uri imageUri) {
        if(imageUri != null){
            Glide
                    .with(NutritionistProfile.this)
                    .load(imageUri)
                    .centerCrop()
                    .into(pro_pic);
        }else {
            Glide
                    .with(NutritionistProfile.this)
                    .load(R.drawable.boruto)
                    .centerCrop()
                    .into(pro_pic);
        }
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
                loadImageIntoimageview(uri);
            }
        }

    }

    private void populateFields(Nutritionist_items nutritionist) {
        name.setText(nutritionist.getmItemName());
        qualification.setText(nutritionist.getmQualification());
        experience.setText(nutritionist.getmExperience());
        institute.setText(nutritionist.getmInstitute());

        Uri myImageUri = convertToUri(nutritionist.getmImageUriStrl());

        loadImageIntoimageview(myImageUri);
    }

    private Uri convertToUri(String imageStr) {
        if(imageStr != null){
            return Uri.parse(imageStr);
        }

        return null;
    }

    @Override
    public void onNutritionistDataFetched(Nutritionist_items nutritionist) {
        if (nutritionist != null){
            populateFields(nutritionist);
        }
    }

    @Override
    public void onTrainerDataFetched(Trainer trainer) {

    }

    @Override
    public void onImageUploaded(Uri downloadUri) {
      //This uri contains uri of the image that was uploaded by the user on firestore
        if(downloadUri != null)
            duri = downloadUri;
    }

}
