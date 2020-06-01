package com.shubhcode.nmcourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


import es.dmoral.toasty.Toasty;

public class ActivityForVideos extends AppCompatActivity {
    private static final String TAG = "ActivityForVideos";
    VideoView videoView;
    ListView listView;
    private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("url");
    String name[] = {"shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham", "shubham"};
ImageView cross;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: openingvideo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_videos);
        videoView = findViewById(R.id.videoview);
       String videopath = "android.resource://" + getPackageName() + "/" + R.raw.episode;

        Uri uri = Uri.parse(videopath);
      videoView.setVideoURI(uri);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        mc.setAnchorView(videoView);
        Toolbar toolbar=findViewById(R.id.toolbarVideos);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

cross=findViewById(R.id.cross);
cross.setOnClickListener(crossClickListener());

        listView = findViewById(R.id.listvew);
        final ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("shubham");
        arrayList.add("shubham");
        arrayList.add("shubham");
        arrayList.add("shubham");
        arrayList.add("shubham");
        arrayList.add("shubham");
        arrayList.add("shubham");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String message=dataSnapshot.getValue(String.class);
//                Uri uri=Uri.parse(message);
//                videoView.setVideoURI(uri);
//                videoView.start();
//                MediaController mc = new MediaController(getApplicationContext());
//                videoView.setMediaController(mc);
//                mc.setAnchorView(videoView);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toasty.warning(ActivityForVideos.this, arrayList.get(i), Toasty.LENGTH_SHORT).show();
            }
        });

    }

    private View.OnClickListener crossClickListener() {
        View.OnClickListener onClickListener=       new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),NavigationActivity.class);
            startActivity(intent);
            finish();
            }
        };
        return onClickListener;
    }
}

