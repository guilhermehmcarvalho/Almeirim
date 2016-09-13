package com.guilherme.almeirim.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guilherme.almeirim.DB.Models.ArvoreModel;

/**
 * Created by Guilherme on 11/09/2016.
 */
public class DBArvore extends SQLiteOpenHelper {

    private static final String TABLE_ARVORE = "arvore_table";

    private static final String KEY_ID = "id";
    private static final String KEY_PLACA = "placa";
    private static final String KEY_UT = "ut";
    private static final String KEY_CAP = "cap";
    private static final String KEY_ESPECIE = "especie";
    private static final String KEY_PROJETO = "projeto";

    public DBArvore(Context context) {
        super(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = "CREATE TABLE " + TABLE_ARVORE + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_PLACA + " REAL, " +
                KEY_UT + " REAL, " +
                KEY_CAP + " REAL, " +
                KEY_ESPECIE + " REAL, " +
                KEY_PROJETO + " REAL " +
                ")";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARVORE);
        // Creating tables again
        onCreate(db);
    }

    public void addItem(ArvoreModel item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, item.getId());
        values.put(KEY_PLACA, item.getPlaca());
        values.put(KEY_UT, item.getUT());
        values.put(KEY_CAP, item.getCap());
        values.put(KEY_ESPECIE, item.getEspecie());
        values.put(KEY_PROJETO, item.getIdProjeto());

        // Inserting Row
        db.insert(TABLE_ARVORE, null, values);
        db.close(); // Closing database connection
    }
}
