package com.guilherme.almeirim.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guilherme.almeirim.DB.Models.PatioItemModel;
import com.guilherme.almeirim.DB.Models.TranporteItemModel;

/**
 * Created by Guilherme on 20/10/2016.
 */
public class DBTransporteItem extends SQLiteOpenHelper {

    private final String TABLE_TRANSPORTE_ITEM = "transporte_table_item";

    private final String KEY_ID = "id";
    private final String KEY_TRANSPORTE = "id_transporte";
    private final String KEY_ARVORE = "arvore";

    public DBTransporteItem(Context context) {
        super(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addItem(TranporteItemModel item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, item.getId());
        values.put(KEY_ARVORE, item.getArvoreId());
        values.put(KEY_TRANSPORTE, item.getTransporteId());

        // Inserting Row
        db.insert(TABLE_TRANSPORTE_ITEM, null, values);
        db.close(); // Closing database connection
    }
}
