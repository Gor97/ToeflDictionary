package com.toefldictionary.DB.tables;

/**
 * Created by Gor on 25-Apr-16.
 */
public class DescriptionsTable {
    public static final String TABLE_NAME = "descriptions";
    public static final String COLUMN_ID = "description_id";
    public static final String COLUMN_TEXT = "text";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + COLUMN_ID + " integer primary key, "
            + COLUMN_TEXT + " text " + ");";
}
