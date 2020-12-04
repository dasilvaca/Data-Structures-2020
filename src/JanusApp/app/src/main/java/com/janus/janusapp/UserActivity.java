package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.janus.janusapp.classes.Project;
import com.janus.janusapp.classes.User;

public class UserActivity extends AppCompatActivity {

    private TextView currentUsername;
    private TextView currentUserFullName;
    private TextView currentUserBirthDate;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        currentUsername=findViewById(R.id.username);
        currentUserFullName=findViewById(R.id.fullname);
        currentUserBirthDate=findViewById(R.id.birthdate);

        Gson gson = new Gson();
        String gsonUser=getIntent().getStringExtra("user");

        user= gson.fromJson(gsonUser, User.class);

        currentUsername.setText(user.username);
        currentUserFullName.setText(user.firstName+""+user.lastName);
        currentUserBirthDate.setText(user.birthDate);

    }
}