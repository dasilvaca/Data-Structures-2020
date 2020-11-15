package com.janus.janusapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.janus.janusapp.classes.User;
import com.janus.janusapp.structs.DynamicArrayS;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;


public class Login extends AppCompatActivity {
    public EditText username;
    private EditText password;
    public Button goToSignUp;
    public CheckBox stayLogged;
    private CheckBox showPassword;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference firebaseReference = FirebaseDatabase.getInstance().getReference();

    /**
     * En la siguiente sección aparece el chechbox para mostrar o no la contraseña
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        showPassword = (CheckBox) findViewById(R.id.ShowPassword);
        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        /**==============================================================================*/


        /**
         * En esta sección se instancia y se usa el boton para cambiar de login a signup
         */
        goToSignUp = (Button) findViewById(R.id.ButtonSignUp);
        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vayaseARegistrar = new Intent(Login.this, signup.class);
                startActivity(vayaseARegistrar);
            }
        });

        /**===============================================================================*/

        /**
         * se instancian los edit Text de username
         */
        username = findViewById(R.id.newUserUsername);
        password = findViewById(R.id.newUserPassword);
        stayLogged = (CheckBox) findViewById(R.id.StayLogged);

    }

    /**
     * Aquí se hacen las verificaciones de si las entradas son o no vacías
     * y se procede a ingresar según el caso, es decir, se hace lectura con la base de datos
     * Conectandolo directo desde el XML
     */

    public void leer(View v) {
        String LogInUsername = username.getText().toString();

        /**
         * Se verifica si el campo Usuario está vacío
         */

        if (!LogInUsername.equals("")) {

            /**
             * El siguiente método hace la lectura de la base de datos, teniendo como llava el nombre de usuario ingresado
             */
            firebaseReference.child("Users").child(LogInUsername).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    /**
                     * Si el usuario ingresado existe, toma la cadena del campo Password
                     */
                    if (snapshot.exists()) {
                        String LogInPassword = password.getText().toString();
                        /**
                         * Se hace la verificación con la contrasseña del usuario que se encontró con la ingresada por el usuario
                         */
                        if (LogInPassword.equals(snapshot.child("password").getValue().toString())) {
                            SharedPreferences p = getSharedPreferences("Check", Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed = p.edit();
                            if (stayLogged.isChecked()) ed.putString("check", "si");
                            String firstName= snapshot.child("firstName").getValue().toString();
                            String lastName=snapshot.child("lastName").getValue().toString();
                            String email=snapshot.child("email").getValue().toString();
                            String username=snapshot.child("username").getValue().toString();
                            String mobileNumber=snapshot.child("mobileNumber").getValue().toString();
                            String password=snapshot.child("password").getValue().toString();
                            int wallet=Integer.parseInt(snapshot.child("wallet").getValue().toString());
                            String birthDate=snapshot.child("birthDate").getValue().toString();
                            String gender=snapshot.child("gender").getValue().toString();
                            ArrayList<String> ownProjectArrayList = (ArrayList<String>) snapshot.child("ownProjectList").getValue();
                            ArrayList<String> followedProjectsArrayList= (ArrayList<String>) snapshot.child("followedProjects").getValue();

                            DynamicArrayS ownProjectList = new DynamicArrayS((String[]) ownProjectArrayList.toArray(new String[0]));
                            DynamicArrayS followedProjects = new DynamicArrayS((String[]) followedProjectsArrayList.toArray(new String[0]));
                            User newUser = new User(firstName,lastName,email,username,mobileNumber,password,birthDate,gender);
                            newUser.wallet=wallet;
                            newUser.ownProjectList=ownProjectList;
                            newUser.followedProjects=followedProjects;
                            if(snapshot.child("picture").exists()) {
                                newUser.picture=snapshot.child("picture").getValue().toString();
                            }
                            Intent vamoahome = new Intent(Login.this, Inicio.class);

                            newUser.ownProjectList = new DynamicArrayS(); //Esto lo hago para evitar un error, Att: Joselo
                            newUser.followedProjects = new DynamicArrayS();

                            Gson gson = new Gson();
                            String usuario = gson.toJson(newUser);
                            vamoahome.putExtra("usuario", usuario);
                            startActivity(vamoahome);
                            finish();

                        } else {
                            Toast.makeText(Login.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }

                    } else
                        Toast.makeText(Login.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Login.this, "No Funciona", Toast.LENGTH_LONG);

                }
            });
        } else
            Toast.makeText(Login.this, "El campo de Usuario está vacío", Toast.LENGTH_SHORT).show();
    }
/**=================Hasta aquí va el método de lectura e inicio de sesión en la DB================*/
}