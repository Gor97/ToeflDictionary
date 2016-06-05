package com.toefldictionary.activities.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.toefldictionary.DB.TOEFL_DB;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.R;

import java.util.ArrayList;

public class QuizResultActivity extends AppCompatActivity {

    private ListView rightList, wrongList;
    private ArrayAdapter<String> wrongAdapter, rightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        Intent i = getIntent();
        rightList = (ListView) findViewById(R.id.rightList);
        wrongList = (ListView) findViewById(R.id.wrongList);
        ArrayList<Word> words = (ArrayList<Word>) i.getExtras().get("rightAnswers");
        ArrayList<Word> wrongWords = (ArrayList<Word>) i.getExtras().get("wrongAnswers");
        ArrayList<String> s1 = new ArrayList<>();
        ArrayList<String> s2 = new ArrayList<>();

        for(int it = 0; it < words.size(); it++)
        {
            s1.add(words.get(it).getName() + " - " + words.get(it).getTranslation());
        }
        for(int it = 0; it < wrongWords.size(); it++)
        {
            s2.add(wrongWords.get(it).getName() + " - " + wrongWords.get(it).getTranslation());
        }
        rightAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, s1);
        wrongAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, s2);
        rightList.setAdapter(rightAdapter);
        wrongList.setAdapter(wrongAdapter);
    }
}
