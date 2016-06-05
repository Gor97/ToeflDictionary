package com.toefldictionary.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.toefldictionary.activities.quiz.QuizSetupActivity;
import com.toefldictionary.tools.CardViewAdapter;
import com.toefldictionary.DB.TOEFL_DB;
import com.toefldictionary.DB.executors.FirstWords;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.R;
import com.toefldictionary.tools.service.Receiver;

import java.util.ArrayList;


public class StartingPageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ArrayList<Word> words;
    private TOEFL_DB db;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private boolean loading = true;
    private TextView translation;
    private EditText translate;
    private String translatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);
        setTitle("TOEFL 400 Words");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = TOEFL_DB.getInstance(this);

        if (db.getAllWords() == null || db.getAllWords().size() == 0) {
            FirstWords.addingFirstWords(getApplicationContext());
        }

        translation = (TextView) findViewById(R.id.translationText);
        translate = (EditText) findViewById(R.id.translateEdit);
        words = db.getAllWordByType(1);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CardViewAdapter(words, this);
        recyclerView.setAdapter(adapter);
        final LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int pastVisiblesItems, visibleItemCount, totalItemCount;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                        }
                    }
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartingPageActivity.this, AddWordPageActivity.class);
                startActivity(i);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.nav_mywords) {
            Intent i = new Intent(StartingPageActivity.this, MyWordsPageActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_quiz) {
            Intent i = new Intent(StartingPageActivity.this, QuizSetupActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_blacklist) {

        } else if (id == R.id.nav_settings) {
            Intent i = new Intent(StartingPageActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            Log.e("TAG", TOEFL_DB.getInstance(this).getAllSynonymsByWord(1).toString());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
