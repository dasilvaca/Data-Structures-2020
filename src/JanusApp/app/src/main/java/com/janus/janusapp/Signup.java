package com.janus.janusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.firebase.database.DatabaseReference;

public class Signup extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseReference mRootReference;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }
}
