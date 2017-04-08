package com.example.coledeman.homebrew.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cole DeMan on 4/8/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "home_brew";

    private static final String KEY_ID = "id";

    //brew info
    private static final String BREW_TABLE = "brews";
    private static final String BREW_NAME = "name";
    private static final String BREW_DATE = "brew_date";
    private static final String BREW_INTIAL_GRAVITY = "intial_gravity";
    private static final String BREW_FINAL_GRAVITY = "final_gravity";
    private static final String BREW_DESCRIPTION = "final_gravity";

    //brew ingredients
    private static final String BREW_INGREDIENT_NAME = "name";
    private static final String BREW_ID = "brew_id";
    private static final String BREW_INGREDIENT_TABLE = "brew_ingredients";
    private static final String BREW_INGREDIENT_UNIT = "unit";
    private static final String BREW_INGREDIENT_QUANTITY = "quantity";

    //gravity measurements
    private static final String GRAVITY_MEASUREMENT_TABLE = "gravity_measurements";
    private static final String GRAVITY_MEASUREMENT_GRAVITY = "gravity";
    private static final String GRAVITY_MEASUREMENT_DATE = "date";
    private static final String GRAVITY_MEASUREMENT_TEMP = "gravity";

    private static DatabaseHelper sInstance;

    //table creation methods
    private void createBrewTable(SQLiteDatabase db) {
        String CREATE_BREW_DATABASE = "CREATE TABLE "
                + BREW_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + BREW_NAME + " TEXT,"
                + BREW_DESCRIPTION + " TEXT,"
                + BREW_DATE + " DATE,"
                + BREW_INTIAL_GRAVITY + " REAL,"
                + BREW_FINAL_GRAVITY + " REAL"
                + ")";
        db.execSQL(CREATE_BREW_DATABASE);
    }

    private void createBrewIngedientTable(SQLiteDatabase db) {
        String CREATE_BREW_INGREDIENT_DATABASE = "CREATE TABLE "
                + BREW_INGREDIENT_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + BREW_ID + " REFERENCES " + BREW_TABLE + "(" + KEY_ID + ") ON DELETE CASCADE,"
                + BREW_INGREDIENT_UNIT + " INTEGER,"
                + BREW_INGREDIENT_QUANTITY + " TEXT"
                + ")";
        db.execSQL(CREATE_BREW_INGREDIENT_DATABASE);
    }

    private void createGravityTable(SQLiteDatabase db) {
        String CREATE_BREW_INGREDIENT_DATABASE = "CREATE TABLE "
                + GRAVITY_MEASUREMENT_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + BREW_ID + " REFERENCES " + BREW_TABLE + "(" + KEY_ID + ") ON DELETE CASCADE,"
                + GRAVITY_MEASUREMENT_DATE + " DATE,"
                + GRAVITY_MEASUREMENT_GRAVITY + " REAL,"
                + GRAVITY_MEASUREMENT_TEMP + " REAL"
                + ")";
        db.execSQL(CREATE_BREW_INGREDIENT_DATABASE);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createBrewTable(db);
        createBrewIngedientTable(db);
        createGravityTable(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BREW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BREW_INGREDIENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + GRAVITY_MEASUREMENT_TABLE);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase database) {
        database.setForeignKeyConstraintsEnabled(true);

    }
}
