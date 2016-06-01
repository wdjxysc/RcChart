package com.rthc.rcchart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/5/30.
 */
public class RcPieChart extends View {
    public RcPieChart(Context context) {
        super(context);
    }

    public RcPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupAttrs(attrs);
    }

    public RcPieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int mPieColor = Color.CYAN;
    int mCircleSecondColor = Color.LTGRAY;
    int mPieBackColor = Color.TRANSPARENT;
    float mPieProgress;
    int mCircleWidth = 0;//默认为0 就是扇形

    Paint mPaintBack;
    Paint mPaintTop;

    int radius;



    void setupAttrs(AttributeSet attrs){

        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.RcPieChart, 0, 0);

        for (int i = 0; i < a.getIndexCount(); i++)
        {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.RcPieChart_pieColor:
                    // 默认颜色设置为灰色
                    mPieColor = a.getColor(attr, Color.LTGRAY);
                    break;
                case R.styleable.RcPieChart_circleSecondColor:
                    // 默认颜色设置为灰色
                    mCircleSecondColor = a.getColor(attr, Color.LTGRAY);
                    break;
                case R.styleable.RcPieChart_pieBackColor:
                    // 默认颜色设置为透明
                    mPieBackColor = a.getColor(attr, Color.TRANSPARENT);
                    break;
                case R.styleable.RcPieChart_pieProgress:
                    // 默认设置为60%，TypeValue也可以把sp转化为px
                    mPieProgress = a.getFloat(attr, 0.6f);
                    break;
                case R.styleable.RcPieChart_circleWidth:
                    mCircleWidth = (int)a.getDimension(attr,radius);
            }
        }

        a.recycle();



        //初始化画笔
        mPaintBack = new Paint();
        mPaintBack.setAntiAlias(true);
        mPaintTop = new Paint();
        mPaintTop.setAntiAlias(true);
        mPaintTop.setColor(mPieColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(mPieColor);

//        canvas.drawCircle(getWidth()/2,
//                getHeight()/2,
//                getWidth()/3, mPaint);

        if (getWidth() > getHeight()) {
            radius = getHeight() / 2;
        } else {
            radius = getWidth() / 2;
        }


        RectF rect = new RectF(getWidth() / 2 - radius, getHeight() / 2 - radius, getWidth() / 2 + radius, getHeight() / 2 + radius);

        mPaintBack.setColor(mPieBackColor);
        canvas.drawArc(rect, -90, 360, false, mPaintBack);

        if(mCircleWidth == 0) mCircleWidth = radius;
        RectF rectCircle = new RectF(getWidth() / 2 - radius + mCircleWidth /2,
                getHeight() / 2 - radius+ mCircleWidth /2,
                getWidth() / 2 + radius - mCircleWidth /2,
                getHeight() / 2 + radius- mCircleWidth /2);
        mPaintTop.setColor(mCircleSecondColor);
        mPaintTop.setStyle(Paint.Style.STROKE);
        mPaintTop.setStrokeWidth(mCircleWidth);
        canvas.drawArc(rectCircle, -90, 360, false, mPaintTop);

        mPaintTop.setColor(mPieColor);
        canvas.drawArc(rectCircle, -90, 360 * mPieProgress, false, mPaintTop);

        Log.i("log", "getWidth()--" + getWidth() + "     getHeight()--" + getHeight());
    }

    /**
     * 设置百分比
     * @param progress
     */
    public void setProgress(final float progress) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int animatorTime = 500;//动画时长
                int frameIntervalTime = 10;//每帧间隔时间

                float interval = progress - mPieProgress;

                for (int i = 0;i < animatorTime; i+=frameIntervalTime){
                    mPieProgress += interval/(animatorTime/frameIntervalTime);
                    postInvalidate();
                    try {
                        Thread.sleep(frameIntervalTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                mPieProgress = progress;
                postInvalidate();
            }
        }).start();

    }

    /**
     * 获取百分比
     */
    public float getProgress() {
        return mPieProgress;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        event.getAction();
        return super.onTouchEvent(event);
    }
}
