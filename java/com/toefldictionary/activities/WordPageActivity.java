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

public class WordPageActivity extends AppCompatActivity {

    private TextView wordText, transText;
    private ListView synonymsList, antonymsList;
    private ArrayAdapter<Word> synAdapter;
    private ArrayAdapter antAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_page);
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
        synAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, TOEFL_DB.getInstance(this).getAllSynonymsByWord(w.getId()));
        antAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, TOEFL_DB.getInstance(this).getAllAntonymsByWord(w.getId()));
        synonymsList.setAdapter(synAdapter);
        antonymsList.setAdapter(antAdapter);
    }

}
