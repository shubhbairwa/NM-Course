package com.shubhcode.nmcourse;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class StoreFragment extends Fragment {
    public final static String TITLEW = "title";
    public final static String URL = "url";
    public RecyclerView mRecyclerViewCourseCard;
    public RecyclerViewCourseDetails mRecyclerViewCourseDetailsAdapter;

    // private RecyclerViewCourseDetails mRecyclerViewCourseDetails;
    Context mcont;
    private List<ClassChapterDetails> mClassChapterDetailsList;
    private DatabaseReference mDatabaseRef;
    FirebaseAuth mAuth;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course, container, false);
        mAuth = FirebaseAuth.getInstance();
        mRecyclerViewCourseCard = view.findViewById(R.id.recyclerviewCourseDetails);
        mRecyclerViewCourseCard.setHasFixedSize(true);
        mRecyclerViewCourseCard.setLayoutManager(new LinearLayoutManager(getContext()));


//        setSupportActionBar((Toolbar)findViewById(R.id.store_toolbar));
//        setTitle("Notes");

        //progressDialog
         builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
         dialog = builder.create();
        dialog.show();


        mClassChapterDetailsList = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Chapter Details");

        mDatabaseRef.addValueEventListener(storeValueEventListener());

        return view;



    }
    private ValueEventListener storeValueEventListener(){
        ValueEventListener ValueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        ClassChapterDetails list = postSnapshot.getValue(ClassChapterDetails.class);
                        mClassChapterDetailsList.add(list);
                    }
                    dialog.dismiss();
                    mRecyclerViewCourseDetailsAdapter = new RecyclerViewCourseDetails(getContext(), mClassChapterDetailsList);
                    mRecyclerViewCourseCard.setAdapter(mRecyclerViewCourseDetailsAdapter);
                    mRecyclerViewCourseDetailsAdapter.setOnItemClickListener(new RecyclerViewCourseDetails.onItemCLickListenerRecycle() {
                        @Override
                        public void onItemClicked(int position) {
                            Log.d(TAG, "onItemClicked: clickeddd");
                            ClassChapterDetails clickedItem = mClassChapterDetailsList.get(position);
                            final ClassUserPickedChapter classUserPickedChapter = new ClassUserPickedChapter();
                            classUserPickedChapter.setPicUser(clickedItem.getmThumbnailofChapter());
                            classUserPickedChapter.setTitleUser(clickedItem.getmTitleOfChapter());
                            Log.d(TAG, "onItemClicked: geet");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            //it give a parent to a user picked chapters in ChapterPickedByUsers Parent with uid
                            String uid = user.getUid();
                            mDatabaseRef = FirebaseDatabase.getInstance().getReference("ChapterPickedByUsers").child(user.getUid());
                            // String key = user.getUid();
                            String key = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(key).setValue(classUserPickedChapter);
                            //  Toasty.error(getActivity(), "You successfully add "+clickedItem.getmTitleOfChapter()+".Tap on home button to access", Toasty.LENGTH_SHORT).show();
                            Snackbar snackbar = Snackbar.make(getView(), "You successfully added the chapter.\nTap on home button to access", Snackbar.LENGTH_LONG);
                            snackbar.show();




                        }
                    });
                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
Toasty.error(getContext(),databaseError.getMessage(),Toasty.LENGTH_SHORT).show();
            }

        };
        return ValueEventListener;

    }
}
