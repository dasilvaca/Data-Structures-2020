package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Categorys extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorys);

        intent = new Intent(Categorys.this,ProjectByCat.class);
    }
    public void food(View v){
        intent.putExtra("cat","food");
        startActivity(intent);
    }
    public void software(View v){
        intent.putExtra("cat","software");
        startActivity(intent);
    }
    public void technology(View v){
        intent.putExtra("cat","technology");
        startActivity(intent);
    }
    public void accesories(View v){
        intent.putExtra("cat","accesories");
        startActivity(intent);
    }
    public void art(View v){
        intent.putExtra("car","art");
        startActivity(intent);
    }
    public void entertainment(View v){
        intent.putExtra("cat","entertainment");
        startActivity(intent);
    }
    public void services(View v){
        intent.putExtra("cat","services");
        startActivity(intent);
    }
    public void science(View v){
        intent.putExtra("cat","science");
        startActivity(intent);
    }
    public void education(View v){
        intent.putExtra("cat","education");
        startActivity(intent);
    }
    public void other(View v){
        intent.putExtra("cat","other");
        startActivity(intent);
    }

}