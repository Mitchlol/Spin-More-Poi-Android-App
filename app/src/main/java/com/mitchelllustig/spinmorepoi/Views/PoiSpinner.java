package com.mitchelllustig.spinmorepoi.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.mitchelllustig.spinmorepoi.R;

/**
 * Created by Mitch on 4/16/15.
 */
public class PoiSpinner extends View {
    Context context;
    Bitmap dude, poi;

    Rect dudeSourceRect, poiRightSourceRect, poiLeftSourceRect;

    int angleRight, angleLeft;

    public PoiSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inSampleSize = 4;

        dude = BitmapFactory.decodeResource(getResources(), R.drawable.smp_dude, options);
        poi = BitmapFactory.decodeResource(getResources(), R.drawable.smp_poi, options);

        dudeSourceRect = new Rect(0,0,dude.getWidth(),dude.getHeight());
        poiLeftSourceRect = new Rect(0,0,poi.getWidth(),poi.getHeight());
        poiRightSourceRect = new Rect(poi.getWidth(),0,0,dude.getHeight());

        angleRight = 0;
        angleLeft = 180;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int centerX = width/2;
        int centerY = height/2;


        canvas.drawColor(getResources().getColor(R.color.dark));

        float dudeScale = ((float)height) / dude.getHeight();
        int dudeDestHeight = height;
        int dudeDestWidth = (int)(dude.getWidth() * dudeScale);
        int dudeWidthOffset = dudeDestWidth/2;
        Rect dudeDestRect = new Rect(centerX-dudeWidthOffset, 0, centerX+dudeWidthOffset, height);

        canvas.drawBitmap(dude, dudeSourceRect, dudeDestRect, new Paint());

        canvas.save();
        canvas.translate(centerX, 0);
        canvas.save();
        canvas.translate((int)(dudeDestHeight * -.245), (int)(dudeDestHeight * .61));

        int poiDstWidth = (int)(poi.getWidth() * dudeScale);
        int poiDstHeight = (int)(poi.getHeight() * dudeScale);
        Rect poiDestRect = new Rect(0, 0, poiDstWidth, poiDstHeight);

        angleLeft = angleLeft + 2 % 360;
        canvas.rotate(angleLeft, 0 + (int)(poiDstWidth * .60), 0 + (int) (poiDstHeight * .04));

        canvas.drawBitmap(poi, poiLeftSourceRect, poiDestRect, new Paint());

        canvas.restore();
        canvas.translate((int)((dudeDestHeight * .155)), (int)(dudeDestHeight * .61));

        angleRight = angleRight + -2 % 360;
        canvas.rotate(angleRight, 0 + (int)(poiDstWidth * .45), 0 + (int) (poiDstHeight * .04));

        canvas.drawBitmap(poi, poiRightSourceRect, poiDestRect, new Paint());
        canvas.restore();

        invalidate();
    }
}
