package com.toefldictionary.DB.executors.objects;


/**
 * Created by Gor on 27-Apr-16.
 */
public class Description {
    private int id;
    private String text;

    public Description() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Description{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
