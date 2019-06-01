package com.wasisoft.fitsa_user_app;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

public class NutritionistHomeActivity extends AppCompatActivity {

    private ImageView nutHeaderImageview;
    private TextView nutHeaderName;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    Nutritionist_items model;
    String imagePath;
    Uri uri,duri;
    // Firebase References
    FirestoreHelper mFirestoreHelper;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private String mCurrentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritionist_home);

        // Remove it whenever you wish
       /*Button gotoNutProfile = findViewById(R.id.goto_nut_profile);

        gotoNutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FieldHelper.moveTo(NutritionistHomeActivity.this,NutritionistProfile.class);
            }
        });*/

        initViews();
        setUpToolbar();
        initRef();
        navItemClick();
/*--navbar drawer*/
        final ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu_navbtn);
    }

    private void navItemClick() {

       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

               switch(menuItem.getItemId())
               {
                   case R.id.action_home:
                       menuItem.setChecked(true);
                       FieldHelper.displayToast(NutritionistHomeActivity.this,"Home");
                       mDrawerLayout.closeDrawers();
                       return true;

                   case R.id.action_profile:
                       menuItem.setChecked(true);
                       FieldHelper.displayToast(NutritionistHomeActivity.this,"Profile");
                       openProfile();
                       mDrawerLayout.closeDrawers();
                       return true;

                   case R.id.action_logout:
                       menuItem.setChecked(true);
                       FieldHelper.displayToast(NutritionistHomeActivity.this,"Logout");
                       mDrawerLayout.closeDrawers();
                       return true;
               }
               return false;
           }
       });
    }

    private void openProfile() {

        FieldHelper.moveTo(NutritionistHomeActivity.this,NutritionistProfile.class);

    }

    private void initViews() {
        nutHeaderName=findViewById(R.id.nut_header_textview);
        nutHeaderImageview= findViewById(R.id.nut_header_imageview);
        mDrawerLayout = findViewById(R.id.nut_drawerlayout);
        mToolbar = findViewById(R.id.mytoolbar);
        navigationView = findViewById(R.id.naview);
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setElevation(4.0f);
            getSupportActionBar().setHomeButtonEnabled(true);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initRef() {
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open_drawer,R.string.close_drawer);

//
//            mFirestoreHelper = FirestoreHelper.getInstance();
//
//            mFirestoreHelper.setFetchUserDataListener(this);
//
//            mAuth = FirebaseAuth.getInstance();
//            mCurrentUser = mAuth.getCurrentUser();
//
//            if(mCurrentUser != null)
//                mCurrentUserId = mCurrentUser.getUid();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkDrawer();
//        if(mCurrentUser != null){
//            retrieveNutritionistData();

//        }
    }

//    private void retrieveNutritionistData() {
//        mFirestoreHelper.retrieveFirestoreNutritionistData(Keys.NUTRITIONIST_COLLECTION, mCurrentUserId);
//        mFirestoreHelper.retrieveFirestoreNutritionistNameImage(Keys.NUTRITIONIST_COLLECTION, mCurrentUserId);
//
//    }

    private void checkDrawer() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        checkDrawer();
    }
/*-----------------------------------nav drawer end ---------------------------------------------------------*/



//    @Override
//    public void onTrainerDataFetched(Trainer trainer) {
//
//    }

//    @Override
//    public void onImageUploaded(Uri downloadUri) {
//        //This uri contains uri of the image that was uploaded by the user on firestore
//
//        if(downloadUri != null)
//            duri = downloadUri;
//    }

//
//    @Override
//    public void onNutritionistDataFetched(Nutritionist_items nutritionist) {
//        if (nutritionist != null){
//          //  populateFields(nutritionist);
//        }
//    }
//    private void populateFields(Nutritionist_items nutritionist) {
//        nutHeaderName.setText(nutritionist.getmItemName());
//
//
//        Uri myImageUri = convertToUri(nutritionist.getmImageUriStrl());
//
//        loadImageIntoimageview(myImageUri);
//    }
//
//    private void loadImageIntoimageview(Uri imageUri) {
//        if(imageUri != null){
//            Glide
//                    .with(NutritionistHomeActivity.this)
//                    .load(imageUri)
//                    .centerCrop()
//                    .into(nutHeaderImageview);
//        }else {
//            Glide
//                    .with(NutritionistHomeActivity.this)
//                    .load(R.drawable.boruto)
//                    .centerCrop()
//                    .into(nutHeaderImageview);
//        }
//    }
//
//    private Uri convertToUri(String imageStr) {
//        if(imageStr != null){
//            return Uri.parse(imageStr);
//        }
//
//        return null;
//    }

}
