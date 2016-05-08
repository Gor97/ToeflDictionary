package com.toefldictionary.DB.executors.queries;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toefldictionary.DB.DBHelper;
import com.toefldictionary.DB.executors.functionality.WordSynonymFunctionality;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.DB.tables.WordSynonymTable;
import com.toefldictionary.DB.tables.WordsTable;

import java.util.ArrayList;

/**
 * Created by Gor on 27-Apr-16.
 */
public class WordSynonymQueries implements WordSynonymFunctionality {

    private String[] allColumns = {WordSynonymTable.COLUMN_WORD_ID, WordSynonymTable.COLUMN_SYNONYM_ID};
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public WordSynonymQueries(Context context) {
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
    public void addWordSynonym(int id1, int id2) {
        ContentValues values = new ContentValues();
        values.put(WordSynonymTable.COLUMN_WORD_ID, id1);
        values.put(WordSynonymTable.COLUMN_SYNONYM_ID, id2);
        database.insert(WordSynonymTable.TABLE_NAME, null, values);
    }

    @Override
    public void deleteWordSynonym(int id) {
        database.delete(WordSynonymTable.TABLE_NAME, WordSynonymTable.COLUMN_WORD_ID
                + " = " + id + " or " + WordSynonymTable.COLUMN_SYNONYM_ID + " = " + id, null);
    }

    @Override
    public ArrayList<Word> getAllSynonymsByWord(int id) {
        ArrayList<Word> synonyms = new ArrayList<>();
        Cursor cursor = database.query(WordSynonymTable.TABLE_NAME,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(0) == id) {
                int neededID = cursor.getInt(1);
                Word s = new Word();
                Cursor c = database.rawQuery("select * from " + WordsTable.TABLE_NAME + " inner join " + WordSynonymTable.TABLE_NAME + " on " + WordsTable.COLUMN_ID + " = " + neededID, null);
                c.moveToFirst();
                s.setId(c.getInt(0));
                s.setName(c.getString(1));
                s.setTranslation(c.getString(2));
                synonyms.add(s);
                c.close();
            }
            cursor.moveToNext();
        }
        cursor.close();
        return synonyms;
    }
}
