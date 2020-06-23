package com.example.sqlitevannien.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlitevannien.model.BoiBaiModel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QuangND on 6/13/2020.
 */
public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "lichvannien.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void copyDatabase() throws IOException {
        //  SQLiteDatabase checkDB = SQLiteDatabase.openDatabase(context.getAssets().+(DATABASE_NAME), null, SQLiteDatabase.OPEN_READONLY);
        //  checkDB.close();
        InputStream myInput = context.getAssets().open(DATABASE_NAME);
        String outFileName =  "/data/data/"+ context.getPackageName() + "/databases/" + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public List<BoiBaiModel> getBoiBaiModels(SQLiteDatabase database)
    {
        List<BoiBaiModel> boiBaiModels = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM 'boi_bai'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            BoiBaiModel boiBaiModel = new BoiBaiModel();
            boiBaiModel.setLabai(cursor.getString(0));
            boiBaiModel.setNghia(cursor.getString(1));

            boiBaiModels.add(boiBaiModel);

            cursor.moveToNext();
        }

        cursor.close();
        return boiBaiModels;
    }
}
