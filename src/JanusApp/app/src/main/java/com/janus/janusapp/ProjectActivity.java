package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.janus.janusapp.classes.Project;
import android.widget.Toast;

import com.google.gson.Gson;
import com.janus.janusapp.classes.Project;

public class ProjectActivity extends AppCompatActivity {

    private ImageView projectImage;
    private TextView projectName;
    private TextView foundProgress;
    private ProgressBar progressBar;
    private TextView followersTextView;
    private TextView followersNumber;
    private TextView ownersTextView;
    private TextView ownersList;
    private TextView descriptionTextView;
    private TextView projectDescription;
    private Uri pic;
    Project onProject;
    /**=============TODO: Ponerle un atributo Progress a los proyectos para usar la ProgressBar ==================**/
    Project proyecto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        projectImage = findViewById(R.id.ProjectImage);
        projectName = findViewById(R.id.ProjectName);
        foundProgress = findViewById(R.id.FoundProgress);
        progressBar = findViewById(R.id.progressBar);
        followersTextView = findViewById(R.id.Followers);
        followersNumber = findViewById(R.id.FollowersNumber);
        ownersTextView = findViewById(R.id.Owners);
        ownersList = findViewById(R.id.OwnersList);
        descriptionTextView = findViewById(R.id.ProjectDescription);
        projectDescription = findViewById(R.id.Description);


        //Sección Gson
        Gson gson = new Gson();
        proyecto= gson.fromJson(getIntent().getStringExtra("project"),Project.class);

        projectName.setText(proyecto.name);
        projectDescription.setText(proyecto.description);
        followersNumber.setText(String.valueOf(proyecto.followers.size));
        ownersList.setText(proyecto.owners.toString());
        foundProgress.setText(String.valueOf(proyecto.budget));
        if(proyecto.picture!=null) {
            pic = Uri.parse(proyecto.picture);
            Glide.with(ProjectActivity.this).load(pic).fitCenter().centerCrop().into(projectImage);
        }
    }
}