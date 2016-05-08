package com.toefldictionary.DB.tables;

/**
 * Created by Gor on 02-May-16.
 */
public class WordAntonymTable {
    public static final String TABLE_NAME = "word_antonyms";
    public static final String COLUMN_WORD_ID = "antonym1_id";
    public static final String COLUMN_ANTONYM_ID = "antonym2_id";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + COLUMN_WORD_ID + " integer, "
            + COLUMN_ANTONYM_ID + " integer " + ");";
}
