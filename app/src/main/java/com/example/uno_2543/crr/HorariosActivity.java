package com.example.uno_2543.crr;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.uno_2543.crr.Adapter.ControlesAdapter;
import com.example.uno_2543.crr.Database.DBControle;
import com.example.uno_2543.crr.Database.DBCore;
import com.example.uno_2543.crr.Interfaces.DividerItemDecoration;
import com.example.uno_2543.crr.Models.ControleAplicacao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class HorariosActivity extends AppCompatActivity {
    private List<ControleAplicacao> movieList = new ArrayList<>();
    private List<ControleAplicacao> controlesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ControlesAdapter mAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        mAdapter = new ControlesAdapter(controlesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        try {
            prepareControleData();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNewControleAplicacao();
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(), controlesList.get(position).get_id() + " is selected!", Toast.LENGTH_SHORT).show();
                //todo criar uma nova activity que pede se quer excluir esse controle ou edita-lo.
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public void callNewControleAplicacao(){
        Intent i = new Intent(this, ControleAplicacaoActivity.class);
        startActivity(i);
        this.finish();
    }


    private void prepareControleData() throws ParseException {
        DBControle db = new DBControle(getApplicationContext());

        movieList =  db.getAllControles();

        for( int i = 0; i < movieList.size();i++){
            ControleAplicacao controleAplicacao = new ControleAplicacao();
            controleAplicacao.setDescricao(movieList.get(i).getDescricao());
            controleAplicacao.set_id(movieList.get(i).get_id());
            controleAplicacao.setSom(movieList.get(i).getSom());
            controleAplicacao.setDuracao(movieList.get(i).getDuracao());
            controleAplicacao.setTipo(movieList.get(i).getTipo());
            controleAplicacao.setTipo_notificacao(movieList.get(i).getTipo_notificacao());
            controleAplicacao.setValidade_produto(movieList.get(i).getValidade_produto());

            controlesList.add(controleAplicacao);
        }
        mAdapter.notifyDataSetChanged();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_horarios, menu);
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
        if (id == 16908332){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}
