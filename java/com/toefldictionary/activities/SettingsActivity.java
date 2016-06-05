package com.toefldictionary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.api.translate.Language;
import com.toefldictionary.R;
import com.toefldictionary.tools.Translate;

public class SettingsActivity extends AppCompatActivity {
    private EditText translateEdit;
    private TextView translationText;
    private String translatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        translateEdit = (EditText) findViewById(R.id.translateEdit);
        translationText = (TextView) findViewById(R.id.translationText);

        Translate.setKey("trnsl.1.1.20160521T091147Z.41183c19259abd89.48573491ee4d6489543d0b128fb4b0c65935aa04");
        Button b = (Button) findViewById(R.id.button);
        assert b != null;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    public void run() {
                        try {
                            translatedText = Translate.execute(translateEdit.getText().toString(), Language.ENGLISH, Language.ARMENIAN);
                            translationText.setText(translatedText);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });
    }

    @Override
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
