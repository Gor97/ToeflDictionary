package com.toefldictionary.DB.executors.queries;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toefldictionary.DB.DBHelper;
import com.toefldictionary.DB.executors.functionality.TypeFunctionality;
import com.toefldictionary.DB.executors.objects.Type;
import com.toefldictionary.DB.tables.TypesTable;

import java.util.ArrayList;

/**
 * Created by Gor on 27-Apr-16.
 */
public class TypeQueries implements TypeFunctionality {
    private String[] allColumns = {TypesTable.COLUMN_TYPE_ID, TypesTable.COLUMN_TYPE_NAME};
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public TypeQueries(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }
    public void open() {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    private Type cursorToType(Cursor cursor) {
        Type t = new Type();
        t.setId(cursor.getInt(0));
        t.setName(cursor.getString(1));
        return t;
    }
    @Override
    public Type addType(Type type) {
        ContentValues values = new ContentValues();
        values.put(TypesTable.COLUMN_TYPE_NAME, type.getName());
        long insertId = database.insert(TypesTable.TABLE_NAME, null, values);
        Cursor cursor = database.query(TypesTable.TABLE_NAME, allColumns, TypesTable.COLUMN_TYPE_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Type newType = cursorToType(cursor);
        cursor.close();
        return newType;
    }

    @Override
    public Type editType(Type type) {
        return null;
    }

    @Override
    public void deleteType(int id) {

    }

    @Override
    public ArrayList<Type> getAllTypes() {
        return null;
    }
}
