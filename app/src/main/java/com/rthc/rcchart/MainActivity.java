package com.rthc.rcchart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    RcPieChart rcPieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcPieChart = (RcPieChart) findViewById(R.id.pieChart);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rcPieChart != null)
                    rcPieChart.setProgress((float) Math.random());
            }
        });
    }

}
