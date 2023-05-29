package com.example.localshopecommerceapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnect extends SQLiteOpenHelper {
    // Declare variables
    private static String dbName = "ecommerceApp";
    private static int dbVersion = 1;

    // Users table
    private static String dbTableUsers = "users";
    private static String firstName = "firstName";
    private static String lastName = "lastName";
    private static String email = "emailAddress";
    private static String password = "password";

    // Items table
    private static String dbTableItems = "items";
    private static String itemId = "id";
    private static String itemName = "name";
    private static String itemCategory = "category";
    private static String itemPrice = "price";
    private static String itemVersion = "version";
    private static String itemSet = "itemSet";
    private static String itemDescription = "description";
    private static String itemImageFilePath = "imageFilePath";
    private static String itemInStock = "inStock";

    public DatabaseConnect(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryUsers = "create table " + dbTableUsers + " (" + firstName + " TEXT, " + lastName
                + " TEXT, " + email + " TEXT PRIMARY KEY, " + password + " TEXT)";
        sqLiteDatabase.execSQL(queryUsers);

        //User adminUser = new User("Admin", "Account", "admin@email.com", "account");
        //addUser(adminUser);

        //User userUser = new User("User", "Account", "user@email.com", "account");
        //addUser(userUser);

        String queryItems = "create table " + dbTableItems + " (" + itemId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + itemName
                + " TEXT, " + itemCategory + " TEXT, " + itemPrice + " INTEGER, " + itemVersion + " TEXT, " + itemSet + " TEXT, " + itemImageFilePath + " TEXT, "
                + itemDescription + " TEXT, " + itemInStock + " INTEGER)";
        sqLiteDatabase.execSQL(queryItems);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbTableUsers);
        onCreate(sqLiteDatabase);
    }

    // Functions for users table
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(firstName, user.getFirstName());
        values.put(lastName, user.getLastName());
        values.put(email, user.getEmail());
        values.put(password, user.getPassword());

        db.insert(dbTableUsers, null, values);
        db.close();
    }

    public boolean updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(firstName, user.getFirstName());
        values.put(lastName, user.getLastName());
        values.put(email, user.getEmail());
        values.put(password, user.getPassword());

        int updatedUser = db.update(dbTableUsers, values, email + "=?", new String[]{String.valueOf(user.getEmail())});

        if (updatedUser > 0) {  // User updated successfully
            return true;
        }
        else {
            return false;
        }
    }

    public boolean deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        int deleteUser = db.delete(dbTableUsers,email + "=?", new String[]{String.valueOf(user.getEmail())});

        if (deleteUser > 0) {  // User updated successfully
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dbTableUsers;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            String columnFirstName = cursor.getString((cursor.getColumnIndexOrThrow(firstName)));
            String columnLastName = cursor.getString((cursor.getColumnIndexOrThrow(lastName)));
            String columnEmail = cursor.getString((cursor.getColumnIndexOrThrow(email)));
            String columnPassword = cursor.getString((cursor.getColumnIndexOrThrow(password)));

            User user = new User(columnFirstName, columnLastName, columnEmail, columnPassword);
            users.add(user);

            cursor.moveToNext();
        }
        return users;
    }

    public boolean checkIfEmailExists(String emailToCheck) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + dbTableUsers + " WHERE " + email + " = ?";
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

        String query = "SELECT * FROM " + dbTableUsers + " WHERE " + email + " = ?";
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

    // Functions for items table
    public void addItem(ItemModel item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(itemId, item.getId());
        values.put(itemName, item.getName());
        values.put(itemCategory, item.getCategory());
        values.put(itemPrice, item.getPrice());
        values.put(itemVersion, item.getVersion());
        values.put(itemSet, item.getSet());
        values.put(itemImageFilePath, item.getImageFilePath());
        values.put(itemDescription, item.getDescription());
        values.put(itemInStock, item.getInStock());

        db.insert(dbTableItems, null, values);
        db.close();
    }

    public boolean updateItem(ItemModel item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(itemId, item.getId());
        values.put(itemName, item.getName());
        values.put(itemCategory, item.getCategory());
        values.put(itemPrice, item.getPrice());
        values.put(itemVersion, item.getVersion());
        values.put(itemSet, item.getSet());
        values.put(itemImageFilePath, item.getImageFilePath());
        values.put(itemDescription, item.getDescription());
        values.put(itemInStock, item.getInStock());

        int updatedItem = db.update(dbTableItems, values, itemId + "=?", new String[]{String.valueOf(item.getId())});

        if (updatedItem > 0) {  // Item updated successfully
            return true;
        }
        else {
            return false;
        }
    }

    public boolean deleteItem(ItemModel item) {
        SQLiteDatabase db = this.getWritableDatabase();

        int deleteItem = db.delete(dbTableItems,itemId + "=?", new String[]{String.valueOf(item.getId())});

        if (deleteItem > 0) {  // Item updated successfully
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<ItemModel> getAllItems() {
        ArrayList<ItemModel> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dbTableItems;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            int columnId = cursor.getInt(cursor.getColumnIndexOrThrow(itemId));
            String columnName = cursor.getString((cursor.getColumnIndexOrThrow(itemName)));
            String columnCategory = cursor.getString((cursor.getColumnIndexOrThrow(itemCategory)));
            int columnPrice = cursor.getInt(cursor.getColumnIndexOrThrow((itemPrice)));
            String columnVersion = cursor.getString((cursor.getColumnIndexOrThrow(itemVersion)));
            String columnSet = cursor.getString((cursor.getColumnIndexOrThrow(itemSet)));
            String columnImageFilePath = cursor.getString((cursor.getColumnIndexOrThrow(itemImageFilePath)));
            String columnDescription = cursor.getString((cursor.getColumnIndexOrThrow(itemDescription)));
            int columnInStock = cursor.getInt(cursor.getColumnIndexOrThrow((itemInStock)));

            ItemModel item = new ItemModel(columnId, columnName, columnCategory, columnPrice, columnVersion, columnSet, columnImageFilePath, columnDescription, columnInStock);
            items.add(item);

            cursor.moveToNext();
        }
        return items;
    }

    public ArrayList<CategoryModel> getAllCategories() {
        ArrayList<CategoryModel> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT DISTINCT " + itemCategory + " FROM " + dbTableItems;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            String columnName = cursor.getString((cursor.getColumnIndexOrThrow(itemCategory)));

            CategoryModel category = new CategoryModel(columnName);
            categories.add(category);

            cursor.moveToNext();
        }
        return categories;
    }

    public List<String> getVersionsOfItem(String itemToCheck) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + itemVersion + " FROM " + dbTableItems + " WHERE " + itemName + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{itemToCheck});

        List<String> itemVersions = new ArrayList<>();

        while (cursor.moveToNext()) {
            String version = cursor.getString(cursor.getColumnIndexOrThrow(itemVersion));
            itemVersions.add(version);
        }
        cursor.close();
        return itemVersions;
    }

    public List<String> getSetsOfItem(String itemToCheck) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + itemSet + " FROM " + dbTableItems + " WHERE " + itemName + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{itemToCheck});

        List<String> itemSets = new ArrayList<>();

        while (cursor.moveToNext()) {
            String set = cursor.getString(cursor.getColumnIndexOrThrow(itemSet));
            itemSets.add(set);
        }
        cursor.close();
        return itemSets;
    }
}
