package com.toefldictionary.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.toefldictionary.DB.tables.WordAntonymTable;
import com.toefldictionary.DB.tables.BlacklistTable;
import com.toefldictionary.DB.tables.WordSynonymTable;
import com.toefldictionary.DB.tables.TypesTable;
import com.toefldictionary.DB.tables.WordTypeTable;
import com.toefldictionary.DB.tables.WordsTable;

/**
 * Created by Gor on 25-Apr-16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "my_toefl_words.db";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WordsTable.CREATE_TABLE);
        db.execSQL(WordSynonymTable.CREATE_TABLE);
        db.execSQL(WordAntonymTable.CREATE_TABLE);
        db.execSQL(TypesTable.CREATE_TABLE);
        db.execSQL(BlacklistTable.CREATE_TABLE);
        db.execSQL(WordTypeTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + WordsTable.TABLE_NAME);
        db.execSQL("drop table if exists " + WordSynonymTable.TABLE_NAME);
        db.execSQL("drop table if exists " + WordAntonymTable.TABLE_NAME);
        db.execSQL("drop table if exists " + TypesTable.TABLE_NAME);
        db.execSQL("drop table if exists " + BlacklistTable.TABLE_NAME);
        db.execSQL("drop table if exists " + WordTypeTable.TABLE_NAME);
    }
}
