package com.toefldictionary.DB.tables;

/**
 * Created by Gor on 25-Apr-16.
 */
public class WordSynonymTable {
    public static final String TABLE_NAME = "word_synonyms";
    public static final String COLUMN_WORD_ID = "synonym1_id";
    public static final String COLUMN_SYNONYM_ID = "synonym2_id";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + COLUMN_WORD_ID + " integer, "
            + COLUMN_SYNONYM_ID + " integer " + ");";
}
