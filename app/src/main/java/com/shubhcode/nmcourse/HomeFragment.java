package com.shubhcode.nmcourse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    public RecyclerView pickedRecyclerView;
    public RecyclerUserPickedChapter mRecyclerUserPickedChapter;
    private ArrayList<ClassUserPickedChapter> pickedChaptersList;
    private DatabaseReference mHomeDatabaseRef;
    FirebaseAuth mAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: home create view");
        View view = inflater.inflate(R.layout.home, container, false);
        mAuth = FirebaseAuth.getInstance();
        pickedRecyclerView = view.findViewById(R.id.homerecycler);
        pickedRecyclerView.setHasFixedSize(true);
        pickedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pickedChaptersList = new ArrayList<>();
        mHomeDatabaseRef = FirebaseDatabase.getInstance().getReference("ChapterPickedByUsers");

        mHomeDatabaseRef.addChildEventListener(homeChildEventListener());


        return view;
    }

    private ChildEventListener homeChildEventListener() {
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Log.d(TAG, "for loop: opening h");

                        ClassUserPickedChapter classUserPickedChapterList = postSnapshot.getValue(ClassUserPickedChapter.class);
                        pickedChaptersList.add(classUserPickedChapterList);


                }
                mRecyclerUserPickedChapter = new RecyclerUserPickedChapter(getContext(), pickedChaptersList);
                pickedRecyclerView.setAdapter(mRecyclerUserPickedChapter);
                mRecyclerUserPickedChapter.setOnItemClickListener(new RecyclerUserPickedChapter.onItemClickUserPickedRrecycle() {
                    @Override
                    public void onPickedClicked(int position) {
                        Intent intent = new Intent(getContext(), ActivityForVideos.class);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        return childEventListener;

    }
}