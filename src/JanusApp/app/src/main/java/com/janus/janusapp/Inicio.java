package com.janus.janusapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.janus.janusapp.classes.User;

public class Inicio extends AppCompatActivity {

    public static User MainUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //DatabaseReference mRootReference;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        String jeje = getIntent().getStringExtra("JEJE");
        //MainUser=(User)getIntent().getParcelableExtra("usuario");
        BottomNavigationView bottomnav = findViewById(R.id.bottomNavigationView2);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        if(Login.MainUser==null){
            MainUser=signup.MainUser;
        }else{
            MainUser=Login.MainUser;
        }
        bottomnav.setSelectedItemId(R.id.homeFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch(item.getItemId()){
                        case R.id.configFragment:
                            selectedFragment = new configFragment();
                            break;
                        case R.id.homeFragment:
                            selectedFragment = new homeFragment();
                            break;
                        case R.id.newProjectFragment:
                            selectedFragment = new newProjectFragment();
                            break;
                        case R.id.profileFragment:
                            selectedFragment = new profileFragment();
                            break;
                        case R.id.searchFragment:
                            selectedFragment = new searchFragment();
                            break;
                        default: selectedFragment = new newProjectFragment();
                        break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment,selectedFragment).commit();
                    return true;
                }
            };
}