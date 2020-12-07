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
    private User usuario;
    private User currentUser;
    private SearchView searchView;
    private ListView recomendaciones;
    private Spinner Project_User;
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
        Project_User = (Spinner)findViewById(R.id.spinner);
        users=Inicio.userNameTrie;
        projects=Inicio.projectNameTrie;
        String[] options = {"Search Users","Search Projects"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,options);
        Project_User.setAdapter(adapter);
        recomendaciones=findViewById(R.id.recomendar);
        currentUser = gson.fromJson(gsonUser,User.class);
        searchView = findViewById(R.id.searchv);
        String tipo = getIntent().getStringExtra("Tipo");
        busqueda=(EditText)findViewById(R.id.search);
        busca=(ImageButton)findViewById(R.id.lupitaSearchButton);
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(Project_User.getSelectedItem().toString().equals("Search Users")){
            showableUser = userTable.find(query);
            if(showableUser!=null){
                Toast.makeText(searchs.this,showableUser.firstName,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(searchs.this,"The user does not exist",Toast.LENGTH_SHORT).show();
            }
        }else if(Project_User.getSelectedItem().toString().equals("Search Projects")){
            showableProject = projectTable.find(query);
            if(showableProject!=null){
                Toast.makeText(searchs.this,showableProject.description,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(searchs.this,"The project does not exist",Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String[] UserSugest;
        String[] ProjectSugest;
        if(Project_User.getSelectedItem().toString().equals("Search Users") && newText.length()>=3){
            Object[] sugests =users.findSuggestions(newText);
            UserSugest = new String[sugests.length];
            for(int i=0;i<sugests.length;i++){
                UserSugest[i]=(String)sugests[i];
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,UserSugest);
            recomendaciones.setAdapter(adapter);
            recomendaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showableUser = userTable.find(UserSugest[position]);
                    Toast.makeText(searchs.this,showableUser.firstName,Toast.LENGTH_SHORT).show();
                }
            });

        }else if(Project_User.getSelectedItem().toString().equals("Search Projects") && newText.length()>=3){
            Object[] sugests = users.findSuggestions(newText);
            ProjectSugest = new String[sugests.length];
            for(int i=0;i<sugests.length;i++){
                ProjectSugest[i]=(String)sugests[i];
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ProjectSugest);
            recomendaciones.setAdapter(adapter);
            recomendaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showableProject = projectTable.find(ProjectSugest[position]);
                    Toast.makeText(searchs.this,showableProject.description,Toast.LENGTH_SHORT).show();
                }
            });
        }



        return false;
    }


}