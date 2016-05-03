package com.toefldictionary.DB.executors.queries;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toefldictionary.DB.DBHelper;
import com.toefldictionary.DB.executors.functionality.WordFunctionality;
import com.toefldictionary.DB.executors.objects.Word;
import com.toefldictionary.DB.tables.WordsTable;

import java.util.ArrayList;

/**
 * Created by Gor on 25-Apr-16.
 */
public class WordQueries implements WordFunctionality {

    private String[] allColumns = {WordsTable.COLUMN_ID, WordsTable.COLUMN_WORD, WordsTable.COLUMN_TRANSLATION};
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public WordQueries(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    private Word cursorToWord(Cursor cursor) {
        Word w = new Word();
        w.setId(cursor.getInt(0));
        w.setName(cursor.getString(1));
        w.setTranslation(cursor.getString(2));
        return w;
    }
    @Override
    public Word addWord(Word word) {
        ContentValues values = new ContentValues();
        values.put(WordsTable.COLUMN_WORD, word.getName());
        values.put(WordsTable.COLUMN_TRANSLATION, word.getTranslation());

        long insertId = database.insert(WordsTable.TABLE_NAME, null, values);
        Cursor cursor = database.query(WordsTable.TABLE_NAME, allColumns, WordsTable.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Word newWord = cursorToWord(cursor);
        cursor.close();
        return newWord;
    }

    @Override
    public Word editWord(Word word) {
        ContentValues values = new ContentValues();
        values.put(WordsTable.COLUMN_WORD, word.getName());
        values.put(WordsTable.COLUMN_TRANSLATION, word.getTranslation());

        long insertId = database.update(WordsTable.TABLE_NAME, values, WordsTable.COLUMN_ID + " = " + word.getId(), null);
        Cursor cursor = database.query(WordsTable.TABLE_NAME, allColumns, WordsTable.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Word newWord = cursorToWord(cursor);
        cursor.close();
        return newWord;
    }

    @Override
    public void deleteWord(int id) {
        database.delete(WordsTable.TABLE_NAME, WordsTable.COLUMN_ID
                + " = " + id, null);
    }

    @Override
    public ArrayList<Word> getAllWords() {
        ArrayList<Word> words = new ArrayList<>();

        Cursor cursor = database.query(WordsTable.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Word w = cursorToWord(cursor);
            words.add(w);
            cursor.moveToNext();
        }

        cursor.close();
        return words;
    }
}
