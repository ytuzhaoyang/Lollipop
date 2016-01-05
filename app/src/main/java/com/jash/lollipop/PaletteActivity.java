package com.jash.lollipop;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;


public class PaletteActivity extends Activity implements View.OnClickListener {
    public static String name = "调色版";
    private ImageView image;
    private CardView[] cardViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        image = ((ImageView) findViewById(R.id.image));
//        image.setOnClickListener(this);
        cardViews = new CardView[]{
                (CardView) findViewById(R.id.card_1),
                (CardView) findViewById(R.id.card_2),
                (CardView) findViewById(R.id.card_3),
                (CardView) findViewById(R.id.card_4),
                (CardView) findViewById(R.id.card_5),
                (CardView) findViewById(R.id.card_6),
        };
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        setBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "调色版"), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            Uri uri = data.getData();
            ContentResolver cr = getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                setBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void setBitmap(Bitmap bitmap) {
        image.setImageBitmap(bitmap);
        //通过bitmap获得调色板对象
        Palette palette = Palette.from(bitmap).generate();
        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
        if (vibrantSwatch != null) {
            cardViews[0].setCardBackgroundColor(vibrantSwatch.getRgb());
        } else {
            cardViews[0].setCardBackgroundColor(Color.WHITE);
        }
        Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
        if (darkVibrantSwatch != null) {
            cardViews[1].setCardBackgroundColor(darkVibrantSwatch.getRgb());
        } else {
            cardViews[1].setCardBackgroundColor(Color.WHITE);
        }
        Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
        if (lightVibrantSwatch != null) {
            cardViews[2].setCardBackgroundColor(lightVibrantSwatch.getRgb());
        } else {
            cardViews[2].setCardBackgroundColor(Color.WHITE);
        }
        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
        if (mutedSwatch != null) {
            cardViews[3].setCardBackgroundColor(mutedSwatch.getRgb());
        } else {
            cardViews[3].setCardBackgroundColor(Color.WHITE);
        }
        Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
        if (darkMutedSwatch != null) {
            cardViews[4].setCardBackgroundColor(darkMutedSwatch.getRgb());
        } else {
            cardViews[4].setCardBackgroundColor(Color.WHITE);
        }
        Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
        if (lightMutedSwatch != null) {
            cardViews[5].setCardBackgroundColor(lightMutedSwatch.getRgb());
        } else {
            cardViews[5].setCardBackgroundColor(Color.WHITE);
        }
    }
}
