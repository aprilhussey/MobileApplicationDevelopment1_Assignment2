package com.example.localshopecommerceapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.localshopecommerceapplication.models.CategoryModel;
import com.example.localshopecommerceapplication.models.ItemModel;
import com.example.localshopecommerceapplication.User;
import com.example.localshopecommerceapplication.models.OrderModel;

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

    private static String dbTableOrders = "orders";
    private static String orderId = "id";
    private static String orderUser = "user";
    private static String orderItems = "items";
    private static String orderAddress = "address";

    public DatabaseConnect(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryUsers = "create table " + dbTableUsers + " (" + firstName + " TEXT, " + lastName
                + " TEXT, " + email + " TEXT PRIMARY KEY, " + password + " TEXT)";
        sqLiteDatabase.execSQL(queryUsers);

        String queryItems = "create table " + dbTableItems + " (" + itemId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + itemName
                + " TEXT, " + itemCategory + " TEXT, " + itemPrice + " TEXT, " + itemVersion + " TEXT, " + itemSet + " TEXT, " + itemImageFilePath + " TEXT, "
                + itemDescription + " TEXT, " + itemInStock + " INTEGER)";
        sqLiteDatabase.execSQL(queryItems);

        String queryOrders = "CREATE TABLE " + dbTableOrders + " (" + orderId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + orderUser + " TEXT, " + orderItems + " TEXT," + orderAddress + " TEXT)";
        sqLiteDatabase.execSQL(queryOrders);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbTableUsers);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbTableItems);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbTableOrders);
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

    public ItemModel getItem(String selectedItemName, String selectedVer, String selectedSet) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dbTableItems + " WHERE " + itemName + " = ? AND " + itemVersion + " = ? AND " + itemSet + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{selectedItemName, selectedVer, selectedSet});

        int id;
        String name;
        String category;
        String price;
        String version;
        String set;
        String imageFilePath;
        String description;
        int inStock;

        ItemModel item = null;

        if (cursor.moveToFirst()) {
            do {
                // Access the values in the returned row using the column index or column name
                id = cursor.getInt(cursor.getColumnIndex(itemId));
                name = cursor.getString(cursor.getColumnIndex(itemName));
                category = cursor.getString(cursor.getColumnIndex(itemCategory));
                price = cursor.getString(cursor.getColumnIndex(itemPrice));
                version = cursor.getString(cursor.getColumnIndex(itemVersion));
                set = cursor.getString(cursor.getColumnIndex(itemSet));
                imageFilePath = cursor.getString(cursor.getColumnIndex(itemImageFilePath));
                description = cursor.getString(cursor.getColumnIndex(itemDescription));
                inStock = cursor.getInt(cursor.getColumnIndex(itemInStock));
                // Do something with the retrieved data
            } while (cursor.moveToNext());
            item = new ItemModel(id, name, category, price, version, set, imageFilePath, description, inStock);
        }
        cursor.close();
        return item;
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
            String columnPrice = cursor.getString(cursor.getColumnIndexOrThrow((itemPrice)));
            String columnVersion = cursor.getString((cursor.getColumnIndexOrThrow(itemVersion)));
            String columnSet = cursor.getString((cursor.getColumnIndexOrThrow(itemSet)));
            String columnImageFilePath = cursor.getString((cursor.getColumnIndexOrThrow(itemImageFilePath)));
            String columnDescription = cursor.getString((cursor.getColumnIndexOrThrow(itemDescription)));
            int columnInStock = cursor.getInt(cursor.getColumnIndexOrThrow((itemInStock)));

            ItemModel item = new ItemModel(columnId, columnName, columnCategory, columnPrice, columnVersion, columnSet, columnImageFilePath, columnDescription, columnInStock);
            items.add(item);

            cursor.moveToNext();
        }
        cursor.close();
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

    public ArrayList<ItemModel> getItemsOfCategory(String categoryToCheck) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT DISTINCT * FROM " + dbTableItems + " WHERE " + itemCategory + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{categoryToCheck});

        ArrayList<ItemModel> itemsOfCategory = new ArrayList<>();

       if (cursor.moveToFirst()) {
           do {
               int columnId = cursor.getInt(cursor.getColumnIndexOrThrow(itemId));
               String columnName = cursor.getString((cursor.getColumnIndexOrThrow(itemName)));
               String columnCategory = cursor.getString((cursor.getColumnIndexOrThrow(itemCategory)));
               String columnPrice = cursor.getString(cursor.getColumnIndexOrThrow((itemPrice)));
               String columnVersion = cursor.getString((cursor.getColumnIndexOrThrow(itemVersion)));
               String columnSet = cursor.getString((cursor.getColumnIndexOrThrow(itemSet)));
               String columnImageFilePath = cursor.getString((cursor.getColumnIndexOrThrow(itemImageFilePath)));
               String columnDescription = cursor.getString((cursor.getColumnIndexOrThrow(itemDescription)));
               int columnInStock = cursor.getInt(cursor.getColumnIndexOrThrow((itemInStock)));

               ItemModel item = new ItemModel(columnId, columnName, columnCategory, columnPrice, columnVersion, columnSet, columnImageFilePath, columnDescription, columnInStock);
               itemsOfCategory.add(item);

           } while (cursor.moveToNext());
        }
        cursor.close();
        return itemsOfCategory;
    }

    public String getItemPrice(ItemModel item) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + itemPrice + " FROM " + dbTableItems + " WHERE " + itemName + " = ? AND " + itemVersion + " = ? AND " + itemSet + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{item.getName(), item.getVersion(), item.getSet()});

        String price = null;

        if (cursor.moveToFirst()) {
            do {
                // Access the values in the returned row using the column index or column name
                price = cursor.getString(cursor.getColumnIndex(itemPrice));
                // Do something with the retrieved data
            } while (cursor.moveToNext());
        }
        cursor.close();
        return price;
    }

    public List<String> getVersionsOfItem(String itemToCheck) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + itemVersion + " FROM " + dbTableItems + " WHERE " + itemName + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{itemToCheck});

        List<String> itemVersions = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String version = cursor.getString(cursor.getColumnIndexOrThrow(itemVersion));
                itemVersions.add(version);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemVersions;
    }

    public List<String> getSetsOfItem(String itemToCheck) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + itemSet + " FROM " + dbTableItems + " WHERE " + itemName + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{itemToCheck});

        List<String> itemSets = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String set = cursor.getString(cursor.getColumnIndexOrThrow(itemSet));
                itemSets.add(set);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemSets;
    }

    public String getItemImageFilePath(ItemModel item) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + itemImageFilePath + " FROM " + dbTableItems + " WHERE " + itemName + " = ? AND " + itemVersion + " = ? AND " + itemSet + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{item.getName(), item.getVersion(), item.getSet()});

        String imageFilePath = null;

        if (cursor.moveToFirst()) {
            do {
                // Access the values in the returned row using the column index or column name
                imageFilePath = cursor.getString(cursor.getColumnIndex(itemImageFilePath));
                // Do something with the retrieved data
            } while (cursor.moveToNext());
        }
        cursor.close();
        return imageFilePath;
    }

    String itemDescriptionTxt;
    public String getItemDescription(ItemModel item) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + itemDescription + " FROM " + dbTableItems + " WHERE " + itemName + " = ? AND " + itemVersion + " = ? AND " + itemSet + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{item.getName(), item.getVersion(), item.getSet()});

        if (cursor.moveToFirst()) {
            do {
                // Access the values in the returned row using the column index or column name
                itemDescriptionTxt = cursor.getString(cursor.getColumnIndex(itemDescription));
                // Do something with the retrieved data
            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemDescriptionTxt;
    }

    int itemStockInt;
    String itemStock;
    public String getInStock(ItemModel item) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + itemInStock + " FROM " + dbTableItems + " WHERE " + itemName + " = ? AND " + itemVersion + " = ? AND " + itemSet + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{item.getName(), item.getVersion(), item.getSet()});

        if (cursor.moveToFirst()) {
            do {
                // Access the values in the returned row using the column index or column name
                itemStockInt = cursor.getInt(cursor.getColumnIndex(itemInStock));
                // Do something with the retrieved data
            } while (cursor.moveToNext());
        }
        cursor.close();

        if (itemStockInt > 0) {
            itemStock = "In Stock";
        }
        else {
            itemStock = "Out of Stock";
        }

        return itemStock;
    }

    public void checkItemsTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("PRAGMA table_info(" + dbTableItems + ")", null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            // You can also retrieve other column information such as "notnull", "dflt_value", and "pk"
            Log.d("PRAGMA", "Column name: " + name + ", Column type: " + type);
        }
        cursor.close();
    }

    public void addOrder(String address, ArrayList<ItemModel> itemsList, String userEmail) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        // Creates a useful text that is used to store items in a "list" separated by : for example: :1:2:3:
        StringBuilder items = new StringBuilder();
        for (ItemModel item : itemsList){
            items.append(items).append("Name: ").append(item.getName() + "\nVersion: " + item.getVersion() + "\nSet: " + item.getSet());
        }
        items.append("\n" + "\n");

        values.put(orderAddress, address);
        values.put(orderItems, items.toString());
        values.put(orderUser, userEmail);

        db.insert(dbTableOrders, null, values);
        db.close();
    }

    public ArrayList<OrderModel> getAdminOrders() {
        ArrayList<OrderModel> orders = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dbTableOrders;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            int columnId = cursor.getInt(cursor.getColumnIndexOrThrow(orderId));
            String columnAddress = cursor.getString((cursor.getColumnIndexOrThrow(orderAddress)));
            String columnItems = cursor.getString((cursor.getColumnIndexOrThrow(orderItems)));
            String columnUser = cursor.getString((cursor.getColumnIndexOrThrow(orderUser)));

            OrderModel order = new OrderModel(columnId, columnAddress, columnItems, columnUser);
            orders.add(order);

            cursor.moveToNext();
        }
        cursor.close();
        return orders;
    }

    public ArrayList<OrderModel> getOrders(String email) {
        ArrayList<OrderModel> orders = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dbTableOrders + " WHERE " + orderUser + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            int columnId = cursor.getInt(cursor.getColumnIndexOrThrow(orderId));
            String columnAddress = cursor.getString((cursor.getColumnIndexOrThrow(orderAddress)));
            String columnItems = cursor.getString((cursor.getColumnIndexOrThrow(orderItems)));
            String columnUser = cursor.getString((cursor.getColumnIndexOrThrow(orderUser)));

            OrderModel order = new OrderModel(columnId, columnAddress, columnItems, columnUser);
            orders.add(order);

            cursor.moveToNext();
        }
        cursor.close();
        return orders;
    }
}
