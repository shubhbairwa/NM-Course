package com.shubhcode.nmcourse;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
public class MeFragment extends Fragment {
    private static final String TAG = "MeFragment";

    FirebaseAuth mAuth;
    public TextView nEmailId, nFullName;
    private ImageView nProfilePic;
    Button logoutbtn;
    private Uri imageUri;
    FirebaseStorage storage;
    StorageReference mStorageRef;

    FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me, container, false);
        Log.d(TAG, "onCreateView:opening ");
        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        //finding elements through code
        nEmailId = view.findViewById(R.id.userEmail);
        nFullName = view.findViewById(R.id.userName);

        nProfilePic = view.findViewById(R.id.profile_Image);

        logoutbtn = view.findViewById(R.id.logoutButton);
        mStorageRef = FirebaseStorage.getInstance().getReference(" User Pics");


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String name = user.getDisplayName();
        imageUri = user.getPhotoUrl();

        if (!name.equals("")) {
            nFullName.setText(name);
        } else {

            nFullName.setText(user.getPhoneNumber());
            nProfilePic.setImageResource(R.drawable.logo);
        }


        String email = user.getEmail();
        nEmailId.setText(email);


        Glide.with(getActivity()).load(imageUri).into(nProfilePic);



        getListenAuthOfUser();


        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
            }
        });

        return view;
    }


    private void getListenAuthOfUser() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);

                }

            }
        };
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener == null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }
}
