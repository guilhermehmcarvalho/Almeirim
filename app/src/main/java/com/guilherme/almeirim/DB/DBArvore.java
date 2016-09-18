package com.guilherme.almeirim.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guilherme.almeirim.DB.Models.ArvoreModel;

import java.util.ArrayList;
import java.util.List;

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
    private static final String KEY_PROJECT = "project_id";

    public DBArvore(Context context) {
        super(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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

        values.put(KEY_PLACA, item.getPlaca());
        values.put(KEY_UT, item.getUT());
        values.put(KEY_CAP, item.getCap());
        values.put(KEY_ESPECIE, item.getEspecie());
        values.put(KEY_PROJECT, item.getIdProjeto());

        // Inserting Row
        db.insert(TABLE_ARVORE, null, values);
        db.close(); // Closing database connection
    }

    public List<ArvoreModel> getAllArvores(int projectId) {
        List<ArvoreModel> arvoreList = new ArrayList<ArvoreModel>();
        // Select All Query
        try {
            String selectQuery = "SELECT * FROM " + TABLE_ARVORE +
                    " WHERE " + KEY_PROJECT + " = " + projectId;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    ArvoreModel arvore = new ArvoreModel();
                    arvore.setId(Integer.parseInt(cursor.getString(0)));
                    arvore.setPlaca(Integer.parseInt(cursor.getString(1)));
                    arvore.setUT(Integer.parseInt(cursor.getString(2)));
                    arvore.setCap(Integer.parseInt(cursor.getString(3)));
                    arvore.setEspecie(cursor.getString(4));
                    arvore.setIdProjeto(Integer.parseInt(cursor.getString(5)));

                    arvoreList.add(arvore);
                } while (cursor.moveToNext());
            }
        }catch (Exception e) {}

        // return contact list
        return arvoreList;
    }
}
