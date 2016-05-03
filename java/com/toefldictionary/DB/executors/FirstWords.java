package com.toefldictionary.DB.executors;

import android.content.Context;

import com.toefldictionary.DB.TOEFL_DB;
import com.toefldictionary.DB.executors.objects.Word;

/**
 * Created by Gor on 01-May-16.
 */
public class FirstWords {
    public static void addingFirstWords(Context context)
    {
        TOEFL_DB db = TOEFL_DB.getInstance(context);
        Word w = new Word("abandon", "թողնել");
        db.addWord(w);
        w = new Word("adversely", "բացասաբար");
        db.addWord(w);
        w = new Word("aggregate", "ժողովել");
        db.addWord(w);
        w = new Word("cultivation", "աճեցում");
        db.addWord(w);
        w = new Word("fertilize", "պարարտացնել");
        db.addWord(w);
        w = new Word("intensify", "ուժեղացնել");
        db.addWord(w);
        w = new Word("irrigation", "ոռոգում");
        db.addWord(w);
        w = new Word("obtain", "ստանալ");
        db.addWord(w);
        w = new Word("photosynthesis", "Ֆոտոսինթեզ");
        db.addWord(w);
        w = new Word("precipitation", "Երկրին հասնող ջուր");
        db.addWord(w);


    }
}
