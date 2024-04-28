package com.example.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);



        firebaseAuth = FirebaseAuth.getInstance();

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        HomeFragment fragment1 = new HomeFragment();
        FragmentTransaction ft1 =getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.content,fragment1,"");
        ft1.commit();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
   private  BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
           new BottomNavigationView.OnNavigationItemSelectedListener() {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                   if (menuItem.getItemId() == R.id.nav_home) {
                       HomeFragment fragment1 = new HomeFragment();
                       FragmentTransaction ft1 =getSupportFragmentManager().beginTransaction();
                       ft1.replace(R.id.content,fragment1,"");
                       ft1.commit();
                       return true;
                   } else if (menuItem.getItemId() == R.id.nav_profile) {
                       ProfileFragment fragment2 = new ProfileFragment();
                       FragmentTransaction ft2 =getSupportFragmentManager().beginTransaction();
                       ft2.replace(R.id.content,fragment2,"");
                       ft2.commit();
                       return true;
                   } else if (menuItem.getItemId() == R.id.nav_users) {
                       UsersFragment fragment3 = new UsersFragment();
                       FragmentTransaction ft3 =getSupportFragmentManager().beginTransaction();
                       ft3.replace(R.id.content,fragment3,"");
                       ft3.commit();
                       return true;
                   }else if (menuItem.getItemId() == R.id.nav_chat) {
                       ChatLIstFragment fragment4 = new ChatLIstFragment();
                       FragmentTransaction ft4 =getSupportFragmentManager().beginTransaction();
                       ft4.replace(R.id.content,fragment4,"");
                       ft4.commit();
                       return true;
                   }
                   return false;
               }
           };
    private void checkUserStatus()
    {
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null)
        {



        }
        else
        {
            startActivity(new Intent(DashboardActivity.this,MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        checkUserStatus();
        super.onStart();
    }


}