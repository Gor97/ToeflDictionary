package com.toefldictionary.activities.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.toefldictionary.R;

public class QuizResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        Intent i = getIntent();
        Log.e("TOEFL", i.getExtras().get("rightQuestionCount") + "");
        Log.e("TOEFL", i.getExtras().get("allQuestionCount") + "");
        Log.e("TOEFL", i.getExtras().get("rightAnswers").toString());
        Log.e("TOEFL", i.getExtras().get("wrongAnswers").toString());

    }
}
