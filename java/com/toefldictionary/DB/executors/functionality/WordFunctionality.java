package com.toefldictionary.DB.executors.functionality;

import com.toefldictionary.DB.executors.objects.Word;

import java.util.ArrayList;

/**
 * Created by Gor on 25-Apr-16.
 */
public interface WordFunctionality {
    Word addWord(Word word);
    Word editWord(Word word);
    void deleteWord(int id);
    ArrayList<Word> getAllWords();
}