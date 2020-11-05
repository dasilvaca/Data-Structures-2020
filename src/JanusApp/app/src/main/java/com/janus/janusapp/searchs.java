package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.janus.janusapp.classes.User;

public class searchs extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchs);
        User tipo= (User)getIntent().getParcelableExtra("Tipo");
        tv=(TextView)findViewById(R.id.textView);
       tv.setText(tipo.userName);
    }
}