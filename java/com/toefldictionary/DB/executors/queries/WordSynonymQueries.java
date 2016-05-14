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
        //Cursor c = database.rawQuery("select id, word, translation from " + WordsTable.TABLE_NAME + " inner join " + WordSynonymTable.TABLE_NAME + " on " + WordSynonymTable.COLUMN_WORD_ID + " = " + id,  null);
        Cursor c = database.rawQuery("SELECT W2.id,\n" +
                "       W2.word,\n" +
                "       W2.translation\n" +
                "FROM words\n" +
                "JOIN word_synonyms ON words.id = word_synonyms.synonym1_id\n" +
                "JOIN words AS W2 ON word_synonyms.synonym2_id = W2.id\n" +
                "WHERE words.id = " + id, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Word s = new Word();
            s.setId(c.getInt(0));
            s.setName(c.getString(1));
            s.setTranslation(c.getString(2));
            synonyms.add(s);
            c.moveToNext();
        }
        c.close();
        return synonyms;
    }
}
