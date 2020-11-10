package com.janus.janusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.janus.janusapp.classes.*;


import com.google.firebase.database.DatabaseReference;

import java.time.LocalDate;

public class Signup extends Activity {

    private DatabaseReference dataBaseRef;
    private EditText newUserName;
    private EditText newUserPassword;
    private EditText newUserEmail;
    private EditText newUserBirthDate;
    private Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseReference mRootReference;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        dataBaseRef = FirebaseDatabase.getInstance().getReference();
        newUserName = (EditText)findViewById(R.id.newUserUsername);
        newUserPassword = (EditText)findViewById(R.id.newUserPassword);
        newUserEmail = (EditText)findViewById(R.id.newUserEmail);
        newUserBirthDate = (EditText)findViewById(R.id.newUserBirthdate);
        signUpButton = (Button)findViewById(R.id.SignUpButton);
    }

    protected void writeNewUser(String Username, String password, LocalDate birthdate, String email){
        User newUser = new User(Username,password,birthdate,email);
        dataBaseRef.child("Users").child(Username).setValue(newUser);
        //return newUser;
    }

    protected void signUp(View view){
        String newUrNm = newUserName.getText().toString();
        String newUrPassword = newUserPassword.getText().toString();
        String newUrEmail = newUserEmail.getText().toString();
        LocalDate newUrBirthD = LocalDate.parse(newUserBirthDate.getText());//Sigan en esatudios a mimir
        // User currentUser = writeNewUser(newUrNm, newUrPassword, newUrBirthD, newUrEmail);
        writeNewUser(newUrNm, newUrPassword, newUrBirthD, newUrEmail);
        Intent goToHome = new Intent(this, Inicio.class);
        //goToHome.putExtra("currentUser", currentUser);
        startActivity(goToHome);
    }
}