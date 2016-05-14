package com.toefldictionary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.toefldictionary.R;

public class SettingsActivity extends AppCompatActivity {
    public static boolean answerSwitchON = false;
    private Switch answerSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        answerSwitch = (Switch) findViewById(R.id.answerSwitch);
        answerSwitch.setChecked(answerSwitchON);
        answerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!answerSwitchON) {
                    answerSwitchON = true;
                } else {
                    answerSwitchON = false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
