package com.toefldictionary.DB.executors.objects;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Gor on 26-Apr-16.
 */
public class Word implements Serializable {
    private int id;
    private String name;
    private String translation;
    private ArrayList<String> synonyms;
    private ArrayList<String> antonyms;

    public Word(){}
    public Word(String name, String translation) {
        this.name = name;
        this.translation = translation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public ArrayList<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(ArrayList<String> synonyms) {
        this.synonyms = synonyms;
    }

    public ArrayList<String> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(ArrayList<String> antonyms) {
        this.antonyms = antonyms;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }
}
