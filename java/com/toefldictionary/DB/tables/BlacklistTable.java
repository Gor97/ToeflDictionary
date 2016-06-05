package com.toefldictionary.DB.tables;

/**
 * Created by Gor on 25-Apr-16.
 */
public class BlacklistTable {
    public static final String TABLE_NAME = "blacklist";
    public static final String COLUMN_WORD_ID = "blacklist_word_id";
    public static final String COLUMN_BLACKLIST_WORD_ID = "blacklist_word";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + COLUMN_WORD_ID + " integer primary key, "
            + COLUMN_BLACKLIST_WORD_ID + " integer not null " + ");";
}