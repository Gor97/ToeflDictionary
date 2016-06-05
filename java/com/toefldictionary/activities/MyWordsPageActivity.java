package com.toefldictionary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.toefldictionary.DB.TOEFL_DB;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.R;
import com.toefldictionary.tools.CardViewAdapter;
import com.toefldictionary.tools.CardViewAdapter2;
import com.toefldictionary.tools.TTSManager;

import java.util.ArrayList;

public class MyWordsPageActivity extends AppCompatActivity {

    private ArrayList<Word> words;
    private TOEFL_DB db;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private boolean loading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_words_page);

        setTitle("My Words");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        db = TOEFL_DB.getInstance(this);
        words = db.getAllWordByType(42);
        recyclerView = (RecyclerView) findViewById(R.id.my_words_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CardViewAdapter2(words, this);
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
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
