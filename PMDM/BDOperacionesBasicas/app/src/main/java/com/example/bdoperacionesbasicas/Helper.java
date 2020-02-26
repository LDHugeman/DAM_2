package com.example.bdoperacionesbasicas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {

    private String sqlCreate = "CREATE TABLE usuarios (codigo INTEGER PRIMARY KEY, " +
            "nombre VARCHAR(20))";
    private String sqlDrop = "DROP TABLE IF EXISTS usuarios";
    public Helper(@Nullable Context context,
                  @Nullable String name,
                  @Nullable SQLiteDatabase.CursorFactory factory,
                  int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDrop);
    }
}
