package com.toefldictionary.DB.executors.queries;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toefldictionary.DB.DBHelper;
import com.toefldictionary.DB.executors.functionality.BlacklistFunctionality;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.DB.tables.BlacklistTable;
import com.toefldictionary.DB.tables.WordsTable;

import java.util.ArrayList;

/**
 * Created by Gor on 27-Apr-16.
 */
public class BlacklistQueries implements BlacklistFunctionality {

    private String[] allColumns = {BlacklistTable.COLUMN_WORD_ID, BlacklistTable.COLUMN_BLACKLIST_WORD_ID};
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
    public void addBlacklistWord(int id) {
        boolean alreadyTaken = false;
        Cursor c = database.query(BlacklistTable.TABLE_NAME,
                allColumns, null, null, null, null, null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            if(c.getInt(1) == id) {
                alreadyTaken = true;
            }
        }
        c.close();
        if(!alreadyTaken) {
            ContentValues values = new ContentValues();
            values.put(BlacklistTable.COLUMN_BLACKLIST_WORD_ID, id);
            database.insert(BlacklistTable.TABLE_NAME, null, values);
            long insertId = database.insert(BlacklistTable.TABLE_NAME, null, values);
            Cursor cursor = database.query(BlacklistTable.TABLE_NAME, allColumns, BlacklistTable.COLUMN_WORD_ID + " = " + insertId, null, null, null, null);
            cursor.moveToFirst();
            cursorToBlacklistWord(cursor);
            cursor.close();
        }
    }

    @Override
    public void deleteBlacklistWord(int id) {
        database.delete(BlacklistTable.TABLE_NAME, BlacklistTable.COLUMN_WORD_ID
                + " = " + id, null);
    }
    private Word cursorToBlacklistWord(Cursor cursor) {
        Word w = new Word();
        w.setId(cursor.getInt(0));
        w.setName(cursor.getString(1));
        w.setTranslation(cursor.getString(2));
        return w;
    }
    @Override
    public ArrayList<Word> getAllBlacklistWords() {
        ArrayList<Word> words = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + WordsTable.TABLE_NAME + " where " + WordsTable.COLUMN_ID + " = " + BlacklistTable.COLUMN_BLACKLIST_WORD_ID, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Word w = cursorToBlacklistWord(cursor);
            words.add(w);
            cursor.moveToNext();
        }
        cursor.close();
        return words;
    }
}
