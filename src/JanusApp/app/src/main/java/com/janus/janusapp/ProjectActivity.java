package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.janus.janusapp.classes.Project;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.janus.janusapp.classes.Project;
import com.janus.janusapp.classes.User;

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
    private ToggleButton followButton;
    private User currentUser;
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
        followButton = findViewById(R.id.FollowButton);
        Gson gson;
        gson = new Gson();
        //currentUser = gson.fromJson(MainUserStr, User.class);


        //Sección Gson
        /**============TODO: En caso de que ocurra un error al pasar el usuario actual de Inicio a otra activity, usar las líneas 62 y 64 :v =============*/
        String gsonProject=getIntent().getStringExtra("project");
        String gsonUser=getIntent().getStringExtra("user");
        proyecto= gson.fromJson(gsonProject,Project.class);
        currentUser = gson.fromJson(gsonUser,User.class);

        projectName.setText(proyecto.name);
        projectDescription.setText(proyecto.description);
        followersNumber.setText(String.valueOf(proyecto.followers.size));
        ownersList.setText(proyecto.owners.toString());
        foundProgress.setText(String.valueOf(proyecto.budget));
        progressBar.setProgress(proyecto.progress/proyecto.budget);
        if(proyecto.picture!=null) {
            pic = Uri.parse(proyecto.picture);
            Glide.with(ProjectActivity.this).load(pic).fitCenter().centerCrop().into(projectImage);
        } else{
            Glide.with(ProjectActivity.this).load(R.drawable.default_project_picture).fitCenter().centerCrop().into(projectImage);
        }
        if(currentUser.followedProjects.find(proyecto.name)){
            followButton.setBackgroundResource(R.drawable.round_borders_false);
        }

        followButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!followButton.isChecked()){
                    currentUser.followedProjects.remove(proyecto.name);
                    followButton.setBackgroundResource(R.drawable.round_borders);
                    Toast.makeText(ProjectActivity.this, "You have stopped following this project", Toast.LENGTH_SHORT).show();
                } else{
                    currentUser.followProject(proyecto);
                    Toast.makeText(ProjectActivity.this, "Now you follow this project", Toast.LENGTH_SHORT).show();
                    followButton.setBackgroundResource(R.drawable.round_borders_false);
                }
            }
        });
    }
}