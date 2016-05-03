package com.toefldictionary.DB.tables;

/**
 * Created by Gor on 25-Apr-16.
 */
public class WordTypeTable {
    public static final String TABLE_NAME = "word_type";
    public static final String COLUMN_WORD_ID = "word_id";
    public static final String COLUMN_TYPE_ID = "type_id";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + COLUMN_WORD_ID + " integer, "
            + COLUMN_TYPE_ID + " integer " + ");";
}
