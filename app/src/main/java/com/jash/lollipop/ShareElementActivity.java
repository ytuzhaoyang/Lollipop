package com.jash.lollipop;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


public class ShareElementActivity extends Activity implements View.OnClickListener {
    public static String name = "共享元素";
    private RelativeLayout l;
    private String[] stringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element);
        l = ((RelativeLayout) findViewById(R.id.layout));
        l.setOnClickListener(this);
        stringArray = getResources().getStringArray(R.array.image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout:
                Pair<View, String>[] pairs = new Pair[stringArray.length];
                for (int i = 0; i < stringArray.length; i++) {
                    String s = stringArray[i];
                    View childAt = l.getChildAt(i);
                    pairs[i] = Pair.create(childAt, s);
                }
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
                startActivity(new Intent(this, ElementActivity.class), options.toBundle());
                break;
        }
    }
}
