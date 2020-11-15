package com.janus.janusapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.janus.janusapp.classes.User;

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
        modifyUsername = findViewById(R.id.ModifyUsername);
        modifyPassword = findViewById(R.id.ModifyPassword);
        modifyEmail = findViewById(R.id.ModifyEmail);
        modifyTelNumber = findViewById(R.id.ModifyTelNumber);
        currentProfileImage = findViewById(R.id.currentProfileImage);
        Uri ubiUrl = Uri.parse(mainUser.picture);
        Glide.with(modifyProfileFragment.this).load(ubiUrl).fitCenter().centerCrop().into(currentProfileImage);

        modifyUsername.setText(mainUser.username);
        modifyPassword.setText(mainUser.password);
        modifyEmail.setText(mainUser.email);
        modifyTelNumber.setText(mainUser.mobileNumber);

        saveChangesButton = findViewById(R.id.saveChangesButton);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> changeUserMap = new HashMap<>();
                changeUserMap.put("username", modifyUsername.getText().toString());
                changeUserMap.put("password", modifyPassword.getText().toString());
                changeUserMap.put("email", modifyEmail.getText().toString());
                changeUserMap.put("mobileNumber", modifyTelNumber.getText().toString());

                firebaseRef.child("Users").child(mainUser.username).updateChildren(changeUserMap);
            }
        });
    }
}