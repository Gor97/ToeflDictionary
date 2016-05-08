package com.toefldictionary.DB.executors.queries;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toefldictionary.DB.DBHelper;
import com.toefldictionary.DB.executors.functionality.WordTypeFunctionality;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.DB.tables.WordTypeTable;
import com.toefldictionary.DB.tables.WordsTable;

import java.util.ArrayList;

/**
 * Created by Gor on 27-Apr-16.
 */
public class WordTypeQueries implements WordTypeFunctionality {

    private String[] allColumns = {WordTypeTable.COLUMN_WORD_ID, WordTypeTable.COLUMN_TYPE_ID};
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public WordTypeQueries(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }


    @Override
    public void addWordType(int id1, int id2) {
        ContentValues values = new ContentValues();
        values.put(WordTypeTable.COLUMN_WORD_ID, id1);
        values.put(WordTypeTable.COLUMN_TYPE_ID, id2);
        database.insert(WordTypeTable.TABLE_NAME, null, values);
    }

    @Override
    public void deleteWordTypeByWord(int id) {
        database.delete(WordTypeTable.TABLE_NAME, WordTypeTable.COLUMN_WORD_ID
                + " = " + id, null);
    }

    @Override
    public void deleteWordTypeByType(int id) {
        database.delete(WordTypeTable.TABLE_NAME, WordTypeTable.COLUMN_TYPE_ID
                + " = " + id, null);
    }

    @Override
    public ArrayList<Word> getAllWordByType(int id) {
        ArrayList<Word> words = new ArrayList<>();

        Cursor cursor = database.query(WordTypeTable.TABLE_NAME,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(1) == id) {
                int neededID = cursor.getInt(0);
                Word w = new Word();
                Cursor c = database.rawQuery("select * from " + WordsTable.TABLE_NAME + " inner join " + WordTypeTable.TABLE_NAME + " on " + WordsTable.COLUMN_ID + " = " + neededID, null);
                c.moveToFirst();
                w.setId(c.getInt(0));
                w.setName(c.getString(1));
                w.setTranslation(c.getString(2));
                words.add(w);
                c.close();
            }
            cursor.moveToNext();
        }
        cursor.close();
        return words;
    }
}