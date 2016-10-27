package com.guilherme.almeirim.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Guilherme on 14/09/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Common column names
    private final String KEY_ID = "id";
    private final String KEY_PROJECT = "project_id";
    private final String KEY_ARVORE = "arvore";

    // Table Projects
    private final String TABLE_PROJECTS = "projects_table";
    private final String KEY_NAME = "name";
    private final String CREATE_TABLE_PROJECTS = "CREATE TABLE " + TABLE_PROJECTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT)";

    // Table Patio
    private final String TABLE_PATIO = "patio_table";
    private final String KEY_DATA = "data";
    private final String KEY_ROMANEIO = "romaneio";
    private final String KEY_EMPLOYEE = "funcionario";
    private final String CREATE_TABLE_PATIO = "CREATE TABLE " + TABLE_PATIO + "(" +
            KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_PROJECT + " INTEGER, " +
            KEY_DATA + " TEXT, " +
            KEY_ROMANEIO + " INTEGER UNIQUE NOT NULL, " +
            KEY_EMPLOYEE + " TEXT" +
            ")";


    // Table Transporte
    private final String TABLE_TRANSPORTE = "transporte_table";
    private final String KEY_CLIENTE = "cliente";
    private final String KEY_ID_ROMANEIO = "id_romaneio";
    private final String KEY_MOTORISTA = "motorista";
    private final String KEY_PLACA = "placa";
    private final String CREATE_TABLE_TRANSPORTE = "CREATE TABLE " + TABLE_TRANSPORTE + "(" +
            KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_PROJECT + " INTEGER, " +
            KEY_ID_ROMANEIO + " INTEGER UNIQUE NOT NULL, " +
            KEY_MOTORISTA + " TEXT, " +
            KEY_PLACA + " TEXT, " +
            KEY_CLIENTE + " TEXT" +
            ")";

    // Table arvore
    private final String TABLE_ARVORE = "arvore_table";
    private final String KEY_UT = "ut";
    private final String KEY_CAP = "cap";
    private final String KEY_ESPECIE = "especie";
    String CREATE_TABLE_ARVORE = "CREATE TABLE " + TABLE_ARVORE + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_PLACA + " REAL UNIQUE NOT NULL, " +
            KEY_UT + " REAL, " +
            KEY_CAP + " REAL, " +
            KEY_ESPECIE + " REAL, " +
            KEY_PROJECT + " REAL " +
            ")";

    // Table patio item
    private final String TABLE_PATIO_ITEM = "patio_table_item";
    private final String KEY_COMP1 = "comprimento1";
    private final String KEY_COMP2 = "comprimento2";
    private final String KEY_RODO = "rodo";
    private final String KEY_DIAM1 = "diametro1";
    private final String KEY_DIAM2 = "diametro2";
    private final String KEY_OCO = "oco";
    private final String KEY_PATIO = "patio";

    String CREATE_TABLE_PATIO_ITEM = "CREATE TABLE " + TABLE_PATIO_ITEM + "(" +
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

    // Table transporte item
    private final String TABLE_TRANSPORTE_ITEM = "transporte_table_item";
    private final String KEY_TRANSPORTE = "id_transporte";
    String CREATE_TABLE_TRANSPORTE_ITEM = "CREATE TABLE " + TABLE_TRANSPORTE_ITEM + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_ARVORE + "INTEGER, " +
            KEY_TRANSPORTE + "INTEGER " +
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
        db.execSQL(CREATE_TABLE_PATIO_ITEM);
        db.execSQL(CREATE_TABLE_TRANSPORTE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARVORE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSPORTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIO_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSPORTE_ITEM);

        // create new tables
        onCreate(db);
    }

}
