package com.shubhcode.nmcourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StoreFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.store:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StoreFragment()).commit();
                        break;
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                        break;
                    case R.id.me:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MeFragment()).commit();
                        break;
                }
                //it means we want to show selected item
                return true;
            }
        });
    }

    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


}
