package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.janus.janusapp.classes.Project;
import com.janus.janusapp.structs.DynamicArray;
import com.janus.janusapp.structs.DynamicArrayS;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ProjectByCat extends AppCompatActivity {
    private DynamicArrayS proyectos;
    private ListView listView;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_by_cat);
        listView=(ListView)findViewById(R.id.listview_projects);
        text=(TextView)findViewById(R.id.textView11);
        String category = getIntent().getStringExtra("cat");
        text.setText("Estos son los proyectos de "+category+":");
        switch (category){
            case("food"):
                proyectos=Inicio.Food;
                break;
            case("software"):
                proyectos=Inicio.Software;
                break;
            case("technology"):
                proyectos=Inicio.Technology;
                break;
            case("art"):
                proyectos=Inicio.Art;
                break;
            case("entertainment"):
                proyectos=Inicio.Entertainment;
                break;
            case("services"):
                proyectos=Inicio.Services;
                break;
            case("science"):
                proyectos=Inicio.Science;
                break;
            case("education"):
                proyectos=Inicio.Education;
                break;
            case("other"):
                proyectos=Inicio.Other;
                break;
            default:
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProjectByCat.this,R.layout.list_item,Arrays.copyOfRange(proyectos.array,0,proyectos.size));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project project = Inicio.projectTrie.findWord(proyectos.get(position)) ;
                Gson gson = new Gson();
                String enviable = gson.toJson(project);
                Intent intent = new Intent(ProjectByCat.this,ProjectActivity.class);
                intent.putExtra("project",enviable);
                startActivity(intent);
            }
        });
    }
}