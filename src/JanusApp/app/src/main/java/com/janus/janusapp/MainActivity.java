package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.janus.janusapp.structs.AVLTree;

//public static boolean opened = false;
public class MainActivity extends AppCompatActivity {
    TextView prueba;
    public static AVLTree<String> dadyTree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent next = new Intent(MainActivity.this, Signup_.class);
        startActivity(next);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //prueba=(TextView)findViewById(R.id.prueba);
        //setContentView(R.layout.login_layout);

        setContentView(R.layout.signup_layout_);

    }
};
