package com.example.przeksztalcenie;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView uri = (TextView) findViewById(R.id.uri);
        int[] size = new int[2];

        Button pobierz = (Button) findViewById(R.id.pobierz);
        ImageView goofyCat = (ImageView) findViewById(R.id.goofy);
        Bitmap bitmap = ((BitmapDrawable) goofyCat.getDrawable()).getBitmap();
        int bitW = bitmap.getWidth();
        int bitH = bitmap.getHeight();
        goofyCat.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
              size[0] = goofyCat.getWidth();
              size[1] = goofyCat.getHeight();

            }
        });




        pobierz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    uri.setText("ImageView: " + size[0] + " x " + size[1] + ", bitmapa: " + bitW + " x " + bitH);
                    goofyCat.setVisibility(View.VISIBLE);
            }
        });

        Button przeksztalc = (Button) findViewById(R.id.przeksztalc);
        przeksztalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goofyCat.setDrawingCacheEnabled(true);
                Bitmap originalBitmap = goofyCat.getDrawingCache();

                Bitmap shuffledBitmap = shufflePixels(originalBitmap);
                goofyCat.setImageBitmap(shuffledBitmap);
            }
        });


    }
    public static Bitmap shufflePixels(Bitmap bitmap) {
        Random random = new Random();
        Bitmap shuffledBitmap = bitmap.copy(bitmap.getConfig(), true);

        for (int y = 0; y < bitmap.getHeight(); y++) {
            for (int x = 0; x < bitmap.getWidth(); x++) {

                int color = bitmap.getPixel(x, y);

                int deltaX = random.nextInt(21) - 10;
                int deltaY = random.nextInt(21) - 10;


                int newX = Math.max(0, Math.min(bitmap.getWidth() - 1, x + deltaX));
                int newY = Math.max(0, Math.min(bitmap.getHeight() - 1, y + deltaY));


                shuffledBitmap.setPixel(newX, newY, color);
            }
        }

        return shuffledBitmap;
    }
}