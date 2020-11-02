package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

//public static boolean opened = false;
public class MainActivity extends AppCompatActivity {
    TextView prueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prueba=(TextView)findViewById(R.id.prueba);
        setContentView(R.layout.login_screen);
    }
}