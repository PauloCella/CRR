package com.example.uno_2543.crr.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.uno_2543.crr.Database.DBControle;
import com.example.uno_2543.crr.Database.DBCore;
import com.example.uno_2543.crr.Models.ControleAplicacao;

/**
 * Created by uno-2543 on 29/02/16.
 */
public class ControleService extends Service {
    @Nullable

    @Override
    public void onCreate(){

    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void salvarControle(ControleAplicacao controleAplicacao){
        DBControle db = new DBControle(getApplicationContext());

        db.addControle(controleAplicacao);


    }

}
