package com.rthc.rcchart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
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

    int mPieColor;
    float mPieProgress;

    float mLineWidth;

    Paint mPaint;


    void setupAttrs(AttributeSet attrs){

        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.RcPieChart, 0, 0);

        for (int i = 0; i < a.getIndexCount(); i++)
        {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.RcPieChart_pieColor:
                    // 默认颜色设置为灰色
                    mPieColor = a.getColor(attr, Color.GRAY);
                    break;
                case R.styleable.RcPieChart_pieProgress:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mPieProgress = a.getFloat(attr, 0.6f);
                    break;
            }

        }

        a.recycle();

        //初始化画笔
        mPaint = new Paint();
        mPaint.setColor(mPieColor);
        mPaint.setAntiAlias(true);
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


        int radius;
        if (getWidth() > getHeight()) {
            radius = getHeight() / 2;
        } else {
            radius = getWidth() / 2;
        }
        RectF rect = new RectF(getWidth() / 2 - radius, getHeight() / 2 - radius, getWidth() / 2 + radius, getHeight() / 2 + radius);

        mPaint.setColor(Color.LTGRAY);
        canvas.drawArc(rect, -90, 360, false, mPaint);

        mLineWidth = radius;
        RectF rectCircle = new RectF(getWidth() / 2 - radius + mLineWidth/2,
                getHeight() / 2 - radius+ mLineWidth/2,
                getWidth() / 2 + radius - mLineWidth/2,
                getHeight() / 2 + radius- mLineWidth/2);
        mPaint.setColor(mPieColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mLineWidth);
        canvas.drawArc(rectCircle, -90, 360 * mPieProgress, false, mPaint);

        Log.i("log", "getWidth()--" + getWidth() + "     getHeight()--" + getHeight());
    }
}
