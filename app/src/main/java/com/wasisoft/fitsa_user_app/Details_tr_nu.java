package com.wasisoft.fitsa_user_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class Details_tr_nu extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView name, qualification, institute, experience;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_tr_nu);

        name=findViewById(R.id.f_name_tr_nu);
        qualification=findViewById(R.id.study);
        experience=findViewById(R.id.study5);
        institute=findViewById(R.id.study3);
        imageView=findViewById(R.id.circularImageView);


//    toolbar = findViewById(R.id.toolbar_tr_nu);
//    setSupportActionBar(toolbar);
//   // toolbar.setTitle("Profile");
////        toolbar.setLogo(R.drawable.pikachu);
//       // toolbar.setLogoDescription(getResources().getString(R.string.logo_desc));
    }
}
