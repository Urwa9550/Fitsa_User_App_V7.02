package com.wasisoft.fitsa_user_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wasisoft.fitsa_user_app.Helpers.FieldHelper;

import java.util.Objects;

public class NutritionistLoginActivity extends AppCompatActivity {

    private Button login_btn;
    private TextView page_title, signup, nutri_login;
    /*create new account = signup */
    private EditText email, password;

    // Firebase Reference
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private String mCurrentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritionist_login);

        initViews();
        initRef();
        onClick();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mCurrentUser != null) {
            FieldHelper.moveTo(NutritionistLoginActivity.this, NutritionistHomeActivity.class);
            finish();
        }
    }

    private void initRef() {
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        if (mCurrentUser != null)
            mCurrentUserId = mCurrentUser.getUid();
    }

    private void onClick() {

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionistLoginActivity.this, NutritionistRegistrationActivity.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!FieldHelper.fieldempty(email,password)){
                    signInUser();
                }
            }
        });

        nutri_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionistLoginActivity.this, User_login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void signInUser() {

        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        mAuth
                .signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FieldHelper.displayToast(NutritionistLoginActivity.this,"Login Successful!");

                            FieldHelper.moveTo(NutritionistLoginActivity.this, NutritionistHomeActivity.class);
                            finish();
                        }else {
                            FieldHelper.displayToast(NutritionistLoginActivity.this,"Login Failed "+ Objects.requireNonNull(task.getException()).getMessage());
                        }
                    }
                });
    }

    private void initViews() {
        login_btn = findViewById(R.id.login_btn);
        signup = findViewById(R.id.signup_tv);
        email = findViewById(R.id.email);
        password = findViewById(R.id.n_password);
        nutri_login = findViewById(R.id.nutri_gateway);
    }
}
