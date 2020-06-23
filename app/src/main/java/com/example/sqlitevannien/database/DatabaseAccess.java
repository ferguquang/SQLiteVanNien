package com.example.sqlitevannien.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlitevannien.model.BoiBaiModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QuangND on 6/13/2020.
 */
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the databases connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public List<BoiBaiModel> getBoiBaiModels()
    {
        List<BoiBaiModel> boiBaiModels = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM 'boi_bai'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            BoiBaiModel boiBaiModel = new BoiBaiModel();
            boiBaiModel.setId(cursor.getInt(0));
            boiBaiModel.setLabai(cursor.getString(1));
            boiBaiModel.setNghia(cursor.getString(2));

            boiBaiModels.add(boiBaiModel);

            cursor.moveToNext();
        }

        cursor.close();
        return boiBaiModels;
    }
}
