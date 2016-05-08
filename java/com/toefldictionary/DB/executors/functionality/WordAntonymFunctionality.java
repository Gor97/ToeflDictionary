package com.toefldictionary.DB.executors.functionality;

import com.toefldictionary.DB.executors.objects.Word;

import java.util.ArrayList;

/**
 * Created by Gor on 02-May-16.
 */
public interface WordAntonymFunctionality {
    void addWordAntonym(int id1, int id2);
    void deleteWordAntonym(int id);
    ArrayList<Word> getAllAntonymsByWord(int id);
}
