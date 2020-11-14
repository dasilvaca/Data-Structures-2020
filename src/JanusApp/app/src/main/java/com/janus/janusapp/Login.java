package com.janus.janusapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.janus.janusapp.classes.User;
import com.janus.janusapp.structs.DynamicArray;
import com.janus.janusapp.structs.DynamicArrayS;
import com.janus.janusapp.structs.LinkedL;

import java.io.Serializable;

import static android.content.ContentValues.TAG;


public class Login extends Activity {
    public static User MainUser=null;
    public EditText username;
    private EditText password;
    public String usernameInDB;
    public  String passwordInDB;
    public Button loginNow;
    public Button goToSignUp;
    public CheckBox stayLogged;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference firebaseReference = FirebaseDatabase.getInstance().getReference();
    //private Button LogInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);


        goToSignUp = (Button) findViewById(R.id.ButtonSignUp);
        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vayaseARegistrar = new Intent(Login.this, signup.class);
                startActivity(vayaseARegistrar);
            }
        });
        username = findViewById(R.id.newUserUsername);
        password = findViewById(R.id.newUserPassword);
        //loginNow;
        //LogInButton = findViewById(R.id.LogInButton);
        stayLogged=(CheckBox)findViewById(R.id.StayLogged);
/*
        DatabaseReference userNameReference = database.getReference("Users");
        //if (userNameReference.child(username.toString()).getValue())

        }
*/
        //DatabaseReference userNameReference = database.getReference("Users");


        /*LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseReference.child("Users").child(LogInUsername).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()){
                            Toast.makeText(Login.this,"Funciona", Toast.LENGTH_LONG);
                            //String LogInUsername = usernameEditText.getText().toString();
                            String LogInPassword = password.getText().toString();
                            username.setText(LogInPassword);
                            //if (!LogInUsername.isEmpty() && !LogInPassword.isEmpty()){
                                //if (firebaseReference.child("Users").child(LogInUsername).)
                            }
                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Login.this,"No Funciona", Toast.LENGTH_LONG);

                    }
                });
                Toast.makeText(Login.this,"bfdbdf", Toast.LENGTH_LONG); }
        });*/
    }

    // Read from the database
/*8
DBusername.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            String value = dataSnapshot.getValue(String.class);
            Log.d(TAG, "Value is: " + value);
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    });*/
    public void leer(View v){
        String LogInUsername = username.getText().toString();

        firebaseReference.child("Users").child(LogInUsername).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    Toast.makeText(Login.this,"Funciona", Toast.LENGTH_LONG);
                    //String LogInUsername = usernameEditText.getText().toString();
                    String LogInPassword = password.getText().toString();
                    if(LogInPassword.equals(snapshot.child("password").getValue().toString())){
                        SharedPreferences p = getSharedPreferences("Check", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = p.edit();
                        if(stayLogged.isChecked()){

                           ed.putString("check","si");

                        }
                        Intent vamoahome = new Intent(Login.this, Inicio.class);
                        User newUser = (User)snapshot.getValue(User.class);
                        newUser.ownProjectList=new DynamicArrayS(); //Esto lo hago para evitar un error, Att: Joselo
                        newUser.followedProjects=new DynamicArrayS();
                        firebaseReference.child("Users").child(LogInUsername).child("followedProjects").child("Projects").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    int cont=0;
                                    for(DataSnapshot ds : snapshot.getChildren()){
                                        String project = ds.child("P"+cont).getValue().toString();
                                        newUser.followedProjects.append(project);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        firebaseReference.child("Users").child(LogInUsername).child("ownProjectList").child("Projects").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    int cont=0;
                                    for(DataSnapshot ds : snapshot.getChildren()){
                                        String project = ds.child("P+cont").getValue().toString();
                                        newUser.ownProjectList.append((project));
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        Gson gson = new Gson();
                        String usuario = gson.toJson(newUser);
                        vamoahome.putExtra("usuario",usuario);
                        startActivity(vamoahome);
                        finish();

                    }else{
                        Toast.makeText(Login.this,"Contraseña incorrecta",Toast.LENGTH_LONG).show();
                    }

                    //if (!LogInUsername.isEmpty() && !LogInPassword.isEmpty()){
                    //if (firebaseReference.child("Users").child(LogInUsername).)
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this,"No Funciona", Toast.LENGTH_LONG);

            }
        });
    }

}
