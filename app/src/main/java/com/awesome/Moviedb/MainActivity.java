package com.awesome.Moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity  extends AppCompatActivity  {
    TabLayout tabLayout;
    Toolbar toolbar;
    TabItem poptab, ratetab;
    PageAdapter pageAdapter;
    ViewPager viewPager;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("MovieDB");
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tablayout);
        poptab = findViewById(R.id.poptab);
        mAuth = FirebaseAuth.getInstance();
        ratetab = findViewById(R.id.ratetab);
        viewPager = findViewById(R.id.viewpager);



        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.signout:
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this,Login.class));
                return true;

            case R.id.search_button:
                startActivity(new Intent(MainActivity.this,Search.class));

            default:
                return super.onOptionsItemSelected(item);
        }
    }





}



