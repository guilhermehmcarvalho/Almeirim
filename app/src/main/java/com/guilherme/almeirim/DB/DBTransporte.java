package com.guilherme.almeirim.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guilherme.almeirim.DB.Models.TransporteModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 15/09/2016.
 */
public class DBTransporte extends SQLiteOpenHelper {

    private static final String TABLE_TRANSPORTE = "transporte_table";
    private static final String KEY_CLIENTE = "cliente";
    private static final String KEY_ID_ROMANEIO = "id_romaneio";
    private static final String KEY_MOTORISTA = "motorista";
    private static final String KEY_PLACA = "placa";
    private static final String KEY_PROJECT = "project_id";

    public DBTransporte(Context context) {
        super(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION);
    }

    public void addTransporte(TransporteModel transp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_CLIENTE, transp.getCliente());
        values.put(KEY_ID_ROMANEIO, transp.getIdRomaneio());
        values.put(KEY_MOTORISTA, transp.getMotorista());
        values.put(KEY_PLACA, transp.getPlaca());
        values.put(KEY_PROJECT, transp.getProjectId());

        // Inserting Row
        db.insert(TABLE_TRANSPORTE, null, values);
        db.close(); // Closing database connection
    }

    public List<TransporteModel> getAllTransporte(int projectId) {
        List<TransporteModel> transpList = new ArrayList<TransporteModel>();
        // Select All Query
        try {
            String selectQuery = "SELECT * FROM " + TABLE_TRANSPORTE +
                    " WHERE " + KEY_PROJECT + " = " + projectId;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    TransporteModel transp = new TransporteModel();
                    transp.setId(Integer.parseInt(cursor.getString(0)));
                    transp.setCliente(cursor.getString(1));
                    transp.setIdRomaneio(Integer.parseInt(cursor.getString(2)));
                    transp.setMotorista(cursor.getString(3));
                    transp.setPlaca(cursor.getString(4));
                    transp.setProjectId(Integer.parseInt(cursor.getString(5)));

                    transpList.add(transp);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
        }

        return transpList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
