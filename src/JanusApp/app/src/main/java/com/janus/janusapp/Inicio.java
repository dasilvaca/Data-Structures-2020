package com.janus.janusapp;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.janus.janusapp.classes.Project;
import com.janus.janusapp.classes.User;
import com.janus.janusapp.structs.DynamicArray;
import com.janus.janusapp.structs.DynamicArrayS;
import com.janus.janusapp.structs.Trie;

import java.util.ArrayList;

public class Inicio extends FragmentActivity {
    public static Trie<Project> projectTrie;
    public static Trie<User> userTrie;
    public static DynamicArrayS Food, Software, Technology, Accesories, Art, Entertainment, Services, Science, Education, Other;
    Gson gson;
    public static int num_projects;
    public static FragmentTransaction fragmentTransaction;
    public static User MainUser;
    public static boolean entro = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        /**
         * Estos DynamicArray guardan los proyectos por categorías
         */
        projectTrie = new Trie<Project>();
        userTrie = new Trie<User>();
        {
            Food = new DynamicArrayS();
            Software = new DynamicArrayS();
            Technology = new DynamicArrayS();
            Accesories = new DynamicArrayS();
            Art = new DynamicArrayS();
            Entertainment = new DynamicArrayS();
            Services = new DynamicArrayS();
            Science = new DynamicArrayS();
            Education = new DynamicArrayS();
            Other = new DynamicArrayS();
        }
        String MainUserStr = getIntent().getStringExtra("usuario");
        gson = new Gson();
        MainUser = gson.fromJson(MainUserStr, User.class);
        newProjectFragment frag = new newProjectFragment(); //Posible error
        Bundle bundle = new Bundle();
        bundle.putString("usuario", MainUserStr);
        frag.setArguments(bundle);
        BottomNavigationView bottomnav = findViewById(R.id.bottomNavigationView2);
        bottomnav.setOnNavigationItemSelectedListener(navListener);

        DatabaseReference firebaseref = FirebaseDatabase.getInstance().getReference();

        /**
         * =======================Esta parte baja los usuarios y los mete en un trie=====================================
         */
        firebaseref.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    User newUser = new User(ds.child("firstName").getValue().toString(), ds.child("lastName").getValue().toString(),
                            ds.child("email").getValue().toString(), ds.child("username").getValue().toString(),
                            ds.child("mobileNumber").getValue().toString(), ds.child("password").getValue().toString(),
                            ds.child("birthDate").getValue().toString(), ds.child("gender").getValue().toString());
                    ArrayList<String> ownProjectArrayList = (ArrayList<String>) ds.child("ownProjectList").getValue();
                    ArrayList<String> followedProjectsArrayList = (ArrayList<String>) ds.child("followedProjects").getValue();
                    DynamicArrayS ownProjectList = new DynamicArrayS(ownProjectArrayList.toArray(new String[0]));
                    DynamicArrayS followedProjects = new DynamicArrayS(followedProjectsArrayList.toArray(new String[0]));
                    newUser.ownProjectList = ownProjectList;
                    newUser.followedProjects = followedProjects;
                    if (ds.child("picture").exists()) {
                        newUser.picture = ds.child("picture").getValue().toString();
                    }
                    userTrie.addWord(newUser.username, newUser);
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }); /**
         * =======================Esta parte baja los proyectos y los mete en un trie y los separa por categorías en los DynArrays=====================================
         */
        firebaseref.child("Project").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                num_projects = 0;

                for (DataSnapshot ds : snapshot.getChildren()) {
                    num_projects++;
                    Project newProject = new Project(ds.child("name").getValue().toString(),
                            Integer.parseInt(ds.child("budget").getValue().toString()),
                            ds.child("category").getValue().toString(), ds.child("description").getValue().toString());

                    ArrayList<String> ownersArrayList = (ArrayList<String>) ds.child("owners").getValue();
                    ArrayList<String> followersArrayList = (ArrayList<String>) ds.child("followers").getValue();
                    DynamicArrayS owners = new DynamicArrayS();
                    DynamicArrayS followers = new DynamicArrayS();
                    if (ownersArrayList != null) {
                        owners = new DynamicArrayS((String[]) ownersArrayList.toArray(new String[0]));
                    }
                    if (followersArrayList != null) {
                        followers = new DynamicArrayS((String[]) followersArrayList.toArray(new String[0]));
                    }
                    newProject.owners = owners;
                    newProject.followers = followers;
                    if (ds.child("picture").exists()) {
                        newProject.picture = ds.child("picture").getValue().toString();
                    }

                    projectTrie.addWord(newProject.name, newProject);
                    switch (newProject.category) {
                        case ("Food"):
                            Food.append(newProject.name);
                            break;
                        case ("Software"):
                            Software.append(newProject.name);
                            break;
                        case ("Technology"):
                            Technology.append(newProject.name);
                            break;
                        case ("Art"):
                            Art.append(newProject.name);
                            break;
                        case ("Entertainment"):
                            Entertainment.append(newProject.name);
                            break;
                        case ("Services"):
                            Services.append(newProject.name);
                            break;
                        case ("Science"):
                            Science.append(newProject.name);
                            break;
                        case ("Education"):
                            Education.append(newProject.name);
                            break;
                        case ("Other"):
                            Other.append(newProject.name);
                            break;
                        default:
                            break;
                    }
                }
                if (!entro) {
                    entro = true;
                    bottomnav.setSelectedItemId(R.id.homeFragment);
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
/**
 * ===========================================================================================================
 */
    /**
     * Bueno, aquí es donde se cambian por defecto las vistas de la aplicación. Por defecto, es decir, cuando arranca se manda a Home
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment;
                    switch (item.getItemId()) {
                        case R.id.configFragment:
                            selectedFragment = new configFragment();
                            break;
                        case R.id.homeFragment:
                            selectedFragment = new homeFragment();
                            break;
                        case R.id.newProjectFragment:
                            selectedFragment = new newProjectFragment();
                            break;
                        case R.id.profileFragment:
                            selectedFragment = new profileFragment();
                            break;
                        case R.id.searchFragment:
                            String gsonU=gson.toJson(MainUser);
                            Intent intent = new Intent(Inicio.this, searchs.class);
                            intent.putExtra("user",gsonU);
                            startActivity(intent);
                        default:
                            selectedFragment = new newProjectFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, selectedFragment).commit();
                    return true;
                }
            };
    /** ==========================Hasta aquí el selector de fragment=============================**/


}