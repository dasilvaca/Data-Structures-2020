package com.janus.janusapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.janus.janusapp.classes.User;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profileFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PICK_USER = 101;
    private User MainUser;
    TextView userName, fullName, Email, Gender, birthdate, wallet;

    /**
     * Bueno, en esta sección hago las animaciones de los botopnes para la edición del perfil,
     * así que por eso declaro las siguientes 4 ANIMACIONES, y los 3 botones
     */

    private Animation rotateOpen;//= AnimationUtils.loadAnimation(this.getContext(),R.anim.rotate_open_anim);
    private Animation rotateClose;//= AnimationUtils.loadAnimation( this.getContext(),R.anim.rotate_close_anim);
    private Animation fromBottom;//= AnimationUtils.loadAnimation(this.getContext(),R.anim.from_bottom_anim);
    private Animation toBottom;//= AnimationUtils.loadAnimation( this.getContext(),R.anim.to_bottom_anim);
    private Boolean pic;
    private StorageReference storageRef;
    private static final int PICK_IMAGE = 101;
    private Uri imageUri;
    private ImageView profileImage;
    private FloatingActionButton more_buttons;
    private FloatingActionButton edit_profile_picture;
    private FloatingActionButton edit_profile;
    private boolean clicked = false;

    private TabLayout tabLayout;

    /*=======================================================================================================/



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public profileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static profileFragment newInstance(String param1, String param2) {
        profileFragment fragment = new profileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainUser = Inicio.MainUser;
        if (getArguments() != null) {

        }
    }

    /**
     * La siguiente sección de métodos son para desplegar los botones ocultos
     */
    private void deployMoreButtons() {
        setVisibility(clicked);
        setAnimation(clicked);
        clicked = !clicked;
        setClickable(clicked);
    }

    private void setVisibility(boolean clicked) {
        if (clicked) {
            edit_profile.setVisibility(View.INVISIBLE);
            edit_profile_picture.setVisibility(View.INVISIBLE);
        } else {
            edit_profile.setVisibility(View.VISIBLE);
            edit_profile_picture.setVisibility(View.VISIBLE);
        }
    }

    private void setAnimation(boolean clicked) {
        if (clicked) {
            edit_profile_picture.startAnimation(toBottom);
            edit_profile.startAnimation(toBottom);
            more_buttons.startAnimation(rotateClose);
        } else {
            edit_profile_picture.startAnimation(fromBottom);
            edit_profile.startAnimation(fromBottom);
            more_buttons.startAnimation(rotateOpen);
        }
    }


    private void setClickable(boolean clicked) {
        if (clicked) {
            edit_profile_picture.setClickable(true);
            edit_profile.setClickable(true);
        } else {
            edit_profile_picture.setClickable(false);
            edit_profile.setClickable(false);
        }

    }

    /**
     * ==============================Hasta aquí los metodos para mostrar botones===============
     **/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        storageRef = FirebaseStorage.getInstance().getReference();

        edit_profile = view.findViewById(R.id.edit_profile);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), modifyProfileFragment.class);
                startActivityForResult(intent, PICK_USER);
            }
        });


        /**---------------------------- Inicialización del TabLayout ============================**/
        tabLayout = view.findViewById(R.id.selector);

        /**============================ Inicialización de TextViews==============================*/
        userName = view.findViewById(R.id.username);
        fullName = view.findViewById(R.id.fullname);
        Email = view.findViewById(R.id.email);
        wallet = view.findViewById(R.id.dineros);
        birthdate = view.findViewById(R.id.birthdate);
        Gender = view.findViewById(R.id.gender);
        /**=====================Luego los text View Inicializados se "Setean"=====================*/

        fullName.setText(MainUser.firstName + " " + MainUser.lastName);
        Email.setText(MainUser.email);
        userName.setText(MainUser.username);
        wallet.setText("$ " + MainUser.wallet);
        birthdate.setText(MainUser.birthDate);
        Gender.setText(MainUser.gender);
        /**===================Aquí se instancian las animaciones==================================*/
        rotateOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(getActivity(), R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(getActivity(), R.anim.to_bottom_anim);
        /**==================Aquí se instancian los botones peques================================*/
        more_buttons = view.findViewById(R.id.buttons_to_edit);
        edit_profile_picture = view.findViewById(R.id.edit_profile_picture);
        profileImage = view.findViewById(R.id.profilePicture);
        /**=============Se inicializa la base de datos con el método para traer la imagen=========*/
        /**
         * ==============================Aquí verifica si el usuario tiene imagen y si si la muestra===============
         **/
        DatabaseReference d = FirebaseDatabase.getInstance().getReference();
        pic = false;
        d.child("Users").child(MainUser.username).child("upicture").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String ubi = snapshot.getValue().toString();
                    Uri ubiUrl = Uri.parse(ubi);
<<<<<<< HEAD
                    Glide.with(profileFragment.this).load(ubiUrl).fitCenter().centerCrop().into(profileImage);
=======
                    Glide.with(getActivity()).load(ubi).fitCenter().centerCrop().into(profileImage);
>>>>>>> 2f5f7e15d75e10a994c7cd559dfa144d39d06fc7
                    pic = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /**================================Botón que trae más botonoes (Click listener================**/
        more_buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deployMoreButtons();
            }
        });

        /**
         * ==========================Acciones que realiza el botón edit_proile picture================
         */


        if (pic == false) {
            edit_profile_picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openGallery();
                }

            });
        }

        /**===========================TabListener para las tabs de los listviews=================**/

        TabLayout.Tab pestañáSeguidos = tabLayout.getTabAt(0);
        TabLayout.Tab pestañáPropios = tabLayout.getTabAt(1);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;

    }

    /**=========================Fin del OnCreateView=============================**/

    /**
     * ================================OpenGallery Function==========================================
     **/
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
/**===============================================================================================**/

<<<<<<< HEAD
                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            String ubiPic = downloadUrl.toString();
                            MainUser.picture = ubiPic;
                            DatabaseReference fbRef = FirebaseDatabase.getInstance().getReference();
                            fbRef.child("Users").child(MainUser.username).child("picture").setValue(ubiPic);
                            fbRef.child("Users").child(MainUser.username).child("upicture").setValue(imageUri.toString());
                        }
                    });

=======

        @Override
        public void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            /**
             * ==============================Aquí sube la foto al storage y la muestra===============
             **/
            if (resultCode == -1 && requestCode == PICK_IMAGE) {
                imageUri = data.getData();
                profileImage.setImageURI(imageUri);
                profileImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                StorageReference filepath = storageRef.child("Users").child(MainUser.username).child("Picture");
                filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUrl = uri;
                                String ubiPic = downloadUrl.toString();
                                MainUser.picture = ubiPic;
                                DatabaseReference fbRef = FirebaseDatabase.getInstance().getReference();
                                fbRef.child("Users").child(MainUser.username).child("picture").setValue(ubiPic);
                            }
                        });


                    }
                });
            }
>>>>>>> 2f5f7e15d75e10a994c7cd559dfa144d39d06fc7

            if (requestCode == PICK_USER){
                fullName.setText(MainUser.firstName + " " + MainUser.lastName);
                Email.setText(MainUser.email);
                userName.setText(MainUser.username);
                wallet.setText("$ " + MainUser.wallet);
                birthdate.setText(MainUser.birthDate);
                Gender.setText(MainUser.gender);
            }
        }
    }
<<<<<<< HEAD


}



/**
 * Aquí declaro los "Clicklisteners" de los 3 botones, el que despliega ambos,
 * y el que edita el perfil, como el que edita sólo la foto
 */
=======
    /**==========================================================================================**/
>>>>>>> 2f5f7e15d75e10a994c7cd559dfa144d39d06fc7
