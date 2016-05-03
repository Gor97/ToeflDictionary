package com.toefldictionary.DB.executors.queries;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toefldictionary.DB.DBHelper;
import com.toefldictionary.DB.executors.functionality.DescriptionFunctionality;
import com.toefldictionary.DB.executors.objects.Description;
import com.toefldictionary.DB.tables.DescriptionsTable;

/**
 * Created by Gor on 27-Apr-16.
 */
public class DescriptionQueries implements DescriptionFunctionality {

    private String[] allColumns = {DescriptionsTable.COLUMN_ID, DescriptionsTable.COLUMN_TEXT};
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public DescriptionQueries(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    private Description cursorToDescription(Cursor cursor) {
        Description d = new Description();
        d.setId(cursor.getInt(0));
        d.setText(cursor.getString(1));
        return d;
    }

    @Override
    public Description addDescription(Description description) {
        ContentValues values = new ContentValues();
        values.put(DescriptionsTable.COLUMN_TEXT, description.getText());
        long insertId = database.insert(DescriptionsTable.TABLE_NAME, null, values);
        Cursor cursor = database.query(DescriptionsTable.TABLE_NAME, allColumns, DescriptionsTable.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Description newDescription = cursorToDescription(cursor);
        cursor.close();
        return newDescription;
    }

    @Override
    public Description editDescription(Description description) {
        ContentValues values = new ContentValues();
        values.put(DescriptionsTable.COLUMN_TEXT, description.getText());

        long insertId = database.update(DescriptionsTable.TABLE_NAME, values, DescriptionsTable.COLUMN_ID + " = " + description.getId(), null);
        Cursor cursor = database.query(DescriptionsTable.TABLE_NAME, allColumns, DescriptionsTable.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Description newDescription = cursorToDescription(cursor);
        cursor.close();
        return newDescription;
    }

    @Override
    public void deleteDescription(int id) {
        database.delete(DescriptionsTable.TABLE_NAME, DescriptionsTable.COLUMN_ID
                + " = " + id, null);
    }
}
