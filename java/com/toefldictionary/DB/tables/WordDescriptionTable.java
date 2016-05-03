package com.toefldictionary.DB.tables;

/**
 * Created by Gor on 25-Apr-16.
 */
public class WordDescriptionTable {
    public static final String TABLE_NAME = "word_description";
    public static final String COLUMN_WORD_ID = "word_ID";
    public static final String COLUMN_DESCRIPTION_ID = "description_ID";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + COLUMN_WORD_ID + " integer, "
            + COLUMN_DESCRIPTION_ID + " integer " + ");";
}
