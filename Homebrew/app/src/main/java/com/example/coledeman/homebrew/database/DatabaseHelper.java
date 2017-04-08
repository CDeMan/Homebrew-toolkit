package com.example.coledeman.homebrew.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.coledeman.homebrew.objects.Brew;
import com.example.coledeman.homebrew.objects.BrewIngredient;
import com.example.coledeman.homebrew.objects.Enums.Unit;
import com.example.coledeman.homebrew.objects.GravityMeasurement;

import java.sql.Date;
import java.util.ArrayList;

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

    public Brew addBrew(Brew brew) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BREW_NAME, brew.getName());
        values.put(BREW_DATE, brew.getDate().toString());
        values.put(BREW_INTIAL_GRAVITY, brew.getInitialGravity());
        values.put(BREW_FINAL_GRAVITY, brew.getFinalGravity());
        values.put(BREW_NAME, brew.getName());
        values.put(BREW_DESCRIPTION, brew.getDescription());

        long id = db.insert(BREW_TABLE, null, values);

        //// TODO: 4/8/2017 use get brew by id to get the added brew to return, incase something is updated. 
        brew.setId(id);
        db.close();

        return brew;
    }

    public Brew getBrewById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                BREW_TABLE,
                new String[]{
                        KEY_ID, BREW_DATE, BREW_INTIAL_GRAVITY, BREW_FINAL_GRAVITY,
                        BREW_NAME, BREW_DESCRIPTION
                },
                KEY_ID + "=?",
                new String[]{
                        String.valueOf(id)
                },
                null, null, null
        );

        Brew brew = null;
        if (cursor.moveToFirst()) {
            brew = new Brew(cursor.getLong(0), Date.valueOf(cursor.getString(1)), cursor.getDouble(2),
                    cursor.getDouble(3), cursor.getString(4), cursor.getString(5),
                    getAllGravityMeasurementsByBrewId(cursor.getLong(0)), getAllBrewIngredients(cursor.getLong(0)));
        }

        cursor.close();
        db.close();
        return null;
    }

    public GravityMeasurement addGravityMeasurement(GravityMeasurement gravity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BREW_ID, gravity.getBrewId());
        values.put(GRAVITY_MEASUREMENT_DATE, gravity.getDate().toString());
        values.put(GRAVITY_MEASUREMENT_TEMP, gravity.getTemp());
        values.put(GRAVITY_MEASUREMENT_GRAVITY, gravity.getGravity());

        long id = db.insert(GRAVITY_MEASUREMENT_TABLE, null, values);

        //// TODO: 4/8/2017 use get brew by id to get the added brew to return, incase something is updated.
        gravity.setId(id);
        db.close();

        return gravity;
    }

    public GravityMeasurement getGravityMeasurementById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                GRAVITY_MEASUREMENT_TABLE,
                new String[]{
                        KEY_ID, BREW_ID, GRAVITY_MEASUREMENT_DATE, GRAVITY_MEASUREMENT_GRAVITY,
                        GRAVITY_MEASUREMENT_TEMP
                },
                KEY_ID + "=?",
                new String[]{
                        String.valueOf(id)
                },
                null, null, null
        );
        GravityMeasurement gravity = null;
        if (cursor.moveToFirst()) {
            gravity = new GravityMeasurement(cursor.getLong(0), cursor.getLong(1), Date.valueOf(cursor.getString(2)),
                    cursor.getDouble(3), cursor.getInt(4));
        }
        cursor.close();
        db.close();
        return gravity;
    }

    public ArrayList<GravityMeasurement> getAllGravityMeasurementsByBrewId(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<GravityMeasurement> gravities = new ArrayList<GravityMeasurement>();
        Cursor cursor = db.query(GRAVITY_MEASUREMENT_TABLE,
                new String[]{
                        KEY_ID, BREW_ID, GRAVITY_MEASUREMENT_DATE, GRAVITY_MEASUREMENT_GRAVITY,
                        GRAVITY_MEASUREMENT_TEMP
                }, BREW_ID + "=?", new String[]{String.valueOf(id)}, null, null, BREW_NAME);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            gravities.add(new GravityMeasurement(cursor.getLong(0), cursor.getLong(1), Date.valueOf(cursor.getString(2)),
                    cursor.getDouble(3), cursor.getInt(4)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return gravities;
    }

    public BrewIngredient addBrewIngredient(BrewIngredient ing) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BREW_INGREDIENT_NAME, ing.getName());
        values.put(BREW_INGREDIENT_QUANTITY, ing.getQuantity());
        values.put(BREW_INGREDIENT_UNIT, ing.getUnit().getId());
        values.put(BREW_ID, ing.getBrewID());

        long id = db.insert(BREW_INGREDIENT_TABLE, null, values);

        //// TODO: 4/8/2017 use get brew by id to get the added brew to return, incase something is updated.
        ing.setId(id);
        db.close();

        return ing;
    }

    public BrewIngredient getBrewIngredientById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                BREW_INGREDIENT_TABLE,
                new String[]{
                        KEY_ID, BREW_ID, BREW_NAME, BREW_INGREDIENT_NAME, BREW_INGREDIENT_UNIT,
                        BREW_INGREDIENT_QUANTITY
                },
                KEY_ID + "=?",
                new String[]{
                        String.valueOf(id)
                },
                null, null, null
        );
        BrewIngredient ing = null;
        if (cursor.moveToFirst()) {
            ing = new BrewIngredient(cursor.getLong(0), cursor.getLong(1), cursor.getString(2),
                    Unit.getUnitByInt(cursor.getInt(3)), cursor.getInt(4));
        }
        cursor.close();
        db.close();
        return ing;
    }

    public ArrayList<BrewIngredient> getAllBrewIngredients(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<BrewIngredient> ingredients = new ArrayList<BrewIngredient>();
        Cursor cursor = db.query(BREW_INGREDIENT_TABLE,
                new String[]{
                        KEY_ID, BREW_ID, BREW_NAME, BREW_INGREDIENT_NAME, BREW_INGREDIENT_UNIT,
                        BREW_INGREDIENT_QUANTITY
                }, BREW_ID + "=?", new String[]{String.valueOf(id)}, null, null, BREW_NAME);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            ingredients.add(new BrewIngredient(cursor.getLong(0), cursor.getLong(1), cursor.getString(2),
                    Unit.getUnitByInt(cursor.getInt(3)), cursor.getInt(4)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return ingredients;
    }

}
