package com.janus.janusapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.common.collect.Lists;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.janus.janusapp.classes.*;


import com.google.firebase.database.DatabaseReference;
import com.santalu.maskara.widget.MaskEditText;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class signup extends AppCompatActivity /*Activity*/ {
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //private FirebaseAuth mAuth;

    private DatabaseReference dataBaseRef;
    //public static User MainUser=null;
    private EditText newUserFirstName;
    private EditText newUserLastName;
    private EditText newUserEmail;
    private EditText newUserEmailVerification;
    private EditText newUsername;
    private EditText newUserMobilenumber;
    private EditText newUserPassword;
    private EditText newUserPasswordVerification;
    private MaskEditText newUserBirthDate;
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
        goBackToLoginButton = findViewById(R.id.goBackToLogInButton);
        signUpButton = findViewById(R.id.SignUpButton);
        //goBackToLoginButton = findViewById(R.id.LogInButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            boolean messirve = true;

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                messirve = hagoUnChequeo(newUserFirstName) &
                        hagoUnChequeo(newUserLastName) &
                        tanIguales(newUserEmail, newUserEmailVerification) &
                        hagoUnChequeo(newUsername) &
                        hagoUnChequeo(newUserMobilenumber) &
                        tanIguales(newUserPassword, newUserPasswordVerification) &
                        verifyDates(newUserBirthDate);
                if(messirve){
                    User newUser = new User(newUserFirstName, newUserLastName, newUserEmail, newUsername,
                            newUserMobilenumber, newUserPassword, newUserBirthDate, newUserGender);
                    dataBaseRef.child("Users").child(newUsername.getText().toString()).child("firstName").setValue(newUserFirstName.getText().toString());
                    dataBaseRef.child("Users").child(newUsername.getText().toString()).child("lastName").setValue(newUserLastName.getText().toString());
                    dataBaseRef.child("Users").child(newUsername.getText().toString()).child("email").setValue(newUserEmail.getText().toString());
                    dataBaseRef.child("Users").child(newUsername.getText().toString()).child("username").setValue(newUsername.getText().toString());
                    dataBaseRef.child("Users").child(newUsername.getText().toString()).child("mobileNumber").setValue(newUserMobilenumber.getText().toString());
                    dataBaseRef.child("Users").child(newUsername.getText().toString()).child("password").setValue(newUserPassword.getText().toString());
                    dataBaseRef.child("Users").child(newUsername.getText().toString()).child("birthDate").setValue(newUserBirthDate.getText().toString());
                    dataBaseRef.child("Users").child(newUsername.getText().toString()).child("gender").setValue(newUserGender.getSelectedItem().toString());
                    dataBaseRef.child("Users").child(newUsername.getText().toString()).child("wallet").setValue(0);
                    newUser.followedProjects.append("0");  //Las siguientes 4 líneas son para evitar un error, no borrar Att:Joselo
                    newUser.ownProjectList.append("0");/** Toca cambiar esta chambonada xddd**/;

                     ArrayList<String> subible = Lists.newArrayList("0");;
                     dataBaseRef.child("Users").child(newUsername.getText().toString()).child("ownProjectList").setValue(subible);
                     dataBaseRef.child("Users").child(newUsername.getText().toString()).child("followedProjects").setValue(subible);
                     Intent vamoahome = new Intent(signup.this, Inicio.class);

                     Gson gson = new Gson();
                     String usuario = gson.toJson(newUser);
                     vamoahome.putExtra("usuario", usuario);
                     startActivity(vamoahome);
                     finish();
                }

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

        /**========================== TextView edit listeners ====================================**/

        /**
        newUserLastName;
        newUserEmail;
        newUserEmailVerification;
        newUsername;
        newUserMobilenumber;
        newUserPassword;
        newUserPasswordVerification;
        newUserBirthDate;
        newUserGender;
        goBackToLoginButton;
        signUpButton;**/

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

    private boolean hagoUnChequeo(TextView tv){
        if (tv.getText().toString().trim().equalsIgnoreCase("")){
            tv.setError("This field can not be blank");
            return false;
        }
        return true;
    }

    private boolean tanIguales(TextView a, TextView b){
        if (hagoUnChequeo(a) & hagoUnChequeo(b)){
            if (a.getText().toString().equals(b.getText().toString())) return true;
            b.setError("Verify, the fields are not equal");
        }
        return false;
    }

    public boolean verifyDates (TextView date){
        if (hagoUnChequeo(date)){
            String[] stringDate = date.getText().toString().split("/");
                if (stringDate.length != 3 || stringDate[0].length() != 2 || stringDate[1].length() != 2 || stringDate[2].length() != 4){
                    date.setError("Verify Date");
                    return false;
                }
                else if (Integer.parseInt(stringDate[2]) < 1900){
                    date.setError("Hey pal, you can't be so old");
                    return false;
                }
                else if (Integer.parseInt(stringDate[2]) > Calendar.getInstance().get(Calendar.YEAR) - 13){
                    date.setError("Hey pal, you must be 13 or older");
                    return false;
                }
                return true;
        }
        return false;
    }

}
