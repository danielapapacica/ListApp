package com.example.dana.listapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int index = intent.getIntExtra("com.example.dana.listapp.ITEM_INDEX", -1);

        if(index > -1){
            int pic = getImage(index);
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            scaleImage(imageView, pic);
        }
    }

    private int getImage(int index){
        switch (index){
            case 0: return R.drawable.tomato;
            case 1: return R.drawable.peach;
            case 2: return R.drawable.carrot;
            case 3: return R.drawable.banana;
            default: return -1;
        }
    }

    private void scaleImage(ImageView img, int pic){
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();
        if(imgWidth > screenWidth){
            int ratio = Math.round((float)imgWidth/(float)screenWidth);
            options.inSampleSize = ratio;
        }
        options.inJustDecodeBounds = false;

        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);
    }
}
