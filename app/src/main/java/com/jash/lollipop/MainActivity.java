package com.jash.lollipop;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = ((RecyclerView) findViewById(R.id.recycler));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Class<? extends Activity>> list = new ArrayList<>();
        list.add(CircularActivity.class);
        list.add(CardViewActivity.class);
        list.add(RecyclerActivity.class);
        list.add(PaletteActivity.class);
        list.add(ShareElementActivity.class);
        adapter = new MainAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        Intent intent = new Intent(this, adapter.getItem(position));
        startActivity(intent);
    }
}
