package com.example.uno_2543.crr.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.uno_2543.crr.Models.ControleAplicacao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uno-2543 on 01/03/16.
 */
public class DBControle extends DBCore {

    private static final String DATABASE_NAME = "ControleManager";

    // Contacts table name
    private static final String TABLE_CONTROLE = "controle";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DESCRICAO = "descricao";
    private static final String KEY_TIPO = "tipo";
    private static final String KEY_DURACAO = "duracao";
    private static final String KEY_DATA_VALIDADE = "data_validade";
    private static final String KEY_SOM = "som";


    public DBControle(Context context) {
        super(context);
    }

    public void addControle(ControleAplicacao controleAplicacao) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESCRICAO, controleAplicacao.getDescricao());
        values.put(KEY_TIPO, controleAplicacao.getTipo());
        values.put(KEY_DURACAO, controleAplicacao.getDuracao());
        values.put(KEY_DATA_VALIDADE, String.valueOf(controleAplicacao.getValidade_produto()));
        values.put(KEY_SOM, controleAplicacao.getSom()); // Contact Phone

        // Inserting Row
        try {
            db.insert(TABLE_CONTROLE, null, values);
        }catch (Exception e){
            Log.d("BANCO","Erro ao salvar no banco de dados "+ e );
        }
        db.close(); // Closing database connection
    }


    // Getting All Contacts
    public List<ControleAplicacao> getAllControles() throws ParseException {
        List<ControleAplicacao> controlList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTROLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ControleAplicacao controleAplicacao = new ControleAplicacao();
                controleAplicacao.set_id(Integer.parseInt(cursor.getString(0)));
                controleAplicacao.setDescricao(cursor.getString(1));
                controleAplicacao.setTipo(cursor.getString(2));
                controleAplicacao.setDuracao(Integer.valueOf(cursor.getString(3)));
                controleAplicacao.setTipo_notificacao(5);
                controleAplicacao.setValidade_produto(cursor.getString(4));
                controleAplicacao.setSom("minha musica");
                controlList.add(controleAplicacao);
            } while (cursor.moveToNext());
        }

        // return contact list
        return controlList;
    }


    /*/ Getting single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }

    */


/*
    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    */


}
