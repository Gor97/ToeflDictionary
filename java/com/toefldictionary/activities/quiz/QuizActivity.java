package com.toefldictionary.activities.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.toefldictionary.DB.TOEFL_DB;
import com.toefldictionary.DB.executors.objects.Question_Answer;
import com.toefldictionary.DB.executors.objects.Type;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class QuizActivity extends AppCompatActivity {
    private Button answer1, answer2, answer3, answer4;
    private TextView question;
    private ArrayList<Question_Answer> pairs;
    private ArrayList<Button> options;
    private TextView counterText;
    private int counter = 0;
    private int index = 1;
    private int nextQuestion = -1;
    private int correctAnswer;
    private int questionsCount;
    private Type t;
    private TOEFL_DB db;
    private ArrayList<Word> rightAnswers, wrongAnswers, words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTitle("QUIZ");
        Intent intent = getIntent();
        t = (Type) intent.getExtras().get("type");
        questionsCount = (int) intent.getExtras().get("count");
        question = (TextView) findViewById(R.id.questionText);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        rightAnswers = new ArrayList<>();
        wrongAnswers = new ArrayList<>();
        counterText = (TextView) findViewById(R.id.counterText);
        assert counterText != null;
        counterText.setText(index + "/" + questionsCount);
        pairs = new ArrayList<>();
        options = new ArrayList<>();
        options.add(answer1);
        options.add(answer2);
        options.add(answer3);
        options.add(answer4);
        db = TOEFL_DB.getInstance(this);
        words = db.getAllWordByType(t.getId());
        Collections.shuffle(words);
        Collections.shuffle(words);
        for (int i = 0; i < questionsCount; i++) {
            Question_Answer qa = new Question_Answer();
            qa.setQuestion(words.get(i).getName());
            qa.setAnswer(words.get(i).getTranslation());
            pairs.add(qa);
        }
        Collections.shuffle(pairs);
        Collections.shuffle(pairs);
        goToNextQuestion();
    }

    public void choose(View v) {
        switch (v.getId()) {
            case R.id.answer1:
                if (correctAnswer == 0) {
                    rightAnswers.add(new Word(pairs.get(nextQuestion).getQuestion(), pairs.get(nextQuestion).getAnswer()));
                    counter++;
                }
                else {
                    wrongAnswers.add(new Word(pairs.get(nextQuestion).getQuestion(), pairs.get(nextQuestion).getAnswer()));
                }
                goToNextQuestion();
                break;
            case R.id.answer2:
                if (correctAnswer == 1) {
                    rightAnswers.add(new Word(pairs.get(nextQuestion).getQuestion(), pairs.get(nextQuestion).getAnswer()));
                    counter++;
                }
                else {
                    wrongAnswers.add(new Word(pairs.get(nextQuestion).getQuestion(), pairs.get(nextQuestion).getAnswer()));
                }
                goToNextQuestion();
                break;
            case R.id.answer3:
                if (correctAnswer == 2) {
                    rightAnswers.add(new Word(pairs.get(nextQuestion).getQuestion(), pairs.get(nextQuestion).getAnswer()));
                    counter++;
                }
                else {
                    wrongAnswers.add(new Word(pairs.get(nextQuestion).getQuestion(), pairs.get(nextQuestion).getAnswer()));
                }
                goToNextQuestion();
                break;
            case R.id.answer4:
                if (correctAnswer == 3) {
                    rightAnswers.add(new Word(pairs.get(nextQuestion).getQuestion(), pairs.get(nextQuestion).getAnswer()));
                    counter++;
                }
                else {
                    wrongAnswers.add(new Word(pairs.get(nextQuestion).getQuestion(), pairs.get(nextQuestion).getAnswer()));
                }
                goToNextQuestion();
                break;
        }
    }

    public void goToNextQuestion() {
        if(nextQuestion == questionsCount - 1)
        {
            Toast.makeText(QuizActivity.this,  "Quiz ended", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(QuizActivity.this, QuizResultActivity.class);
            i.putExtra("rightQuestionCount", counter);
            i.putExtra("allQuestionCount", questionsCount);
            i.putExtra("rightAnswers", rightAnswers);
            i.putExtra("wrongAnswers", wrongAnswers);
            startActivity(i);
        }
        else {
            nextQuestion++;
            String s = pairs.get(nextQuestion).getQuestion();
            question.setText(s);
            Random r = new Random();
            correctAnswer = r.nextInt(4);
            options.get(correctAnswer).setText(pairs.get(nextQuestion).getAnswer() + "");
            ArrayList<Integer> numbers = new ArrayList<>();
            for(int i = 0; i < 10; i++)
            {
                if(i != correctAnswer)
                {
                    numbers.add(i);
                }
            }
            Collections.shuffle(numbers);
            Collections.shuffle(numbers);
            for (int i = 0; i < 4; i++) {
                if (i != correctAnswer) {
                    options.get(i).setText(db.getAllWordByType(t.getId()).get(numbers.get(i)).getTranslation());
                } else {
                    continue;
                }
            }
        }
        counterText.setText(index + "/" + questionsCount);
        index++;
    }
}
