package com.toefldictionary.DB.executors.functionality;

import com.toefldictionary.DB.executors.objects.Description;

import java.util.ArrayList;

/**
 * Created by Gor on 01-May-16.
 */
public interface WordDescriptionFunctionality {
    void addWordDes(int id1, int id2);
    void deleteWordDesByWord(int id);
    void deleteWordDesByDes(int id);
    ArrayList<Description> getAllDesByWord(int id);
}
