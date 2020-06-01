package com.shubhcode.nmcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide the bar from this screen
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        /** Making this activity, full screen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
//getSupportActionBar().hide();
//
        Thread splash =new Thread(){
            public void run(){

                try{

                    sleep(3000);;
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                 startActivity(intent);
                 finish();
                }catch (Exception e){

                }
            }
        };
        splash.start();
    }
}
