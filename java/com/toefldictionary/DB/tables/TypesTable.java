package com.toefldictionary.DB.tables;

/**
 * Created by Gor on 25-Apr-16.
 */
public class TypesTable {
    public static final String TABLE_NAME = "types";
    public static final String COLUMN_TYPE_ID = "type_id";
    public static final String COLUMN_TYPE_NAME = "type_name";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + COLUMN_TYPE_ID + " integer primary key, "
            + COLUMN_TYPE_NAME + " text not null " + ");";
}
