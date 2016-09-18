package com.guilherme.almeirim.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Guilherme on 14/09/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_PROJECT = "project_id";

    // Table Projects
    private static final String TABLE_PROJECTS = "projects_table";
    private static final String KEY_NAME = "name";
    private static final String CREATE_TABLE_PROJECTS = "CREATE TABLE " + TABLE_PROJECTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT)";

    // Table Patio
    private static final String TABLE_PATIO = "patio_table";
    private static final String KEY_DATA = "data";
    private static final String KEY_ROMANEIO = "romaneio";
    private static final String KEY_EMPLOYEE = "funcionario";
    private static final String CREATE_TABLE_PATIO = "CREATE TABLE " + TABLE_PATIO + "(" +
            KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_PROJECT + " INTEGER, " +
            KEY_DATA + " TEXT, " +
            KEY_ROMANEIO + " INTEGER, " +
            KEY_EMPLOYEE + " TEXT" +
            ")";


    // Table Transporte
    private static final String TABLE_TRANSPORTE = "transporte_table";
    private static final String KEY_CLIENTE = "cliente";
    private static final String KEY_ID_ROMANEIO = "id_romaneio";
    private static final String KEY_MOTORISTA = "motorista";
    private static final String KEY_PLACA = "placa";
    private static final String CREATE_TABLE_TRANSPORTE = "CREATE TABLE " + TABLE_TRANSPORTE + "(" +
            KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_PROJECT + " INTEGER, " +
            KEY_ID_ROMANEIO + " INTEGER, " +
            KEY_MOTORISTA + " TEXT, " +
            KEY_PLACA + " TEXT, " +
            KEY_CLIENTE + " TEXT" +
            ")";

    // Table arvore
    private static final String TABLE_ARVORE = "arvore_table";
    private static final String KEY_UT = "ut";
    private static final String KEY_CAP = "cap";
    private static final String KEY_ESPECIE = "especie";
    String CREATE_TABLE_ARVORE = "CREATE TABLE " + TABLE_ARVORE + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_PLACA + " REAL, " +
            KEY_UT + " REAL, " +
            KEY_CAP + " REAL, " +
            KEY_ESPECIE + " REAL, " +
            KEY_PROJECT + " REAL " +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PATIO);
        db.execSQL(CREATE_TABLE_PROJECTS);
        db.execSQL(CREATE_TABLE_TRANSPORTE);
        db.execSQL(CREATE_TABLE_ARVORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARVORE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSPORTE);

        // create new tables
        onCreate(db);
    }

}
