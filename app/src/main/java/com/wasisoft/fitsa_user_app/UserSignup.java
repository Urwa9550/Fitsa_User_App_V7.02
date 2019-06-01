package com.wasisoft.fitsa_user_app;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wasisoft.fitsa_user_app.Helpers.FieldHelper;

import java.util.Objects;

public class UserSignup extends AppCompatActivity {

    private Button submit;
    private EditText n_email, n_password, n_confirm_pwd;

    // Firebase Reference
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private String mCurrentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();
        initRef();
        onClick();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mCurrentUser != null){
            FieldHelper.moveTo(UserSignup.this,MainActivity.class);
            finish();
        }

    }

    private void initRef() {
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        if(mCurrentUser != null)
            mCurrentUserId = mCurrentUser.getUid();
    }

    private void onClick() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!FieldHelper.fieldempty(n_email,n_password,n_confirm_pwd)){
                    signUpUser();
                }
            }
        });
    }

    private void signUpUser() {

        String email = n_email.getText().toString().trim();
        String password = n_password.getText().toString().trim();
        String confirmPassword = n_confirm_pwd.getText().toString().trim();

        if(password.equals(confirmPassword)){
            mAuth
                    .createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FieldHelper.displayToast(UserSignup.this,"Registration Successful!");

                                FieldHelper.moveTo(UserSignup.this, MainActivity.class);
                                finish();
                            }else {
                                FieldHelper.displayToast(UserSignup.this,"Registration Failed "+ Objects.requireNonNull(task.getException()).getMessage());
                            }
                        }
                    });
        }
    }

    //REGEX regular (expression) for email checks(constraintes) etc
    private void initViews() {
        submit = findViewById(R.id.submit);
        n_email = findViewById(R.id.n_email);
        n_password = findViewById(R.id.n_password);
        n_confirm_pwd = findViewById(R.id.n_confirm_pwd);

    }

}
