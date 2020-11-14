package com.janus.janusapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.janus.janusapp.classes.*;


import com.google.firebase.database.DatabaseReference;

import java.time.LocalDate;
import java.util.Calendar;

public class signup extends AppCompatActivity /*Activity*/ {
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //private FirebaseAuth mAuth;

    private DatabaseReference dataBaseRef;
    public static User MainUser=null;
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


    public class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet (DatePicker view, int year, int month, int dayOfMonth){
            //return year + "/" + month + "/" + dayOfMonth;

        }

    }


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
        goBackToLoginButton = findViewById(R.id.goBackToLogInButton);
        signUpButton = findViewById(R.id.SignUpButton);
        //goBackToLoginButton = findViewById(R.id.LogInButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                User newUser = new User(newUserFirstName,  newUserLastName,newUserEmail, newUsername,
                        newUserMobilenumber, newUserPassword, newUserBirthDate, newUserGender);
                dataBaseRef.child("Users").child(newUsername.getText().toString()).setValue(newUser);

                Intent vamoahome = new Intent(signup.this, Inicio.class);

                Gson gson = new Gson();
                String usuario = gson.toJson(newUser);
                vamoahome.putExtra("usuario",usuario);
                startActivity(vamoahome);
                finish();
            }

        });
        goBackToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signup.this, Login.class);
                startActivity(i);
            }
        });
        newUserBirthDate.setOnClickListener(new View.OnClickListener() {// AQUI LAS FECHAS MALDITAS FECHAS QUE NO PERMITWEN TRABAJARRRR
            @Override
            public void onClick(View v) {
                Intent VayaseALogin = new Intent(signup.this, Login.class);
                startActivity(VayaseALogin);

            }
        });
    }


    /**private void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }*/


    /*
    protected void writeNewUser(String Username, String password, LocalDate birthdate, String email){
        User newUser = new User(Username,password,birthdate,email);
        dataBaseRef.child("Users").child(Username).setValue(newUser);
        //return newUser;
    }*/

}
