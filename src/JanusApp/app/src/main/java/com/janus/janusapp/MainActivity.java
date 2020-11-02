package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.janus.janusapp.structs.*;

//public static boolean opened = false;
public class MainActivity extends AppCompatActivity {
    TextView prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD





=======
        prueba=(TextView)findViewById(R.id.prueba);
        setContentView(R.layout.login_screen);
>>>>>>> 10782bd36377fa227f815606474dd6ecab2f2513
    }
}