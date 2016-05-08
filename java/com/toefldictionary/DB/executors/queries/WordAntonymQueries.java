package com.toefldictionary.DB.executors.queries;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toefldictionary.DB.DBHelper;
import com.toefldictionary.DB.executors.functionality.WordAntonymFunctionality;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.DB.tables.WordAntonymTable;
import com.toefldictionary.DB.tables.WordsTable;

import java.util.ArrayList;

/**
 * Created by Gor on 27-Apr-16.
 */
public class WordAntonymQueries implements WordAntonymFunctionality {
    private String[] allColumns = {WordAntonymTable.COLUMN_WORD_ID, WordAntonymTable.COLUMN_ANTONYM_ID};
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public WordAntonymQueries(Context context) {
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
    public void addWordAntonym(int id1, int id2) {
        ContentValues values = new ContentValues();
        values.put(WordAntonymTable.COLUMN_WORD_ID, id1);
        values.put(WordAntonymTable.COLUMN_ANTONYM_ID, id2);
        database.insert(WordAntonymTable.TABLE_NAME, null, values);
    }

    @Override
    public void deleteWordAntonym(int id) {
        database.delete(WordAntonymTable.TABLE_NAME, WordAntonymTable.COLUMN_WORD_ID
                + " = " + id + " or " + WordAntonymTable.COLUMN_ANTONYM_ID + " = " + id, null);
    }

    @Override
    public ArrayList<Word> getAllAntonymsByWord(int id) {
        ArrayList<Word> antonyms = new ArrayList<>();

        Cursor cursor = database.query(WordAntonymTable.TABLE_NAME,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(0) == id) {
                int neededID = cursor.getInt(1);
                Word a = new Word();
                Cursor c = database.rawQuery("select * from " + WordsTable.TABLE_NAME + " inner join " + WordAntonymTable.TABLE_NAME + " on " + WordsTable.COLUMN_ID + " = " + neededID, null);
                c.moveToFirst();
                a.setId(c.getInt(0));
                a.setName(c.getString(1));
                a.setTranslation(c.getString(2));
                antonyms.add(a);
                c.close();
            }
            cursor.moveToNext();
        }
        cursor.close();
        return antonyms;
    }

}
