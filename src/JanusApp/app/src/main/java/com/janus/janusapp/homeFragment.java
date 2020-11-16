package com.janus.janusapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.janus.janusapp.classes.Project;
import com.janus.janusapp.structs.DynamicArrayS;
import com.janus.janusapp.structs.Queue;
import com.janus.janusapp.structs.Stack;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
/**
* =========AQuí instancio las cosas que se usan========================
 **/
    private int total;
    private Queue<String> projectQueue=new Queue<String>();
    private Stack<Project> previous = new Stack<Project>();
    private Stack<Project> next = new Stack<Project>();
    private DynamicArrayS[] array =  {Inicio.Food,Inicio.Software,Inicio.Technology,
                            Inicio.Accesories,Inicio.Art,Inicio.Entertainment,Inicio.Services,
                            Inicio.Science,Inicio.Education,Inicio.Other};
    private int aleatorio=0;
    private FloatingActionButton previousButton,nextButton,infoButton;
    private Project mostrable;
//**======================================================
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        previousButton=view.findViewById(R.id.floatingActionButton3);
        nextButton=view.findViewById(R.id.floatingActionButton2);
        infoButton=view.findViewById(R.id.floatingActionButton4);
        int xd = 0;

        total=Inicio.num_projects;
        while(projectQueue.size<total){
            aleatorio = (int)(Math.random()*(total-1));
            String ProyectoCualquiera =cual(aleatorio);
            projectQueue.enqueue(ProyectoCualquiera);
        }

        showProject(Inicio.projectTrie.findWord(projectQueue.dequeue()));
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(next.isEmpty()){
                    mostrable =Inicio.projectTrie.findWord(projectQueue.dequeue());
                }else{
                    mostrable=next.pop();
                }

                showProject(mostrable);
                previous.push(mostrable);
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrable = previous.pop();
                showProject(mostrable);
                next.push(mostrable);

            }
        });
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(),ProjectActivity.class);
                Gson gson = new Gson();
                intent.putExtra("project",gson.toJson(intent));
                startActivity(intent);
            }
        });
        return view;
    }

    private void showProject(Project project){
        Toast.makeText(getContext(),project.name,Toast.LENGTH_SHORT).show();
    }
    private String cual(int pos){
        int sumatoria=0;
        for(int i=0;i<9;i++){

            if((sumatoria+array[i].size)>pos){
                    if(projectQueue.in(array[i].array[pos-sumatoria]) ){
                        return cual((pos+1)%total);
                    }
                    return array[i].array[pos-sumatoria];
            }
            sumatoria+=array[i].size;
        }
        return "";
    }

}