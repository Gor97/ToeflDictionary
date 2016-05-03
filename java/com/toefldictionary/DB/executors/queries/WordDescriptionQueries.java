package com.toefldictionary.DB.executors.queries;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toefldictionary.DB.DBHelper;
import com.toefldictionary.DB.executors.functionality.WordDescriptionFunctionality;
import com.toefldictionary.DB.executors.objects.Description;
import com.toefldictionary.DB.tables.DescriptionsTable;
import com.toefldictionary.DB.tables.WordDescriptionTable;

import java.util.ArrayList;

/**
 * Created by Gor on 01-May-16.
 */
public class WordDescriptionQueries implements WordDescriptionFunctionality {

    private String[] allColumns = {WordDescriptionTable.COLUMN_WORD_ID, WordDescriptionTable.COLUMN_DESCRIPTION_ID};
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public WordDescriptionQueries(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }



    @Override
    public void addWordDes(int id1, int id2) {
        ContentValues values = new ContentValues();
        values.put(WordDescriptionTable.COLUMN_WORD_ID, id1);
        values.put(WordDescriptionTable.COLUMN_DESCRIPTION_ID, id2);
        database.insert(WordDescriptionTable.TABLE_NAME, null, values);
    }

    @Override
    public void deleteWordDesByWord(int id) {
        database.delete(WordDescriptionTable.TABLE_NAME, WordDescriptionTable.COLUMN_WORD_ID
                + " = " + id, null);
    }

    @Override
    public void deleteWordDesByDes(int id) {
        database.delete(WordDescriptionTable.TABLE_NAME, WordDescriptionTable.COLUMN_DESCRIPTION_ID
                + " = " + id, null);
    }

    @Override
    public ArrayList<Description> getAllDesByWord(int id) {
        ArrayList<Description> descriptions = new ArrayList<>();

        Cursor cursor = database.query(WordDescriptionTable.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(0) == id) {
                int neededID = cursor.getInt(1);
                Description d = new Description();
                Cursor c = database.rawQuery("select * from " + DescriptionsTable.TABLE_NAME, null);
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    if (c.getInt(0) == neededID) {
                        d.setId(neededID);
                        d.setText(c.getString(1));
                        descriptions.add(d);
                    }
                    c.moveToNext();
                }
                cursor.moveToNext();
                c.close();

            }
        }
        cursor.close();
        return descriptions;
    }
}
