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
        Cursor c = database.rawQuery("SELECT W2.id,\n" +
                "       W2.word,\n" +
                "       W2.translation\n" +
                "FROM words\n" +
                "JOIN word_antonyms ON words.id = word_antonyms.antonym1_id\n" +
                "JOIN words AS W2 ON word_antonyms.antonym2_id = W2.id\n" +
                "WHERE words.id = " + id, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Word a = new Word();
            a.setId(c.getInt(0));
            a.setName(c.getString(1));
            a.setTranslation(c.getString(2));
            antonyms.add(a);
            c.moveToNext();
        }
        c.close();
        return antonyms;
    }

}
