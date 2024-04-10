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
                       FragmentTransaction ft1 =getSupportFragmentManager().beginTransaction();
                       ft1.replace(R.id.content,fragment2,"");
                       ft1.commit();
                       return true;
                   } else if (menuItem.getItemId() == R.id.nav_users) {
                       UsersFragment fragment3 = new UsersFragment();
                       FragmentTransaction ft1 =getSupportFragmentManager().beginTransaction();
                       ft1.replace(R.id.content,fragment3,"");
                       ft1.commit();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_logout)
        {
            firebaseAuth.signOut();
            checkUserStatus();
        }
        return super.onOptionsItemSelected(item);
    }
}