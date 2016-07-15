package com.rthc.rcchart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/1.
 */
public class RcLineChart extends View{


    double[] dataX;
    double[] dataY;

    String[] titleX;
    String[] titleY;



    public RcLineChart(Context context) {
        super(context);
    }

    public RcLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RcLineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
