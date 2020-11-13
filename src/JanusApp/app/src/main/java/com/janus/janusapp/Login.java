package com.janus.janusapp;

import android.app.Activity;
import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;


public class Login extends Activity {
    public EditText username;
    private EditText password;
    public String usernameInDB;
    public  String passwordInDB;
    public Button loginNow;
    public Button goToSignUp;
    private CheckBox showPassword;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference firebaseReference = FirebaseDatabase.getInstance().getReference();
    private Button LogInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        showPassword = (CheckBox)findViewById(R.id.ShowPassword);
        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else{
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
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
        LogInButton = findViewById(R.id.LogInButton);
/*
        DatabaseReference userNameReference = database.getReference("Users");
        //if (userNameReference.child(username.toString()).getValue())

        }
*/
        //DatabaseReference userNameReference = database.getReference("Users");
        String LogInUsername = username.getText().toString();
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseReference.child("Users").child(LogInUsername).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            Toast.makeText(Login.this,"Funciona", Toast.LENGTH_LONG);
                            //String LogInUsername = usernameEditText.getText().toString();
                            String LogInPassword = password.getText().toString();
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
        });
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
}
