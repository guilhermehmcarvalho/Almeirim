package com.guilherme.almeirim.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guilherme.almeirim.DB.Models.PatioModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 11/09/2016.
 */
public class DBPatio extends SQLiteOpenHelper {

    private static final String TABLE_PATIO = "patio_table";

    private static final String KEY_ID = "id";
    private static final String KEY_DATA = "data";
    private static final String KEY_ROMANEIO = "romaneio";
    private static final String KEY_EMPLOYEE = "funcionario";
    private static final String KEY_PROJECT = "project_id";

    public DBPatio(Context context) {
        super(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = "CREATE TABLE " + DBConfig.DATABASE_NAME + "." + TABLE_PATIO + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_PROJECT + " INTEGER, " +
                KEY_DATA + " TEXT, " +
                KEY_ROMANEIO + " INTEGER, " +
                KEY_EMPLOYEE + " TEXT" +
                ")";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIO);
        // Creating tables again
        onCreate(db);
    }

    // Adding new project
    public void addRomaneio(PatioModel patio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(KEY_ID, patio.getPatioId());
        values.put(KEY_PROJECT, patio.getProjectId());
        values.put(KEY_DATA, patio.getDataDigitacao());
        values.put(KEY_ROMANEIO, patio.getNumRomaneio());
        values.put(KEY_EMPLOYEE, patio.getFuncionario());

        // Inserting Row
        db.insert(TABLE_PATIO, null, values);
        db.close(); // Closing database connection
    }

    public List<PatioModel> getAllRomaneios(int projectId) {
        List<PatioModel> patioList = new ArrayList<PatioModel>();
        // Select All Query
        try {
            String selectQuery = "SELECT * FROM " + TABLE_PATIO +
                    " WHERE " + KEY_PROJECT + " = " + projectId;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    PatioModel patio = new PatioModel();
                    patio.setPatioId(Integer.parseInt(cursor.getString(0)));
                    patio.setProjectId(Integer.parseInt(cursor.getString(1)));
                    patio.setDataDigitacao(cursor.getString(2));
                    patio.setNumRomaneio(Integer.parseInt(cursor.getString(3)));
                    patio.setFuncionario(cursor.getString(4));

                    patioList.add(patio);
                } while (cursor.moveToNext());
            }
        }catch (Exception e) {}

        // return contact list
        return patioList;
    }
}
