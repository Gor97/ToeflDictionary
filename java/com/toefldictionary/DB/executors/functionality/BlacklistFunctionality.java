package com.toefldictionary.DB.executors.functionality;

import com.toefldictionary.DB.executors.objects.Word;

import java.util.ArrayList;

/**
 * Created by Gor on 27-Apr-16.
 */
public interface BlacklistFunctionality {
    void addBlacklistWord(Word w);
    void deleteBlacklistWord(int id);
    ArrayList<Word> getAllBlacklistWords();
}
