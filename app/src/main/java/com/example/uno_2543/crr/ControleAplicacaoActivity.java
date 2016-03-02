package com.example.uno_2543.crr;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.uno_2543.crr.Database.DBControle;
import com.example.uno_2543.crr.Database.DBCore;
import com.example.uno_2543.crr.Models.ControleAplicacao;

public class ControleAplicacaoActivity extends AppCompatActivity {
        private EditText etDescricao, etDuracao, etValidade;
        private Spinner spinner;
        private RadioGroup radioGroup;
        private int notifi = 0;
        private FloatingActionButton btSalvar, btCancelar;
        private String tipo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_aplicacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = (Spinner) findViewById(R.id.spinner);

        tipo = spinner.getSelectedItem().toString();

        etValidade = (EditText) findViewById(R.id.etValidade);
        etDescricao = (EditText) findViewById(R.id.etDescricao);
        etDuracao = (EditText) findViewById(R.id.etDuracao);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rd_notificacao){
                    notifi = 0;
                }
                if(checkedId == R.id.rd_toque){
                    notifi = 1;
                }
            }
        });

        btCancelar = (FloatingActionButton) findViewById(R.id.fabCancell);
        btSalvar = (FloatingActionButton) findViewById(R.id.fabSalvar);

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finalizarActivity();
            }
        });


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveControle();
            }
        });

    }

    public void finalizarActivity(){
        Toast.makeText(getApplicationContext(), " Cadastro cancelado com sucesso!",Toast.LENGTH_SHORT).show();
        callHorarios();
        this.finish();
    }

    public void saveControle(){
        int duracao = 0;
        ControleAplicacao controleAplicacao = new ControleAplicacao();
        controleAplicacao.setDescricao(etDescricao.getText().toString());
       /* if(Integer.valueOf((etDuracao.getText().toString())).equals(0)){
            duracao = 0;
        }else{
            duracao = Integer.valueOf((etDuracao.getText().toString()));
        }
*/
        try {
            duracao = Integer.parseInt(etDuracao.getText().toString());
        }catch (Exception e){

        }
        controleAplicacao.setDuracao(duracao);
        controleAplicacao.setTipo_notificacao(notifi);
        controleAplicacao.setTipo(tipo);
        controleAplicacao.setValidade_produto(etValidade.getText().toString());

        if(!controleAplicacao.getDuracao().equals(0)){

            try{
                DBControle db = new DBControle(getApplicationContext());

                db.addControle(controleAplicacao);
                Toast.makeText(getApplicationContext(),"Controle cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                callHorarios();
                this.finish();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Erro ao salvar cadastro: "+ e.toString(), Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(getApplicationContext(), " Por favor preencha todos os campos.", Toast.LENGTH_SHORT).show();
            closeContextMenu();
        }


    }

    public void callHorarios(){
        Intent i = new Intent(this, HorariosActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_controle_aplicacao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == 16908332){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
