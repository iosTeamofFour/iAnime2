package com.nemesiss.dev.ianime.View;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.nemesiss.dev.ianime.Model.Model.Drafting.AnchorPoint;
import com.nemesiss.dev.ianime.Model.Model.Drafting.HintPoint;
import com.nemesiss.dev.ianime.Model.Model.Drafting.Point;
import com.nemesiss.dev.ianime.Model.Model.Drafting.RGB;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.nemesiss.dev.ianime.Acitivity.DrawingActivity.myColor;
import static com.nemesiss.dev.ianime.Acitivity.DrawingActivity.tag;

public class MyDrawView extends PinchImageView {

    Paint mPaint;
//    public static List<Circle> circles = new ArrayList<>();
//    public static List<Rect> rects = new ArrayList<>();

    public HashMap<Point, AnchorPoint> anchors = new HashMap<>();
    public HashMap<Point, HintPoint> hints = new HashMap<>();
    public List<AnchorPoint> anchorDrawQueue = new ArrayList<>();
    public List<HintPoint> hintDrawQueue = new ArrayList<>();



    int myTag;
    boolean canDrawDot = false;
    private Canvas DotsCanva;
    private Bitmap SaveDotsBitmap;
    private PinchImageView background;

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

        if (CanUseCanva()) {
//            if (circles.size() != 0) {
//                for (int i = 0; i < circles.size(); i++) {
//                    mPaint.setColor(circles.get(i).getColor());
//                    DotsCanva.drawCircle(circles.get(i).getCenterX(), circles.get(i).getCenterY(),
//                            circles.get(i).getRadius(), mPaint);
//                }
//            }
//            if (rects.size() != 0) {
//                for (int i = 0; i < rects.size(); i++) {
//                    mPaint.setColor(rects.get(i).getColor());
//                    DotsCanva.drawRect(rects.get(i).getLeft(), rects.get(i).getTop(), rects.get(i).getRight(),
//                            rects.get(i).getBottom(), mPaint);
//                }
//            }
            for(AnchorPoint ap : anchorDrawQueue) {

                mPaint.setColor(ap.Color.ToSystemColor());
                DotsCanva.drawRect(ap.getX() - 6,ap.getY() - 6,ap.getX() + 6,ap.getY() + 6,mPaint);
                anchors.put(ap,ap);

            }

            anchorDrawQueue.clear();


            for(HintPoint hp : hintDrawQueue) {
                mPaint.setColor(hp.Color.ToSystemColor());
                DotsCanva.drawCircle(hp.getX(), hp.getY(), 10, mPaint);
                hints.put(hp,hp);
            }

            hintDrawQueue.clear();

        }
        setImageBitmap(SaveDotsBitmap);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int r = Color.red(myColor);
        int g = Color.green(myColor);
        int b = Color.blue(myColor);


        if (canDrawDot) {
            myTag = tag;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (myTag == 1) {
                        anchorDrawQueue.add(new AnchorPoint((int)event.getX(),(int)event.getY(),new RGB(r,g,b)));
                    }
                    if (myTag == 2) {
//                        Circle circle = new Circle(10, event.getX(), event.getY(), myColor);
//                        circles.add(circle);
//                        String type = "2";
//                        addPoint(event, type);
                        hintDrawQueue.add(new HintPoint((int)event.getX(),(int)event.getY(),new RGB(r,g,b)));
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    DrawDotsToCanva();
                    break;
            }
        } else {
            super.onTouchEvent(event);
            background.onTouchEvent(event);
        }
        return true;
    }

    public void addPoint(MotionEvent event, String type) {
        List<String> aPoint = new ArrayList<>();
        aPoint.add(Integer.toString(Color.red(myColor)));
        aPoint.add(Integer.toString(Color.green(myColor)));
        aPoint.add(Integer.toString(Color.blue(myColor)));
        aPoint.add(type);
        //points.add(aPoint);
    }
}
