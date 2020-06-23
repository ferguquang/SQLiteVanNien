package com.example.sqlitevannien;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.sqlitevannien.database.DataBaseHelper;
import com.example.sqlitevannien.database.DatabaseAccess;
import com.example.sqlitevannien.database.DatabaseOpenHelper;
import com.example.sqlitevannien.model.BoiBaiModel;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DatabaseAccess.getInstance(getApplicationContext()).open();
//        List<BoiBaiModel> boiBaiModels = DatabaseAccess.getInstance(getApplicationContext()).getBoiBaiModels();

//        DatabaseOpenHelper databaseHelper = new DatabaseOpenHelper(this);
//        SQLiteDatabase database = databaseHelper.getReadableDatabase();
//        try {
//            databaseHelper.copyDatabase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        List<BoiBaiModel> boiBaiModels = databaseHelper.getBoiBaiModels(database);

        DataBaseHelper dbhelper = new DataBaseHelper(getApplicationContext());

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        try {
            dbhelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<BoiBaiModel> boiBaiModels = dbhelper.getBoiBaiModels(db);

        Log.d("", "");
    }
}