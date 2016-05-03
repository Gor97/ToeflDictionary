package com.toefldictionary.DB.executors.functionality;


import com.toefldictionary.DB.executors.objects.Word;

import java.util.ArrayList;

/**
 * Created by Gor on 27-Apr-16.
 */
public interface WordTypeFunctionality {
    void addWordType(int id1, int id2);
    void deleteWordTypeByWord(int id);
    void deleteWordTypeByType(int id);
    ArrayList<Word> getAllWordByType(int id);
}
