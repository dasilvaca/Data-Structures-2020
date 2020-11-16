package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.janus.janusapp.classes.Project;
import com.janus.janusapp.classes.User;
import com.janus.janusapp.structs.Trie;

public class searchs extends AppCompatActivity {
    EditText busqueda;
    Button busca;
    Trie<User> usuarios;
    Trie<Project> proyectos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchs);
        String tipo = getIntent().getStringExtra("Tipo");
        busqueda=(EditText)findViewById(R.id.search);
        busca=(Button)findViewById(R.id.searchButton);
        busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = busqueda.getText().toString();
                if(tipo=="usuario"){
                    usuarios=Inicio.userTrie;
                    User usuario=usuarios.findWord(nombre);
                    if(usuario!=null){

                    }
                }
                if(tipo=="proyecto"){
                    proyectos=Inicio.projectTrie;
                    Project proyecyo = proyectos.findWord(nombre);
                    if(proyecyo!=null){

                    }
                }
            }
        });
    }
}