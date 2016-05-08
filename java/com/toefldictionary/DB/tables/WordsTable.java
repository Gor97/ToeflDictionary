package com.toefldictionary.DB.tables;

/**
 * Created by Gor on 25-Apr-16.
 */
public class WordsTable {
    public static final String TABLE_NAME = "words";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_TRANSLATION = "translation";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + COLUMN_ID + " integer primary key, "
            + COLUMN_WORD + " text not null, "
            + COLUMN_TRANSLATION + " text" + ");";
}