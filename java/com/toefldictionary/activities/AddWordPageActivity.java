package com.toefldictionary.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.toefldictionary.DB.TOEFL_DB;
import com.toefldictionary.DB.executors.objects.Description;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.R;

import java.util.ArrayList;
import java.util.Arrays;

public class AddWordPageActivity extends AppCompatActivity {

    private EditText word, trans, antonyms, synonyms, description;
    private String[] antons, synons;
    private Button save;
    private TOEFL_DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word_page);
        word = (EditText) findViewById(R.id.newWordEdit);
        trans = (EditText) findViewById(R.id.newWordTransEdit);
        antonyms = (EditText) findViewById(R.id.newWordAntonEdit);
        synonyms = (EditText) findViewById(R.id.newWordSynonEdit);
        description = (EditText) findViewById(R.id.newWordDescription);
        save = (Button) findViewById(R.id.saveButton);
        db = TOEFL_DB.getInstance(this);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (word.getText().toString().equals("")) {
                    word.setError("Enter the Word");
                } else if (trans.getText().toString().equals("")) {
                    trans.setError("Enter Translation");
                } else if (description.getText().toString().equals("")) {
                    description.setError("Enter Description");
                } else {
                    if(! synonyms.getText().toString().equals(""))
                    {
                        synons = synonyms.getText().toString().split(" ");
                    }
                    if(! antonyms.getText().toString().equals(""))
                    {
                        antons = antonyms.getText().toString().split(" ");
                    }
                    Word w = new Word(word.getText().toString(), trans.getText().toString());
                    Description d = new Description();
                    d.setText(description.getText().toString());
                    w.setSynonyms(new ArrayList<>(Arrays.asList(synons)));
                    w.setAntonyms(new ArrayList<>(Arrays.asList(antons)));
                    w = db.addWord(w);
                    db.addWordType(w.getId(), 1);
                    for(String s:synons)
                    {
                        Word syn = new Word();
                        syn.setName(s);
                        syn.setTranslation(trans.getText().toString());
                        syn = db.addWord(syn);
                        db.addWordSynonym(w.getId(), syn.getId());
                    }
                    for(String a:antons)
                    {
                        Word ant = new Word();
                        ant.setName(a);
                        ant = db.addWord(ant);
                        db.addWordAntonym(w.getId(), ant.getId());
                    }
                    Intent i = new Intent(AddWordPageActivity.this, StartingPageActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
