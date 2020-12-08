package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.janus.janusapp.classes.Project;
import com.janus.janusapp.classes.User;
import com.janus.janusapp.structs.DynamicArray;
import com.janus.janusapp.structs.HashTable;
import com.janus.janusapp.structs.Trie;

public class searchs extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private EditText busqueda;
    private ImageButton busca;
    private ImageView proPicture;
    private Project proyecto;
    private TextView id,muestraid,caracteristica,muestraCaracterisitca;
    private Trie<String> usuarios;
    private Trie<String> proyectos;
    private Uri pic;
    private String type;
    private User currentUser;
    private SearchView searchView;
    private ListView recomendaciones;
    private Trie<String> projects;
    private Trie<String> users;
    private HashTable<String,User> userTable = Inicio.userTable;
    private HashTable<String,Project> projectTable = Inicio.projectTable;
    private User showableUser;
    private Project showableProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchs);
        Gson gson = new Gson();
        String gsonUser=getIntent().getStringExtra("user");

        users=Inicio.userNameTrie;
        projects=Inicio.projectNameTrie;
        String[] options = {"Search Users","Search Projects"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,options);

        recomendaciones=findViewById(R.id.recomendar);
        currentUser = gson.fromJson(gsonUser,User.class);
        searchView = findViewById(R.id.searchv);
        String tipo = getIntent().getStringExtra("Tipo");
        proPicture=(ImageView)findViewById(R.id.imageSearch);
        muestraid=(TextView)findViewById(R.id.muestraid);
        muestraCaracterisitca=(TextView)findViewById(R.id.textView13);
        searchView.setOnQueryTextListener(this);
   /**     busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarios=Inicio.userNameTrie;
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
        });**/

           proPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(type.equals("proyecto")){
                        Intent intent = new Intent(searchs.this, ProjectActivity.class);
                        Gson gson = new Gson();
                        String enviable = gson.toJson(showableProject);
                        String gsonU=gson.toJson(currentUser);
                        intent.putExtra("user",gsonU);
                        intent.putExtra("project",enviable);
                        startActivity(intent);
                    } else if(type.equals("usuario")){
                        Intent intent = new Intent(searchs.this, UserActivity.class);
                        Gson gson = new Gson();
                        String enviable = gson.toJson(showableUser);
                        intent.putExtra("user",enviable);
                        startActivity(intent);
                    }
                }
            });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(users.findWord(query)!=null){
            showableUser = userTable.find(query);
            if (showableUser.picture != null) {
                pic = Uri.parse(showableUser.picture);
                Glide.with(searchs.this).load(pic).fitCenter().centerCrop().into(proPicture);
            } else {
                Glide.with(searchs.this).load(R.drawable.ic_person).fitCenter().centerCrop().into(proPicture);
            }
            muestraid.setText(showableUser.username);
            muestraCaracterisitca.setText(showableUser.firstName + " " + showableUser.lastName);
            type = "usuario";
        }else if(projects.findWord(query)!=null){
            showableProject = projectTable.find(query);
            if (showableProject.picture != null) {
                pic = Uri.parse(showableProject.picture);
                Glide.with(searchs.this).load(pic).fitCenter().centerCrop().into(proPicture);
            } else {
                Glide.with(searchs.this).load(R.drawable.ic_person).fitCenter().centerCrop().into(proPicture);
            }
            muestraid.setText(showableProject.name);
            muestraCaracterisitca.setText(showableProject.description);
            type = "proyecto";
        }else{
            Toast.makeText(searchs.this,"There are no results for this query",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String[] totalSugest;
        if( newText.length()<3){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new String[0]);
            recomendaciones.setAdapter(adapter);
        }
        if( newText.length()>=3) {
            Object[] userSugests = users.findSuggestions(newText);
            Object[] projectSugests = projects.findSuggestions(newText);
            int total= 0;
            if (userSugests != null) {
                total += userSugests.length;
            }
            if(projectSugests!= null) {
                total += projectSugests.length;
            }
            if(projectSugests!= null || userSugests != null) {
            totalSugest = new String[total];

            if (userSugests != null && projectSugests!= null) {
                for (int i = 0; i < userSugests.length; i++) {
                    totalSugest[i] = (String) userSugests[i];
                }

                for (int j = userSugests.length; j < total; j++) {
                    totalSugest[j] = (String) projectSugests[j];
                }
            }else if(userSugests == null){
                for (int i = 0; i < projectSugests.length; i++) {
                    totalSugest[i] = (String) projectSugests[i];
                }
            }else if(projectSugests == null){
                for (int i = 0; i < userSugests.length; i++) {
                    totalSugest[i] = (String) userSugests[i];
                }
            }


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,totalSugest);
            recomendaciones.setAdapter(adapter);
                recomendaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (users.findWord(totalSugest[position]) != null) {
                            showableUser = userTable.find(totalSugest[position]);
                            Toast.makeText(searchs.this, showableUser.firstName, Toast.LENGTH_SHORT).show();
                            if (showableUser.picture != null) {
                                pic = Uri.parse(showableUser.picture);
                                Glide.with(searchs.this).load(pic).fitCenter().centerCrop().into(proPicture);
                            } else {
                                Glide.with(searchs.this).load(R.drawable.ic_person).fitCenter().centerCrop().into(proPicture);
                            }
                            muestraid.setText(showableUser.username);
                            muestraCaracterisitca.setText(showableUser.firstName + " " + showableUser.lastName);
                            type = "usuario";
                        } else if (projects.findWord(totalSugest[position]) != null) {
                            showableProject = projectTable.find(totalSugest[position]);
                            Toast.makeText(searchs.this, showableProject.name, Toast.LENGTH_SHORT).show();
                            if (showableProject.picture != null) {
                                pic = Uri.parse(showableProject.picture);
                                Glide.with(searchs.this).load(pic).fitCenter().centerCrop().into(proPicture);
                            } else {
                                Glide.with(searchs.this).load(R.drawable.ic_person).fitCenter().centerCrop().into(proPicture);
                            }
                            muestraid.setText(showableProject.name);
                            muestraCaracterisitca.setText(showableProject.description);
                            type = "proyecto";
                        }
                    }
                    });
            }
        }
        return false;

    }
}