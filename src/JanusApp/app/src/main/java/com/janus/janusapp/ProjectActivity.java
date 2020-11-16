package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.janus.janusapp.classes.Project;

public class ProjectActivity extends AppCompatActivity {
    Project proyecto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        Gson gson = new Gson();
        proyecto= gson.fromJson(getIntent().getStringExtra("project"),Project.class);
    }
}