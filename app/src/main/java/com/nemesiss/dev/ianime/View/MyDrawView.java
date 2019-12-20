package com.nemesiss.dev.ianime.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.nemesiss.dev.ianime.Model.Model.Circle;
import com.nemesiss.dev.ianime.Model.Model.Rect;

import java.util.ArrayList;
import java.util.List;
import static com.nemesiss.dev.ianime.Acitivity.testActivity.myColor;
import static com.nemesiss.dev.ianime.Acitivity.testActivity.tag;

public class MyDrawView extends View {

    Paint mPaint;
    List<Circle> circles=new ArrayList<>();
    List<Rect> rects=new ArrayList<>();
    int myTag;

    public MyDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(myColor);
        mPaint.setAntiAlias(true);    // 抗锯齿化

    }

    @Override
    protected void onDraw(Canvas canvas) {

        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if(circles.size()!=0) {
            for (int i = 0; i < circles.size(); i++) {
                mPaint.setColor(circles.get(i).getColor());
                canvas.drawCircle(circles.get(i).getCenterX(), circles.get(i).getCenterY(),
                        circles.get(i).getRadius(), mPaint);
            }
        }
        if(rects.size()!=0){
            for(int i=0;i<rects.size();i++)
            {
                mPaint.setColor(rects.get(i).getColor());
                canvas.drawRect(rects.get(i).getLeft(),rects.get(i).getTop(),rects.get(i).getRight(),
                        rects.get(i).getBottom(),mPaint);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        myTag=tag;
        switch (event.getAction())
        {
          case   MotionEvent.ACTION_DOWN:
            if(myTag==1) {
                Circle circle = new Circle(10, event.getX(), event.getY(), myColor);
                circles.add(circle);
            }
            else {
                Rect rect = new Rect(event.getX() - 6, event.getY() - 6, event.getX() + 6, event.getY() + 6, myColor);
                rects.add(rect);
            }
            break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        /**
         * invalidate:只能用在UI线程中
         * postInvalidate:可以在子线程中进行调用
         * */
        postInvalidate();
        //invalidate();    // 通知View对象调用onDraw方法进行重新绘制
        return true;// 返回true表明该事件已经被处理了
    }
}
