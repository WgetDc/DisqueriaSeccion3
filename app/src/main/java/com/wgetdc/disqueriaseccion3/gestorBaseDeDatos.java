package com.wgetdc.disqueriaseccion3;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class gestorBaseDeDatos extends SQLiteOpenHelper {
    String crear_tabla_discos = "CREATE TABLE discos(id int primary key, artista text, album text, fecha text)";

    public gestorBaseDeDatos(@Nullable Context context, @Nullable String name,
                             @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crear_tabla_discos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
