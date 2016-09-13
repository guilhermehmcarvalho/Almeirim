package com.guilherme.almeirim.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guilherme.almeirim.DB.Models.PatioItemModel;

/**
 * Created by Guilherme on 11/09/2016.
 */
public class DBPatioItem extends SQLiteOpenHelper {

    private static final String TABLE_PATIO_ITEM = "patio_table_item";

    private static final String KEY_ID = "id";
    private static final String KEY_COMP1 = "comprimento1";
    private static final String KEY_COMP2 = "comprimento2";
    private static final String KEY_RODO = "rodo";
    private static final String KEY_DIAM1 = "diametro1";
    private static final String KEY_DIAM2 = "diametro2";
    private static final String KEY_OCO = "oco";
    private static final String KEY_PATIO = "patio";
    private static final String KEY_ARVORE = "arvore";

    public DBPatioItem(Context context) {
        super(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = "CREATE TABLE " + TABLE_PATIO_ITEM + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_COMP1 + " REAL, " +
                KEY_COMP2 + " REAL, " +
                KEY_RODO + " REAL, " +
                KEY_DIAM1 + " REAL, " +
                KEY_DIAM2 + " REAL, " +
                KEY_OCO + " REAL, " +
                KEY_PATIO + "INTEGER, " +
                KEY_ARVORE + "INTEGER " +
                ")";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIO_ITEM);
        // Creating tables again
        onCreate(db);
    }

    public void addItem(PatioItemModel item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, item.getId());
        values.put(KEY_COMP1, item.getComprimento1());
        values.put(KEY_COMP2, item.getComprimento2());
        values.put(KEY_RODO, item.getRodo());
        values.put(KEY_DIAM1, item.getDiametro1());
        values.put(KEY_DIAM2, item.getDiametro2());
        values.put(KEY_OCO, item.getOco());
        values.put(KEY_PATIO, item.getPatioId());
        values.put(KEY_ARVORE, item.getArvoreId());

        // Inserting Row
        db.insert(TABLE_PATIO_ITEM, null, values);
        db.close(); // Closing database connection
    }
}
