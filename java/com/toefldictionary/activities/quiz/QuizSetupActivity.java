package com.toefldictionary.activities.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.toefldictionary.DB.TOEFL_DB;
import com.toefldictionary.DB.executors.objects.Type;
import com.toefldictionary.R;

import java.util.ArrayList;

public class QuizSetupActivity extends AppCompatActivity {

    private Spinner typeSpinner;
    private Spinner countSpinner;
    private Button start;
    private int count;
    private Type type;
    private TOEFL_DB db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_setup_page);
        typeSpinner = (Spinner) findViewById(R.id.spinnerType);
        countSpinner = (Spinner) findViewById(R.id.spinnerCount);
        start = (Button) findViewById(R.id.startQuizButton);
        db = TOEFL_DB.getInstance(this);
        final ArrayList<Type> types = db.getAllTypes();
        ArrayList<String> typeName = new ArrayList<>();
        for(int lessonIndex = 1; lessonIndex <= types.size() - 1; lessonIndex++) {
            typeName.add("Lesson " +lessonIndex + " " + types.get(lessonIndex).getName());
        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeName);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(spinnerArrayAdapter);

        ArrayList<Integer> counts = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            counts.add(i);
        }
        ArrayAdapter<Integer> spinnerCountArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, counts);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countSpinner.setAdapter(spinnerCountArrayAdapter);


        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                type = types.get(typeSpinner.getSelectedItemPosition()+1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        countSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                count = (int) countSpinner.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizSetupActivity.this, QuizActivity.class);
                i.putExtra("type", type);
                i.putExtra("count", count);
                startActivity(i);
            }
        });
    }
}
