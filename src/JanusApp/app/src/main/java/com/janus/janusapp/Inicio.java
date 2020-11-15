package com.janus.janusapp;

import android.app.Activity;
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
    public static DynamicArray<Project> Food, Software, Technology, Accesories, Art, Entertainment, Services, Science, Education, Other;
    Gson gson;
    public static FragmentTransaction fragmentTransaction;
    public static User MainUser;
    public static FragmentManager fragmentManager;
    private Fragment selectedFragment = new newProjectFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        {Food=new DynamicArray<Project>();
        Software = new DynamicArray<Project>();
        Technology = new DynamicArray<Project>();
        Accesories = new DynamicArray<Project>();
        Art = new DynamicArray<Project>();
        Entertainment = new DynamicArray<Project>();
        Services = new DynamicArray<Project>();
        Science = new DynamicArray<Project>();
        Education = new DynamicArray<Project>();
        Other = new DynamicArray<Project>();}
        String MainUserStr = getIntent().getStringExtra("usuario");
        Food.append(new Project());
        gson = new Gson();
        MainUser = gson.fromJson(MainUserStr, User.class);
        newProjectFragment frag = new newProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putString("usuario", MainUserStr);
        frag.setArguments(bundle);
        BottomNavigationView bottomnav = findViewById(R.id.bottomNavigationView2);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, selectedFragment).commit();
        DatabaseReference firebaseref = FirebaseDatabase.getInstance().getReference();

        /**
         * =======================Sección comentada para pruebas del Joselo=====================================
         */
        firebaseref.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        User newUser = new User(ds.child("firstName").getValue().toString(),ds.child("lastName").getValue().toString(),
                                ds.child("email").getValue().toString(),ds.child("username").getValue().toString(),
                                ds.child("mobileNumber").getValue().toString(),ds.child("password").getValue().toString(),
                                ds.child("birthDate").getValue().toString(),ds.child("gender").getValue().toString());
                        ArrayList<String> ownProjectArrayList = (ArrayList<String>) ds.child("ownProjectList").getValue();
                        ArrayList<String> followedProjectsArrayList= (ArrayList<String>) ds.child("followedProjects").getValue();
                        DynamicArrayS ownProjectList = new DynamicArrayS((String[]) ownProjectArrayList.toArray(new String[0]));
                        DynamicArrayS followedProjects = new DynamicArrayS((String[]) followedProjectsArrayList.toArray(new String[0]));
                        newUser.ownProjectList= ownProjectList;
                        newUser.followedProjects= followedProjects;
                        if(ds.child("picture").exists()){
                            newUser.picture=ds.child("picture").getValue().toString();
                        }
                        //userTrie.addWord(newUser.username,newUser);
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }); //Trae usuarios de la BD y los mete a un trie
        firebaseref.child("Project").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(Inicio.this,"JEJEJE",Toast.LENGTH_LONG).show();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Project newProject = new Project(ds.child("name").getValue().toString(),
                            Integer.parseInt(ds.child("budget").getValue().toString()),
                            ds.child("category").getValue().toString(),ds.child("description").getValue().toString());
                    ArrayList<String> ownersArrayList = (ArrayList<String>) ds.child("owners").getValue();
                    ArrayList<String> followersArrayList= (ArrayList<String>) ds.child("followers").getValue();
                    DynamicArrayS owners = new DynamicArrayS((String[]) ownersArrayList.toArray(new String[0]));
                    DynamicArrayS followers = new DynamicArrayS((String[]) followersArrayList.toArray(new String[0]));
                    newProject.owners=owners;
                    newProject.followers = followers;
                    //projectTrie.addWord(newProject.name,newProject);
                    /*    switch(newProject.category){
                            case("Food"):
                                Food.append(newProject);
                                break;
                            case("Software"):
                                Software.append(newProject);
                                break;
                            case("Technology"):
                                Technology.append(newProject);
                                break;
                            case("Art"):
                                Art.append(newProject);
                                break;
                            case("Entertainment"):
                                Entertainment.append(newProject);
                                break;
                            case("Services"):
                                Services.append(newProject);
                                break;
                            case("Science"):
                                Science.append(newProject);
                                break;
                            case("Education"):
                                Education.append(newProject);
                                break;
                            case("Other"):
                                Other.append(newProject);
                                break;
                            default:
                                break;
                        }*/
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // bottomnav.setSelectedItemId(R.id.homeFragment);
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
                            selectedFragment = new searchFragment();
                            break;
                        default:
                            selectedFragment = new newProjectFragment();
                            break;
                    }
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment,selectedFragment).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, selectedFragment).commit();
                    return true;
                }
            };
    /** ==========================Hasta aquí el selector de fragment=============================**/


}