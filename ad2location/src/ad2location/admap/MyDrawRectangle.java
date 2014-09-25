package ad2location.admap;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyDrawRectangle extends View {
    private Paint   mPaint = new Paint();
    private float[] mPts = new float[1000];

    public MyDrawRectangle(Context context) {
        super(context);
        
        Random rand = new Random();
        final int N = mPts.length >> 1;
        for (int i = 0; i < N; i++) {
            mPts[(i << 1) + 0] = rand.nextFloat() * 320;    // X
            mPts[(i << 1) + 1] = rand.nextFloat() * 240;    // Y
        }
    }
    
    @Override protected void onDraw(Canvas canvas) {
        Paint paint = mPaint;

        canvas.drawColor(Color.WHITE);
        
        paint.setAntiAlias(true);

        // ROUND cap + width > 0 ... Squares (pretty slow)
        paint.setColor(Color.BLUE);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStrokeWidth(10);
        canvas.drawPoints(mPts, paint);

        // SQUARE cap + width > 0 ... Circles (very slow)
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(6);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoints(mPts, paint);

        // antialias + width == 0 ... blurry pixels (pretty fast)
        paint.setColor(Color.RED);
        paint.setStrokeWidth(0);
        canvas.drawPoints(mPts, paint);

        // no-antialias + width == 0 ... single pixels (very fast)
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(false);
        canvas.drawPoints(mPts, paint);
    }
}
