package com.example.communityhero;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.parse.ParseUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    PostAdapter adapter;
    List<Post> postList;
    CreatePostFragment createPostFragment;
    ViewGroup root;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createPostFragment = new CreatePostFragment();
        root = (ViewGroup) getWindow().getDecorView().getRootView();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Drawer
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.bringToFront();
        //Drawer item selection
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);

                    case R.id.tasks:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TasksFragment()).commit();
                        break;

                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        //To open and close drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Recycler view to contain the cards (posts)
        postList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String pattern = "dd MMMM yyyy";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("us", "US"));
        String date = simpleDateFormat.format(new Date());

        postList.add(
                new Post(
                        1,
                        "Clearing neighborhood garbabge",
                        "There's a lot of garbage left unattended these days. So I thought we could clean it up",
                        "Contributors: 4",
                        date));

        postList.add(
                new Post(
                        1,
                        "Clearing neighborhood garbabge",
                        "There's a lot of garbage left unattended these days. So I thought we could clean it up",
                        "Contributors: 4",
                        date));
        postList.add(
                new Post(
                        1,
                        "Clearing neighborhood garbabge",
                        "There's a lot of garbage left unattended these days. So I thought we could clean it up",
                        "Contributors: 4",
                        date));
        postList.add(
                new Post(
                        1,
                        "Clearing neighborhood garbabge",
                        "There's a lot of garbage left unattended these days. So I thought we could clean it up",
                        "Contributors: 4",
                        date));

        postList.add(
                new Post(
                        1,
                        "Clearing neighborhood garbabge",
                        "There's a lot of garbage left unattended these days. So I thought we could clean it up",
                        "Contributors: 4",
                        date));

        postList.add(
                new Post(
                        1,
                        "Clearing neighborhood garbabge",
                        "There's a lot of garbage left unattended these days. So I thought we could clean it up",
                        "Contributors: 4",
                        date));

        adapter = new PostAdapter(this, postList);
        recyclerView.setAdapter(adapter);
    }

    //Drawer on back press
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    // To bookmark a post
    public void save(View view) {
        Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show();
    }

    // To create a post
    public void add(View view) {
        applyDim(root, 0.1f);
        fab = findViewById(R.id.fab);
        fab.hide();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, createPostFragment).commit();
    }

    public void create(View view) {
        clearDim(root);
        getSupportFragmentManager().beginTransaction().remove(createPostFragment).commit();
        fab.show();
    }

    public static void applyDim(@NonNull ViewGroup parent, float dimAmount){
        Drawable dim = new ColorDrawable(Color.BLACK);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * dimAmount));

        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }

    public static void clearDim(@NonNull ViewGroup parent) {
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }

    public void chooseLocation(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
