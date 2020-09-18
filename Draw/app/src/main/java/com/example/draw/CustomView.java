package com.example.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CustomView extends View {
    Bitmap memBMP;
    Paint memPaint;
    Canvas memCanvas;
    float oldx;
    float oldy;
    float x;
    float y;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
         y = event.getY();

    switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN://落下
            oldx=x;
            oldy=y;
            break;
                case MotionEvent.ACTION_UP://抬起
                    drawStrokes();
                    break;
                    }
        return true;    }





        void drawStrokes() {
        if (memCanvas == null) {
            //缓冲位图
            memBMP = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            memCanvas = new Canvas(); //缓冲画布
            memCanvas.setBitmap(memBMP); //为画布设置位图，图形实际保存在位图中
            memPaint = new Paint(); //画笔
            memPaint.setAntiAlias(true); //抗锯齿            
            memPaint.setColor(Color.RED); //画笔颜色            
            memPaint.setStyle(Paint.Style.STROKE); //设置填充类型            
            memPaint.setStrokeWidth(5); //设置画笔宽度
        }
           memCanvas.drawLine(oldx,oldy,x,y,memPaint);
            invalidate(); //刷新屏幕
        }


    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        if (memBMP != null)
            canvas.drawBitmap(memBMP, 0, 0, paint);
    }
}
