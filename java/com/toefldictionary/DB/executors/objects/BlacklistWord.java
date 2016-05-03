package com.toefldictionary.DB.executors.objects;


/**
 * Created by Gor on 27-Apr-16.
 */
public class BlacklistWord {
    private int id;
    private int counter;
    private Word word;

    public BlacklistWord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
