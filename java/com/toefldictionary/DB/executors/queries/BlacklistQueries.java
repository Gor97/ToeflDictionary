package com.toefldictionary.DB.executors.queries;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.toefldictionary.DB.DBHelper;
import com.toefldictionary.DB.executors.functionality.BlacklistFunctionality;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.DB.tables.BlacklistTable;

import java.util.ArrayList;

/**
 * Created by Gor on 27-Apr-16.
 */
public class BlacklistQueries implements BlacklistFunctionality {

    private String[] allColumns = {BlacklistTable.COLUMN_WORD_ID, BlacklistTable.COLUMN_BLACKLIST_WORD, BlacklistTable.COLUMN_BLACKLIST_WORD_COUNTER};
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public BlacklistQueries(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    @Override
    public void addBlacklistWord(Word w) {

    }

    @Override
    public void deleteBlacklistWord(Word w) {

    }

    @Override
    public ArrayList<Word> getAllBlacklistWords() {
        return null;
    }
}
