package com.janus.janusapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.janus.janusapp.classes.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link modifyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class modifyProfileFragment extends Activity {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public User mainUser;
    private ImageView currentProfileImage;
    private EditText modifyUsername;
    private EditText modifyPassword;
    private EditText modifyEmail;
    private EditText modifyTelNumber;
    private DatabaseReference firebaseRef;
    private Button deleteUserButton;

    private Button saveChangesButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public modifyProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment modifyProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static modifyProfileFragment newInstance(String param1, String param2) {
        modifyProfileFragment fragment = new modifyProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_modify_profile);
        mainUser = Inicio.MainUser;

        //if (getArguments() != null) {
        //  mParam1 = getArguments().getString(ARG_PARAM1);
        //mParam2 = getArguments().getString(ARG_PARAM2);
        //}
        firebaseRef = FirebaseDatabase.getInstance().getReference();
        //View view = inflater.inflate(R.layout.fragment_modify_profile, container, false);
        deleteUserButton = findViewById(R.id.DeleteUserButton);
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseRef.child("Users").child(mainUser.username).removeValue();
                Intent salgaseDeAhi = new Intent(modifyProfileFragment.this, Login.class);
                startActivity(salgaseDeAhi);
                finish();
            }
        });
        modifyUsername = findViewById(R.id.ModifyUsername);
        modifyPassword = findViewById(R.id.ModifyPassword);
        modifyEmail = findViewById(R.id.ModifyEmail);
        modifyTelNumber = findViewById(R.id.ModifyTelNumber);
        currentProfileImage = findViewById(R.id.currentProfileImage);
        DatabaseReference d = FirebaseDatabase.getInstance().getReference();
        d.child("Users").child(mainUser.username).child("picture").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String ubi = snapshot.getValue().toString();
                    Uri ubiUrl = Uri.parse(ubi);
                    Glide.with(modifyProfileFragment.this).load(ubi).fitCenter().centerCrop().into(currentProfileImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        modifyUsername.setText(mainUser.username);
        modifyPassword.setText(mainUser.password);
        modifyEmail.setText(mainUser.email);
        modifyTelNumber.setText(mainUser.mobileNumber);

        saveChangesButton = findViewById(R.id.saveChangesButton);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser = Inicio.MainUser;
                newUser.EditSomeUserInfo(modifyUsername, modifyPassword, modifyEmail, modifyTelNumber);
                firebaseRef.child("Users").child(newUser.username).child("firstName").setValue(mainUser.firstName);
                firebaseRef.child("Users").child(newUser.username).child("lastName").setValue(mainUser.lastName);
                firebaseRef.child("Users").child(newUser.username).child("email").setValue(newUser.email);
                firebaseRef.child("Users").child(newUser.username).child("username").setValue(newUser.username);
                firebaseRef.child("Users").child(newUser.username).child("mobileNumber").setValue(newUser.mobileNumber);
                firebaseRef.child("Users").child(newUser.username).child("password").setValue(newUser.password);
                firebaseRef.child("Users").child(newUser.username).child("birthDate").setValue(mainUser.birthDate);
                firebaseRef.child("Users").child(newUser.username).child("gender").setValue(mainUser.gender);
                firebaseRef.child("Users").child(newUser.username).child("wallet").setValue(mainUser.wallet);
                ArrayList<String> ownProjectArrayList = new ArrayList<>(Arrays.asList(Inicio.MainUser.ownProjectList.array));
                ArrayList<String> followedProjectsArrayList = new ArrayList<>(Arrays.asList(Inicio.MainUser.followedProjects.array));
                firebaseRef.child("Users").child(newUser.username).child("ownProjectList").setValue(ownProjectArrayList);
                firebaseRef.child("Users").child(newUser.username).child("followedProjects").setValue(followedProjectsArrayList);
                Map<String, Object> changeUserMap = new HashMap<>();
                changeUserMap.put("username", modifyUsername.getText().toString());
                changeUserMap.put("password", modifyPassword.getText().toString());
                changeUserMap.put("email", modifyEmail.getText().toString());
                changeUserMap.put("mobileNumber", modifyTelNumber.getText().toString());
                firebaseRef.child("Users").child(mainUser.username).updateChildren(changeUserMap);
                Inicio.MainUser = newUser;
                finish();
            }
        });
    }
}