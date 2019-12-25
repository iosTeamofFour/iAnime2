package com.nemesiss.dev.ianime.View;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
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

    public HashMap<Point, AnchorPoint> anchors = new HashMap<>();
    public HashMap<Point, HintPoint> hints = new HashMap<>();
    public List<AnchorPoint> anchorDrawQueue = new ArrayList<>();
    public List<HintPoint> hintDrawQueue = new ArrayList<>();



    int myTag;
    boolean canDrawDot = false;
    private Canvas DotsCanva;
    private Bitmap SaveDotsBitmap;
    private PinchImageView background;
    float[] MatValue = new float[9];

    public MyDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(myColor);
        mPaint.setAntiAlias(true);    // 抗锯齿化

        post(this::InitDrawView);

    }

    public boolean CanUseCanva() {
        return DotsCanva != null && SaveDotsBitmap != null;
    }

    public void SetBackgroundPinchIv(PinchImageView piv) {
        background = piv;
    }

    public void InitDrawView() {
        int width = getWidth(), height = getHeight();
        SaveDotsBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        DotsCanva = new Canvas(SaveDotsBitmap);
        setImageBitmap(SaveDotsBitmap);
    }



    public void SetCanDrawDot(boolean value) {
        canDrawDot = value;
    }


    public void DrawDotsToCanva() {

        if (CanUseCanva()) {
            for (AnchorPoint ap : anchorDrawQueue) {

                mPaint.setColor(ap.Color.ToSystemColor());
                DotsCanva.drawRect(ap.getX() - 6, ap.getY() - 6, ap.getX() + 6, ap.getY() + 6, mPaint);
                anchors.put(ap, ap);

            }
            anchorDrawQueue.clear();
            for (HintPoint hp : hintDrawQueue) {
                mPaint.setColor(hp.Color.ToSystemColor());
                DotsCanva.drawCircle(hp.getX(), hp.getY(), 10, mPaint);
                hints.put(hp, hp);
            }

            hintDrawQueue.clear();

        }
        setImageBitmap(SaveDotsBitmap);
    }

    private Point ApplyScaleToCoord(int ScreenX, int ScreenY) {
        Matrix mat = new Matrix();
        getCurrentImageMatrix(mat);
        mat.getValues(MatValue);
        float ScaleX = MatValue[0];
        float step = 1 / ScaleX;

        float OffsetX = MatValue[2];
        float OffsetY = MatValue[5];


         float FinalX = step * (-OffsetX + ScreenX);
        float FinalY = step * (-OffsetY + ScreenY);
        return new Point((int) FinalX, (int) FinalY);
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

                    int CurrX = (int) event.getX();
                    int CurrY = (int) event.getY();

                    Point MappedPoint = ApplyScaleToCoord(CurrX, CurrY);

                    if (myTag == 1) {
                        anchorDrawQueue.add(new AnchorPoint(MappedPoint.getX(), MappedPoint.getY(), new RGB(r, g, b)));
                    }
                    if (myTag == 2) {
                        hintDrawQueue.add(new HintPoint(MappedPoint.getX(), MappedPoint.getY(), new RGB(r, g, b)));
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

}
