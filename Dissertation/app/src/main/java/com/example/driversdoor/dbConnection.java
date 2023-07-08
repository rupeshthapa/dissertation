package com.example.driversdoor;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbConnection extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "addressBookManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "addressBook";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USER_TYPE = "userType";

    public dbConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_USER_TYPE + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void addUser(AddressBook addressBook) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, addressBook.getName());
        values.put(COLUMN_EMAIL, addressBook.getEmail());
        values.put(COLUMN_PASSWORD, addressBook.getPassword());
        values.put(COLUMN_USER_TYPE, addressBook.getUserType());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUsername(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_EMAIL}, COLUMN_EMAIL + "=?", new String[]{email}, null, null, null, null);
        boolean emailExists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return emailExists;
    }

    public boolean checkCredentials(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        boolean hasMatch = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return hasMatch;
    }
}