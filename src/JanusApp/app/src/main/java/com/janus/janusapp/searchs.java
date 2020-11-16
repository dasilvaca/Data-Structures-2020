package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button busca;
    private ImageView proPicture;
    private Project proyecto;
    private TextView id,muestraid,caracteristica,muestraCaracterisitca;
    private Trie<User> usuarios;
    private Trie<Project> proyectos;
    private Uri pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchs);
        String tipo = getIntent().getStringExtra("Tipo");
        busqueda=(EditText)findViewById(R.id.search);
        busca=(Button)findViewById(R.id.searchButton);
        proPicture=(ImageView)findViewById(R.id.imageSearch);
        id=(TextView)findViewById(R.id.textView10);
        muestraid=(TextView)findViewById(R.id.muestraid);
        caracteristica=(TextView)findViewById(R.id.textView12);
        muestraCaracterisitca=(TextView)findViewById(R.id.textView13);
        busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = busqueda.getText().toString();
                if(tipo.equals("usuario")){
                    usuarios=Inicio.userTrie;
                    User usuario=usuarios.findWord(nombre);
                    if(usuario!=null){
                        if(usuario.picture!=null){
                            pic = Uri.parse(usuario.picture);
                            Glide.with(searchs.this).load(pic).fitCenter().centerCrop().into(proPicture);
                        }
                        id.setText("Username:");muestraid.setText(usuario.username);
                        caracteristica.setText("Nombre:");muestraCaracterisitca.setText(usuario.firstName+" "+usuario.lastName);

                    }else{ Toast.makeText(searchs.this,"El usuario no existe",Toast.LENGTH_LONG).show(); }
                }
                if(tipo.equals("proyecto")){
                    proyectos=Inicio.projectTrie;
                    proyecto = proyectos.findWord(nombre);
                    if(proyecto!=null){
                        if(proyecto.picture!=null){
                            pic = Uri.parse(proyecto.picture);
                            Glide.with(searchs.this).load(pic).fitCenter().centerCrop().into(proPicture);
                        }
                        id.setText("Project name:");muestraid.setText(proyecto.name);
                        caracteristica.setText("Project goal:");muestraCaracterisitca.setText(String.valueOf(proyecto.budget));

                    }else{Toast.makeText(searchs.this,"El proyecto no existe",Toast.LENGTH_LONG).show(); }
                }
            }
        });

            proPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(searchs.this,"Entró al onclick",Toast.LENGTH_LONG).show();
                    if(tipo.equals("proyecto") && proyecto!=null) {
                        Intent intent = new Intent(searchs.this, ProjectActivity.class);
                        Gson gson = new Gson();
                        String enviable = gson.toJson(proyecto);
                        intent.putExtra("project",enviable);
                        startActivity(intent);
                    }
                }
            });

    }
}