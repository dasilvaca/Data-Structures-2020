package com.janus.janusapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.janus.janusapp.classes.Project;
import com.janus.janusapp.classes.User;
import com.janus.janusapp.structs.DynamicArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link newProjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class newProjectFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Gson gson;
    private User MainUser;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DatabaseReference dataBaseRef;
    private Spinner category;
    private EditText projectName, projectGoal,projectDscr;
    private Button create;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public User hola;
    String MainUserStr;
    public newProjectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newProjectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static newProjectFragment newInstance(String param1, String param2) {
        newProjectFragment fragment = new newProjectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_project, container, false);
        dataBaseRef = FirebaseDatabase.getInstance().getReference();
        category =(Spinner)view.findViewById(R.id.spinner_cat);
        projectGoal=(EditText)view.findViewById(R.id.editTextGoal);
        projectName=(EditText)view.findViewById(R.id.editTextTextProjectName);
        projectDscr=(EditText)view.findViewById(R.id.editTextDscr);
        String[] categories = {"Food","Software", "Technology", "Accesories","Art","Entertainment","Services","Science","Education","Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,categories);
        category.setAdapter(adapter);
        create=(Button)view.findViewById(R.id.Crear);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String Pname = projectName.getText().toString();
                 int Pgoal = Integer.parseInt(projectGoal.getText().toString());
                 String Pdescription = projectDscr.getText().toString();
                 String Pcategory= category.getSelectedItem().toString();

                 Project newProject = new Project(Pname, Inicio.MainUser, Pgoal,Pcategory,Pdescription);
                 Toast.makeText(getContext(),"Proyecto creado",Toast.LENGTH_LONG).show();
                 projectName.setText("");
                 projectGoal.setText("");
                 projectDscr.setText("");
                 //ArrayList<String>  = new ArrayList<>(Arrays.asList(newProject.owners.array));
                 //dataBaseRef.child("Project").child(Pname).setValue(new ArrayList<>(Arrays.asList(categories)));

                 ArrayList<String> subible = new ArrayList<>(Arrays.asList(newProject.owners.array));
                 newProject.owners=null;
                 newProject.followers=null;
                 dataBaseRef.child("Project").child((String)Pname).setValue(newProject);
                 dataBaseRef.child("Project").child(Pname).child("owners").setValue(subible);
                 //dataBaseRef.child("Project").child(Pname).child("followers").setValue(subible);


            }
        });
        return view;
    }


}