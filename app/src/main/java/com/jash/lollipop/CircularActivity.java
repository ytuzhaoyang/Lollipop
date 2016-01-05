package com.jash.lollipop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;


public class CircularActivity extends Activity implements View.OnClickListener {
    public static String name = "波纹动画";
    private CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);
        findViewById(R.id.show).setOnClickListener(this);
        findViewById(R.id.hide).setOnClickListener(this);
        card = ((CardView) findViewById(R.id.card));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show:
            {
                int cx = card.getWidth() / 2;
                int cy = card.getHeight() / 2;

                int finalRadius = Math.max(card.getWidth(), card.getHeight()) / 2;

                Animator anim =
                        ViewAnimationUtils.createCircularReveal(card, cx, cy, 0, finalRadius);
                anim.setDuration(2000);
                card.setVisibility(View.VISIBLE);
                anim.start();
            }
                break;
            case R.id.hide:
            {
                int cx = card.getWidth() / 2;
                int cy = card.getHeight() / 2;

                int initialRadius = Math.max(card.getWidth(), card.getHeight()) / 2;

                Animator anim =
                        ViewAnimationUtils.createCircularReveal(card, cx, cy, initialRadius, 0);

                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        card.setVisibility(View.INVISIBLE);
                    }
                });
                anim.setDuration(2000);
                anim.start();
            }
                break;
        }
    }

}
