package com.guilherme.almeirim.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guilherme.almeirim.DB.Models.ProjectModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 11/09/2016.
 */
public class DBProject extends SQLiteOpenHelper {

    // Contacts table name
    private static final String TABLE_PROJECTS = "projects_table";

    // Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DBProject(Context context) {
        super(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // Adding new project
    public void addProject(ProjectModel project) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, project.getName()); // Project Name
        // Inserting Row
        db.insert(TABLE_PROJECTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Projects
    public List<ProjectModel> getAllProjects() {
        List<ProjectModel> shopList = new ArrayList<ProjectModel>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PROJECTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProjectModel shop = new ProjectModel();
                shop.setId(Integer.parseInt(cursor.getString(0)));
                shop.setName(cursor.getString(1));
                // Adding contact to list
                shopList.add(shop);
            } while (cursor.moveToNext());
        }
        // return contact list
        return shopList;
    }

    public ProjectModel getProjectWithKey(int key) {
        ProjectModel project = new ProjectModel();
        String selectQuery = "SELECT * FROM " + TABLE_PROJECTS +
                " WHERE " + KEY_ID + " = " + key;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                project.setId(Integer.parseInt(cursor.getString(0)));
                project.setName(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return project;
    }
}
