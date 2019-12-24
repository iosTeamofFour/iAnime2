package com.nemesiss.dev.ianime.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.nemesiss.dev.ianime.Model.Model.Circle;
import com.nemesiss.dev.ianime.Model.Model.Rect;

import java.util.ArrayList;
import java.util.List;

import static com.nemesiss.dev.ianime.Acitivity.DrawingActivity.myColor;
import static com.nemesiss.dev.ianime.Acitivity.DrawingActivity.tag;

public class MyDrawView extends PinchImageView {

    Paint mPaint;
    public static List<Circle> circles = new ArrayList<>();
    public static List<Rect> rects = new ArrayList<>();
    int myTag;
    boolean canDrawDot = false;
    private   Canvas DotsCanva;
    private Bitmap SaveDotsBitmap;
    private  PinchImageView background;

    public MyDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(myColor);
        mPaint.setAntiAlias(true);    // 抗锯齿化

    }

    public boolean CanUseCanva() {
        return DotsCanva != null && SaveDotsBitmap != null;
    }

    public void SetBackgroundPinchIv(PinchImageView piv) {
        background = piv;
    }

    public void InitDrawView() {
        int measuredWidth = getMeasuredWidth(), measuredHeight = getMeasuredHeight();
        SaveDotsBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        DotsCanva = new Canvas(SaveDotsBitmap);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        InitDrawView();
    }

    public void SetCanDrawDot(boolean value) {
        canDrawDot = value;
    }


    public void DrawDotsToCanva() {

        if(CanUseCanva()) {
            if (circles.size() != 0) {
                for (int i = 0; i < circles.size(); i++) {
                    mPaint.setColor(circles.get(i).getColor());
                    DotsCanva.drawCircle(circles.get(i).getCenterX(), circles.get(i).getCenterY(),
                            circles.get(i).getRadius(), mPaint);
                }
            }
            if (rects.size() != 0) {
                for (int i = 0; i < rects.size(); i++) {
                    mPaint.setColor(rects.get(i).getColor());
                    DotsCanva.drawRect(rects.get(i).getLeft(), rects.get(i).getTop(), rects.get(i).getRight(),
                            rects.get(i).getBottom(), mPaint);
                }
            }
        }
        setImageBitmap(SaveDotsBitmap);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(canDrawDot) {
            myTag = tag;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (myTag == 1) {
                        Circle circle = new Circle(10, event.getX(), event.getY(), myColor);
                        circles.add(circle);
                    }
                    if (myTag == 2) {
                        Rect rect = new Rect(event.getX() - 6, event.getY() - 6, event.getX() + 6, event.getY() + 6, myColor);
                        rects.add(rect);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    DrawDotsToCanva();
                    break;
            }
        }
        else {
            super.onTouchEvent(event);
            background.onTouchEvent(event);
        }
        return true;
    }
}
