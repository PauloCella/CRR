package com.example.uno_2543.crr.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.uno_2543.crr.Models.ControleAplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by uno-2543 on 26/02/16.
 */
public class DBCore extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "ControleManager";

    // Contacts table name
    private static final String TABLE_CONTROLE = "controle";

    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_DESCRICAO = "descricao";
    private static final String KEY_TIPO = "tipo";
    private static final String KEY_DURACAO = "duracao";
    private static final String KEY_DATA_VALIDADE = "data_validade";
    private static final String KEY_SOM = "som";

    public DBCore(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTROLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DESCRICAO + " TEXT,"
                + KEY_TIPO + " TEXT,"
                + KEY_DURACAO + " INTEGER,"
                + KEY_DATA_VALIDADE + " TEXT,"
                + KEY_SOM + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTROLE);

        // Create tables again
        onCreate(db);
    }



}