package com.janus.janusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.janus.janusapp.structs.AVLTree;


//public static boolean opened = false;
public class MainActivity extends AppCompatActivity {
    TextView prueba;
    public static AVLTree<String> dadyTree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        BottomNavigationView bottomnav = findViewById(R.id.bottomNavigationView2);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
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
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment,selectedFragment).commit();
                    return true;
                }
            };

    public static long log2(long N){

        long result = (long)(Math.log(N) / Math.log(2));
        return result;
    }
}
