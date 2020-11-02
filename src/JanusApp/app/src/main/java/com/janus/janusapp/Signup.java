package com.janus.janusapp;

import android.app.Activity;
import android.os.Bundle;


import com.google.firebase.database.DatabaseReference;

public class Signup extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseReference mRootReference;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }


}
