package com.jash.lollipop;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class RecyclerActivity extends Activity implements AdapterView.OnItemSelectedListener, SwipeRefreshLayout.OnRefreshListener, Handler.Callback {
    public static String name = "RecyclerView";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager[] layoutManagers;
    private SwipeRefreshLayout swipe;
    private RAdapter adapter;
    private String[] list;
    private Handler handler = new Handler(this);
    private RecyclerView.ItemAnimator[] itemAnimators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Spinner layout = (Spinner) findViewById(R.id.layout);
        layout.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"线性", "表格", "瀑布流"}));
        layout.setOnItemSelectedListener(this);
        Spinner anim = (Spinner) findViewById(R.id.anim);
        anim.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"淡入淡出", "左进右出", "百叶窗", "放大缩小"}));
        anim.setOnItemSelectedListener(this);
        recyclerView = ((RecyclerView) findViewById(R.id.recycler));
        layoutManagers = new RecyclerView.LayoutManager[]{
                new LinearLayoutManager(this),
                new GridLayoutManager(this, 3),
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL),
        };
        itemAnimators = new RecyclerView.ItemAnimator[]{
                new FadeItemAnimator(),
                new LiroItemAnimator(),
                new ShadesItemAnimator(),
                new ScaleItemAnimator(),
        };
        recyclerView.setLayoutManager(layoutManagers[0]);
        recyclerView.setItemAnimator(itemAnimators[0]);
        try {
            list = getAssets().list("");
            adapter = new RAdapter(this, new ArrayList<>(Arrays.asList(list)));
            recyclerView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        swipe = ((SwipeRefreshLayout) findViewById(R.id.swipe));
        swipe.setOnRefreshListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.layout:
                recyclerView.setLayoutManager(layoutManagers[position]);
                break;
            case R.id.anim:
                recyclerView.setItemAnimator(itemAnimators[position]);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onRefresh() {
        adapter.clear();
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case 0:
                adapter.addAll(Arrays.asList(list));
                swipe.setRefreshing(false);
                break;
        }
        return true;
    }
}
