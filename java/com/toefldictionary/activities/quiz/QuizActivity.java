package com.toefldictionary.activities.quiz;

import android.content.Intent;
import android.graphics.Color;
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
import java.util.Random;

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
    private ArrayList<Integer> wordIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTitle("QUIZ");
        Intent intent = getIntent();
        db = TOEFL_DB.getInstance(this);
        int a = (int) intent.getExtras().get("type");
        t = db.getAllTypes().get(a);
        questionsCount = 5;
        words = db.getAllWordByType(t.getId());
        rightAnswers = new ArrayList<>();
        wrongAnswers = new ArrayList<>();
        question = (TextView) findViewById(R.id.questionText);
        counterText = (TextView)findViewById(R.id.counterText);
        answer1 = (Button)findViewById(R.id.answer1);
        answer2 = (Button)findViewById(R.id.answer2);
        answer3 = (Button)findViewById(R.id.answer3);
        answer4 = (Button)findViewById(R.id.answer4);

        assert counterText != null;
        counterText.setText(index + "/" + questionsCount);
        pairs = new ArrayList<>();
        options = new ArrayList<>();
        options.add(answer1);
        options.add(answer2);
        options.add(answer3);
        options.add(answer4);
        wordIndex = new ArrayList<>();
        for(int i = 0 ; i < words.size(); i++)
        {
            wordIndex.add(words.get(i).getId());
        }
        Collections.shuffle(wordIndex);
        Collections.shuffle(wordIndex);
        for (int i = 0; i < questionsCount; i++) {
            Question_Answer qa = new Question_Answer();
            qa.setQuestion(db.getAllWords().get(wordIndex.get(i)).getName());
            qa.setAnswer(db.getAllWords().get(wordIndex.get(i)).getTranslation());
            pairs.add(qa);
        }
        Collections.shuffle(pairs);
        Collections.shuffle(pairs);
        goToNextQuestion();
    }

    public void choose(View v) throws InterruptedException {
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
            finish();
        }
        else {
            nextQuestion++;
            String s = pairs.get(nextQuestion).getQuestion();
            question.setText(s);
            Random r = new Random();
            correctAnswer = r.nextInt(4);
            options.get(correctAnswer).setText(pairs.get(nextQuestion).getAnswer() + "");
            ArrayList<Integer> numbers = new ArrayList<>();
            for(int i = 0; i < words.size(); i++)
            {
                numbers.add(i);
            }
            Collections.shuffle(numbers);
            Collections.shuffle(numbers);
            for (int i = 0; i < 4; i++) {
                if (i != correctAnswer) {
                    options.get(i).setText(words.get(numbers.get(i)).getTranslation());
                } else {
                    continue;
                }
            }
        }
        counterText.setText(index + "/" + questionsCount);
        index++;
    }
}
