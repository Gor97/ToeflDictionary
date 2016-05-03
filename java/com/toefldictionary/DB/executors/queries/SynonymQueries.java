package com.toefldictionary.DB.executors.queries;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toefldictionary.DB.DBHelper;
import com.toefldictionary.DB.executors.functionality.SynonymFunctionality;
import com.toefldictionary.DB.tables.SynonymsTable;

import java.util.ArrayList;

/**
 * Created by Gor on 27-Apr-16.
 */
public class SynonymQueries implements SynonymFunctionality {

    private String[] allColumns = {SynonymsTable.COLUMN_WORD_ID, SynonymsTable.COLUMN_SYNONYM_ID};
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public SynonymQueries(Context context) {
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
    public void addSynonym(int id1, int id2) {
        ContentValues values = new ContentValues();
        values.put(SynonymsTable.COLUMN_WORD_ID, id1);
        values.put(SynonymsTable.COLUMN_SYNONYM_ID, id2);
        database.insert(SynonymsTable.TABLE_NAME, null, values);
    }

    @Override
    public void deleteSynonym(int id) {
        database.delete(SynonymsTable.TABLE_NAME, SynonymsTable.COLUMN_WORD_ID
                + " = " + id + " or " + SynonymsTable.COLUMN_SYNONYM_ID + " = " + id, null);
    }

    //************************

    // FOR TESTING

    public ArrayList<Integer> getSynonyms() {
        ArrayList<Integer> pairs = new ArrayList<>();

        Cursor cursor = database.query(SynonymsTable.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            pairs.add(cursorToWord(cursor));
            cursor.moveToNext();
        }

        cursor.close();
        return pairs;
    }

    private int cursorToWord(Cursor cursor) {
        return cursor.getInt(0) * cursor.getInt(1);
    }
}
