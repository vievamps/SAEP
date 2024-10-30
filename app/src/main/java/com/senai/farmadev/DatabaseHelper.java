package com.senai.farmadev;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "farmadev.db";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";


    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_PRODUCT_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "nome";
    private static final String COLUMN_PRODUCT_CATEGORY = "categoria";
    private static final String COLUMN_PRODUCT_PRICE = "preco";
    private static final String COLUMN_PRODUCT_QUANTITY = "quantidade";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT UNIQUE,"
                + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);


        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRODUCT_NAME + " TEXT,"
                + COLUMN_PRODUCT_CATEGORY + " TEXT,"
                + COLUMN_PRODUCT_PRICE + " TEXT,"
                + COLUMN_PRODUCT_QUANTITY + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }


    public boolean addProduct(String nome, String categoria, String preco, String quantidade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, nome);
        values.put(COLUMN_PRODUCT_CATEGORY, categoria);
        values.put(COLUMN_PRODUCT_PRICE, preco);
        values.put(COLUMN_PRODUCT_QUANTITY, quantidade);
        long result = db.insert(TABLE_PRODUCTS, null, values);
        db.close();
        return result != -1;
    }


    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);
    }


}
