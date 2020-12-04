package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.janus.janusapp.classes.Project;
import com.janus.janusapp.classes.User;
import com.janus.janusapp.structs.Trie;

public class searchs extends AppCompatActivity {
    private EditText busqueda;
    private ImageButton busca;
    private ImageView proPicture;
    private Project proyecto;
    private TextView id,muestraid,caracteristica,muestraCaracterisitca;
    private Trie<User> usuarios;
    private Trie<Project> proyectos;
    private Uri pic;
    private String type;
    private User usuario;
    private User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new Gson();
        String gsonUser=getIntent().getStringExtra("user");
        currentUser = gson.fromJson(gsonUser,User.class);

        setContentView(R.layout.activity_searchs);
        String tipo = getIntent().getStringExtra("Tipo");
        busqueda=(EditText)findViewById(R.id.search);
        busca=(ImageButton)findViewById(R.id.lupitaSearchButton);
        proPicture=(ImageView)findViewById(R.id.imageSearch);
        muestraid=(TextView)findViewById(R.id.muestraid);
        muestraCaracterisitca=(TextView)findViewById(R.id.textView13);
        busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarios=Inicio.userTrie;
                String nombre = busqueda.getText().toString();
                usuario=usuarios.findWord(nombre);
                proyectos=Inicio.projectTrie;
                proyecto = proyectos.findWord(nombre);
                if(usuario!=null){
                    if(usuario.picture!=null){
                        pic = Uri.parse(usuario.picture);
                        Glide.with(searchs.this).load(pic).fitCenter().centerCrop().into(proPicture);
                    } else{
                        Glide.with(searchs.this).load(R.drawable.ic_person).fitCenter().centerCrop().into(proPicture);
                    }
                    muestraid.setText(usuario.username);
                    muestraCaracterisitca.setText(usuario.firstName+" "+usuario.lastName);
                    type = "usuario";
                } else if(proyecto!=null){
                    if(proyecto.picture!=null){
                        pic = Uri.parse(proyecto.picture);
                        Glide.with(searchs.this).load(pic).fitCenter().centerCrop().into(proPicture);
                    } else{
                        Glide.with(searchs.this).load(R.drawable.default_project_picture).fitCenter().centerCrop().into(proPicture);
                    }
                    muestraid.setText(proyecto.name);
                    muestraCaracterisitca.setText(String.valueOf(proyecto.budget));
                    type = "proyecto";
                } else{
                    Toast.makeText(searchs.this, "There are not search results :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

            proPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(type.equals("proyecto")){
                        Intent intent = new Intent(searchs.this, ProjectActivity.class);
                        Gson gson = new Gson();
                        String enviable = gson.toJson(proyecto);
                        String gsonU=gson.toJson(currentUser);
                        intent.putExtra("user",gsonU);
                        intent.putExtra("project",enviable);
                        startActivity(intent);
                    } else if(type.equals("usuario")){
                        Intent intent = new Intent(searchs.this, UserActivity.class);
                        Gson gson = new Gson();
                        String enviable = gson.toJson(usuario);
                        intent.putExtra("user",enviable);
                        startActivity(intent);
                    }
                }
            });

    }
}