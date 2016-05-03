package com.toefldictionary.DB.executors.queries;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.toefldictionary.DB.DBHelper;
import com.toefldictionary.DB.executors.functionality.AntonymFunctionality;
import com.toefldictionary.DB.tables.AntonymsTable;

/**
 * Created by Gor on 27-Apr-16.
 */
public class AntonymQueries implements AntonymFunctionality {
    private String[] allColumns = {AntonymsTable.COLUMN_WORD_ID, AntonymsTable.COLUMN_ANTONYM_ID};
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public AntonymQueries(Context context) {
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
    public void addAntonym(int id1, int id2) {
        ContentValues values = new ContentValues();
        values.put(AntonymsTable.COLUMN_WORD_ID, id1);
        values.put(AntonymsTable.COLUMN_ANTONYM_ID, id2);
        database.insert(AntonymsTable.TABLE_NAME, null, values);
    }

    @Override
    public void deleteAntonym(int id) {
        database.delete(AntonymsTable.TABLE_NAME, AntonymsTable.COLUMN_WORD_ID
                + " = " + id + " or " + AntonymsTable.COLUMN_ANTONYM_ID + " = " + id, null);
    }
}
