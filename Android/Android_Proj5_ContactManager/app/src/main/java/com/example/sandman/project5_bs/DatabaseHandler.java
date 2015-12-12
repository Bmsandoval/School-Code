package com.example.sandman.project5_bs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
//    public static final String AUTOINCRMNT = " integer primary key autoincrement, ";
    public static final String NOT_NULL = " TEXT NOT NULL, ";
    public static final String TABLE_FRIENDS = "friends";
    public static final String TABLE_FAMILY = "family";
    public static final String TABLE_COWORKER = "coworkers";
    public static final String TABLE_OTHER = "other";
    public static final String KEY_TABLE = "tbl";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_ID = "_id";
    private static final String DATABASE_NAME = "contact.db";
    private static final int DATABASE_VERSION = 1;
    private static final String[] table = {TABLE_FRIENDS, TABLE_FAMILY, TABLE_COWORKER, TABLE_OTHER};
//    private static final String CREATE_TABLE_FRIENDS = "CREATE TABLE friends(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, phone TEXT NOT NULL, email TEXT NOT NULL, address TEXT NOT NULL, tab TEXT NOT NULL);";
    private static final String CREATE_TABLE_FRIENDS = "CREATE TABLE " + TABLE_FRIENDS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + NOT_NULL + KEY_PHONE + NOT_NULL + KEY_EMAIL + NOT_NULL + KEY_ADDRESS + NOT_NULL + KEY_TABLE + " TEXT NOT NULL);";
    public static final String CREATE_TABLE_FAMILY = "CREATE TABLE " + TABLE_FAMILY + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + NOT_NULL + KEY_PHONE + NOT_NULL + KEY_EMAIL + NOT_NULL + KEY_ADDRESS + NOT_NULL + KEY_TABLE + " TEXT NOT NULL);";
    public static final String CREATE_TABLE_COWORKERS = "CREATE TABLE " + TABLE_COWORKER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + NOT_NULL + KEY_PHONE + NOT_NULL + KEY_EMAIL + NOT_NULL + KEY_ADDRESS + NOT_NULL + KEY_TABLE + " TEXT NOT NULL);";
    public static final String CREATE_TABLE_OTHER = "CREATE TABLE " + TABLE_OTHER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + NOT_NULL + KEY_PHONE + NOT_NULL + KEY_EMAIL + NOT_NULL + KEY_ADDRESS + NOT_NULL + KEY_TABLE + " TEXT NOT NULL);";


    public DatabaseHandler(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_FAMILY);
        database.execSQL(CREATE_TABLE_FRIENDS);
        database.execSQL(CREATE_TABLE_COWORKERS);
        database.execSQL(CREATE_TABLE_OTHER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAMILY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COWORKER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OTHER);
        // create new tables
        onCreate(db);
    }

    /*****************************************************************************************************
     *
     * @param table relation to user
     * @param name contact's name
     * @param phone contact's phone numb
     * @param email contact's email
     * @param address contact's address
     */
    public void createContact(String table, String name, String phone, String email, String address) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TABLE, table);
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE, phone);
        values.put(KEY_EMAIL, email);
        values.put(KEY_ADDRESS, address);
        db.insert(table,null, values);
        db.close();
    }

    /***********************************************************************************************
     * @param id find contact by id#
     * @return contact object found by id
     */
    public Contact getContact(int id) {
        Cursor cursor;
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.query(table[0], new String[] {KEY_ID, KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_ADDRESS, KEY_TABLE}, KEY_ID + "=?", new String[] { String.valueOf(id)}, null, null, null, null );
        if(cursor == null) {
            cursor = db.query(table[1], new String[]{KEY_ID, KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_ADDRESS, KEY_TABLE}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
            if(cursor == null) {
                cursor = db.query(table[2], new String[] {KEY_ID, KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_ADDRESS, KEY_TABLE}, KEY_ID + "=?", new String[] { String.valueOf(id)}, null, null, null, null );
                if(cursor == null) {
                    cursor = db.query(table[3], new String[] {KEY_ID, KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_ADDRESS, KEY_TABLE}, KEY_ID + "=?", new String[] { String.valueOf(id)}, null, null, null, null );
                }
            }
        }
        if(cursor != null)
            cursor.moveToFirst();
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        db.close();
        cursor.close();
        return contact;
    }

    /******************************************************************************************
     * @param contact contact object to be deleted
     */
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(contact.getTable(), KEY_ID + "=?", new String[] { String.valueOf(contact.getID())});
        db.close();
    }

    /********************************************************************************************
     * @return Total contacts stored
     */
 /*   public int getContactsCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        int count = 0;
        for(int i = 0; i < 4; i++) {
            cursor = db.rawQuery("SELECT * FROM " + table[i], null);
            count += cursor.getCount();
        }
        cursor.close();
        db.close();
        return count;
    }
*/
    /***********************************************************************************************
     *@return list of all contacts (use this with the array adapter?)
     */
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;
        for(int i = 0; i < 4; i++) {
            cursor = db.rawQuery("SELECT * FROM " + table[i], null);
            if (cursor.moveToFirst()) {
                do {
                    Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                    contacts.add(contact);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return contacts;
    }

    /***********************************************************************************************
     *
     * @param table table from which you want to view contacts
     * @return string array to use with adapter
     */
    public ArrayList<Contact> getTableContacts(String table){
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                contacts.add(contact);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contacts;
    }
}
