package com.example.localshopecommerceapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        String query = "create table " + dbTable + " (" + firstName + " TEXT, " + lastName
                + " TEXT, " + email + " TEXT PRIMARY KEY, " + password + " TEXT)";
        sqLiteDatabase.execSQL(query);

        User adminUser = new User("Admin", "Account", "admin@email.com", "account");
        addUser(adminUser);
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

    public boolean checkIfEmailExists(String emailToCheck) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + dbTable + " WHERE " + email + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{emailToCheck});

        if (cursor.getCount() <= 0) {
            // Email does not exist
            cursor.close();
            return false;
        }
        else {
            // Email exists
            cursor.close();
            return true;
        }
    }

    public String passwordCheck(String emailToCheck) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + dbTable + " WHERE " + email + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{emailToCheck});

        if (cursor.moveToFirst()) {
            // Email exists
            String passwordInDb = cursor.getString(cursor.getColumnIndex(password));
            cursor.close();
            return passwordInDb;
        }
        else {
            // Email does not exist
            cursor.close();
            return null;
        }
    }
}
