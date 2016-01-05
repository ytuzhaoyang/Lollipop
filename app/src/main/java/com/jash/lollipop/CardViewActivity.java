package com.jash.lollipop;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.SeekBar;


public class CardViewActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    public static String name = "卡片视图";
    private CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);
        card = ((CardView) findViewById(R.id.card));
//        card.setOnClickListener(v -> Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show());
        ((SeekBar) findViewById(R.id.radius)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.elevation)).setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.radius:
                int i = Math.min(card.getWidth(), card.getHeight()) / 2;
                //圆角
                card.setRadius(progress * i / seekBar.getMax());
                break;
            case R.id.elevation:
                //海拔
                card.setCardElevation(progress / 10);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
