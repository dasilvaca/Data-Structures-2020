package com.janus.janusapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.janus.janusapp.classes.*;


import com.google.firebase.database.DatabaseReference;

import java.time.LocalDate;
import java.util.Calendar;

public class signup extends AppCompatActivity /*Activity*/ {
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //private FirebaseAuth mAuth;
    private DatabaseReference dataBaseRef;
    private EditText newUserFirstName;
    private EditText newUserLastName;
    private EditText newUserEmail;
    private EditText newUserEmailVerification;
    private EditText newUsername;
    private EditText newUserMobilenumber;
    private EditText newUserPassword;
    private EditText newUserPasswordVerification;
    private EditText newUserBirthDate;
    private Spinner newUserGender;
    private Button goBackToLoginButton;
    private Button signUpButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //DatabaseReference mRootReference;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dataBaseRef = FirebaseDatabase.getInstance().getReference();
        newUserFirstName = findViewById(R.id.newUserFirstName);
        newUserLastName = findViewById(R.id.newUserLastName);
        newUserEmail = findViewById(R.id.newUserEmail);
        newUserEmailVerification = findViewById(R.id.newUserEmailVerification);
        newUsername = findViewById(R.id.newUserUsername);
        newUserMobilenumber = findViewById(R.id.newUserMobilenumber);
        newUserPassword = findViewById(R.id.newUserPassword);
        newUserPasswordVerification = findViewById(R.id.newUserPasswordVerification);
        newUserBirthDate = findViewById(R.id.newUserBirthdate);
        newUserGender = findViewById(R.id.newUserGender); // Pregunta a José Luis pls
        goBackToLoginButton = findViewById(R.id.goBackToLoginButton);
        signUpButton = findViewById(R.id.SignUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                User newUser = new User(newUserFirstName,  newUserLastName,newUserEmail, newUsername,
                        newUserMobilenumber, newUserPassword, newUserBirthDate, newUserGender);
                dataBaseRef.child("Users").child(newUsername.getText().toString()).setValue(newUser);
                Intent VamoAHomeHomies = new Intent(signup.this,MainActivity.class);
                startActivity(VamoAHomeHomies);
                finish();
            }

        });
        /*newUserBirthDate.setOnClickListener(new View.OnClickListener() {// AQUI LAS FECHAS MALDITAS FECHAS QUE NO PERMITWEN TRABAJARRRR
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.newUserBirthdate:
                        showDatePickerDialog();
                        break;
                }
            }
        });*/
    }

    /*private void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    @Override
    public String onDateSet (DatePicker view, int year, int month, int dayOfMonth){
        return year + "/" + month + "/" + dayOfMonth;
    }*/
    /*
    protected void writeNewUser(String Username, String password, LocalDate birthdate, String email){
        User newUser = new User(Username,password,birthdate,email);
        dataBaseRef.child("Users").child(Username).setValue(newUser);
        //return newUser;
    }*/

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void signUp(View view){
        String newUrNm = newUsername.getText().toString();
        String newUrPassword = newUserPassword.getText().toString();
        String newUrEmail = newUserEmail.getText().toString();
        LocalDate newUrBirthD = LocalDate.parse(newUserBirthDate.getText());//Sigan en esatudios a mimir
        // User currentUser = writeNewUser(newUrNm, newUrPassword, newUrBirthD, newUrEmail);
        //writeNewUser(newUrNm, newUrPassword, newUrBirthD, newUrEmail); //xddddddddddd
        Intent goToHome = new Intent(this, Inicio.class);
        //goToHome.putExtra("currentUser", currentUser);
        startActivity(goToHome);
    }
}