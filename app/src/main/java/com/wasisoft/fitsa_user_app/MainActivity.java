package com.wasisoft.fitsa_user_app;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.wasisoft.fitsa_user_app.Fragments.Exercise_fragment;
import com.wasisoft.fitsa_user_app.Fragments.Nutritionist_Fragment;
import com.wasisoft.fitsa_user_app.Fragments.Trainer_Fragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bnv;
    // public LinearLayout linl;

    private Exercise_fragment exerciseFragment;
    private Nutritionist_Fragment nutritionistFragment;
    private Trainer_Fragment trainerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//instancing fragments

        exerciseFragment= new Exercise_fragment();
        nutritionistFragment = new Nutritionist_Fragment();
        trainerFragment = new Trainer_Fragment();

        addFragment(exerciseFragment);

        bnv = findViewById(R.id.bnv);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.exercise_mi){
                    addFragment(exerciseFragment);
                    return  true;
                }

                if(id == R.id.nutritionist_mi){
                    addFragment(nutritionistFragment);
                    return true;
                }

                if(id == R.id.trainer_mi){
                    addFragment(trainerFragment);
                    return true;
                }

                return false;
            }
        });

    }

    private void addFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.fragment_container,fragment);
        ft.replace(R.id.fragment_container,fragment);
        ft.commit();
    }


}
