package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.janus.janusapp.structs.*;

//public static boolean opened = false;
public class MainActivity extends AppCompatActivity {
    TextView prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

    }

    public void login(View v){
        Intent i = new Intent(this,Main.class);
        startActivity(i);
    }
}