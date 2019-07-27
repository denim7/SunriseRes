package com.example.sunriseres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.sunriseres.Fragments.homefragment;
import com.example.sunriseres.Fragments.menufragment;
import com.example.sunriseres.Fragments.overviewfragment;
import com.example.sunriseres.Fragments.reviewfragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {
    private FrameLayout host;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        host = findViewById(R.id.fragmenthost);
        bottomNavigationView = findViewById(R.id.bottommenu);

        changeFrag(new homefragment(),"Home");

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.overview_menuitem:
                        changeFrag(new overviewfragment(),"Overview");
                        break;
                    case R.id.menu_menuitem:
                        changeFrag(new menufragment(),"Menu");
                    break;
                    case R.id.review_menuitem:
                        changeFrag(new reviewfragment(),"Review");
                        break;
                        default:
                            changeFrag(new homefragment(),"Home");
                }
                return true;
            }
        });

    }

    public void changeFrag(Fragment fragment, String tag){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragmenthost,fragment)
                .addToBackStack(tag)
                .commit();
    }
}









