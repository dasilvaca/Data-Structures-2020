package com.janus.janusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.janus.janusapp.structs.DynamicArray;
import com.janus.janusapp.structs.DynamicArrayS;

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProjectByCat.this,R.layout.list_item,proyectos.array);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ProjectByCat.this,proyectos.get(position),Toast.LENGTH_LONG).show();
            }
        });
    }
}