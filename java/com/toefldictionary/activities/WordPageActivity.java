package com.toefldictionary.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.toefldictionary.DB.TOEFL_DB;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.R;

import java.util.ArrayList;

public class WordPageActivity extends AppCompatActivity {

    private TextView wordText, transText;
    private ListView synonymsList, antonymsList;
    private ArrayAdapter<String> synAdapter;
    private ArrayAdapter<String> antAdapter;
    private TOEFL_DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_page);
        db = TOEFL_DB.getInstance(this);
        wordText = (TextView) findViewById(R.id.clickedWordText);
        transText = (TextView) findViewById(R.id.clickedWordTrans);
        synonymsList = (ListView) findViewById(R.id.synonymList);
        antonymsList = (ListView) findViewById(R.id.antonymList);
        Intent i = getIntent();
        Word w = (Word) i.getExtras().get("word");
        String name = w.getName();
        String trans = w.getTranslation();
        wordText.setText(name);
        transText.setText(trans);
        ArrayList<String> s = new ArrayList<>();
        ArrayList<String> a = new ArrayList<>();
        for(Word word : db.getAllSynonymsByWord(w.getId()))
        {
            s.add(word.getName());
        }
        for(Word word : db.getAllAntonymsByWord(w.getId()))
        {
            a.add(word.getName());
        }
        synAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, s);
        antAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, a);
        synonymsList.setAdapter(synAdapter);
        antonymsList.setAdapter(antAdapter);
    }

}
