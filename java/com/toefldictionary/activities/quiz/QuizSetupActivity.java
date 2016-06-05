package com.toefldictionary.activities.quiz;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.toefldictionary.DB.TOEFL_DB;
import com.toefldictionary.R;

import java.util.ArrayList;
import java.util.List;

public class QuizSetupActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private TOEFL_DB db;
    private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_quiz);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        db = TOEFL_DB.getInstance(this);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<ExpandableListAdapter.Item> data = new ArrayList<>();

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "All Words"));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "My Words"));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Nature"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Science"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Mind and Body"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Society"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Money"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;


        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Government and Justice"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Relationship"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Culture"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Lesson "+ (index-1) + " " + db.getAllTypes().get(index).getName()));
        index++;

        recyclerview.setAdapter(new ExpandableListAdapter(this, data));
    }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
