package com.wasisoft.fitsa_user_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wasisoft.fitsa_user_app.Helpers.FieldHelper;

public class UserTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        Button mUserButton = findViewById(R.id.user);
        Button mNutritionistButton = findViewById(R.id.nutritionist);

        mUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FieldHelper.moveTo(UserTypeActivity.this, User_login.class);
                finish();
            }
        });

        mNutritionistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FieldHelper.moveTo(UserTypeActivity.this, NutritionistLoginActivity.class);
                finish();
            }
        });

    }
}
