package com.example.localshopecommerceapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseConnect extends SQLiteOpenHelper {
    // Declare variables
    private static String dbName = "ecommerceApp";
    private static String dbTable = "users";
    private static int dbVersion = 1;

    private static String firstName = "firstName";
    private static String lastName = "lastName";
    private static String email = "emailAddress";
    private static String password = "password";

    public DatabaseConnect(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + dbTable + " (INTEGER PRIMARY KEY AUTOINCREMENT, "
                + firstName + " TEXT, " + lastName + " TEXT, " + email + " TEXT, " + password + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbTable);
        onCreate(sqLiteDatabase);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(firstName, user.getFirstName());
        values.put(lastName, user.getLastName());
        values.put(email, user.getEmail());
        values.put(password, user.getPassword());

        db.insert(dbTable, null, values);
    }
}
