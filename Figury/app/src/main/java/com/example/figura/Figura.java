package com.example.figura;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class Figura extends View {
    public Figura(Context context) {
        super(context);
    }

    public Figura(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Figura(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Figura(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onDraw(Canvas canvas){

        int width = getWidth();
        int width_ = width/2;

        int height = getHeight();

        int size = (width_ < height ? width_ : height) - 10;

        int x, y, dx, dy;

        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);

        Random r = new Random();

        p.setColor(Color.GRAY);
        canvas.drawRect(0, 0, width-1, height-1, p);

        CharSequence opis = getContentDescription();
        if(opis==null){
            opis = "Brak";
        }
        for(int i = 0; i<10; i++){
            p.setARGB(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
            if(opis.equals("koło")){
                dx = r.nextInt(size);
                x = r.nextInt(width_ - dx);
                y = r.nextInt(  height - dx);
                canvas.drawCircle(x, y, dx, p);
            }
            else if (opis.equals("elipsa")){
                dx = r.nextInt(size);
                dy = r.nextInt(size);
                x = r.nextInt(width_ - dx);
                y = r.nextInt(height - dy);
                RectF rect = new RectF(x, y, x+ dx, y + dx);
                canvas.drawOval(rect, p);
            }
            else if (opis.equals("prostokąt")){
                dx = r.nextInt(size);
                dy = r.nextInt(size);
                x = r.nextInt(width_ - dx);
                y = r.nextInt(height - dy);

                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawRect(rect, p);
            }
            else if (opis.equals("prostokąt okrągły")){
                dx = r.nextInt(size);
                dy = r.nextInt(size);
                x = r.nextInt(width_ - dx);
                y = r.nextInt(height - dy);

                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawRoundRect(rect, 10, 10, p);
            }
            else if (opis.equals("łuk")){
                dx = r.nextInt(size);
                dy = r.nextInt(size);
                x = r.nextInt(width_ - dx);
                y = r.nextInt(height - dy);

                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawArc(rect, r.nextInt(360), r.nextInt(360), false, p);
            }
            else if (opis.equals("linia")){
                dx = r.nextInt(width_);
                dy = r.nextInt(height);
                x = r.nextInt(width_);
                y = r.nextInt(height);

                canvas.drawLine(x, y, dx, dy, p);
            }
            int dp = (int)getResources().getDimension(R.dimen.wys_napisu);
            p.setTextSize(dp);
            p.setTextAlign(Paint.Align.RIGHT);
            p.setColor(Color.BLUE);
            canvas.drawText((String) opis, width - 20, height/2, p);

            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(1);
            canvas.drawRect(0, 0, width-1, height-1, p);

            super.onDraw(canvas);

        }

    }
}
