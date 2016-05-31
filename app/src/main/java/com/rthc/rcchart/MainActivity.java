package com.rthc.rcchart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RcPieChart rcPieChart1;
    RcPieChart rcPieChart2;
    RcPieChart rcPieChart3;

    TextView textView1;
    TextView textView2;
    TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcPieChart1 = (RcPieChart) findViewById(R.id.pieChart1);
        rcPieChart2 = (RcPieChart) findViewById(R.id.pieChart2);
        rcPieChart3 = (RcPieChart) findViewById(R.id.pieChart3);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        Button button = (Button) findViewById(R.id.button);

        if( button!= null)
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rcPieChart1 != null) {
                    float progress1 = (float) Math.random();
                    rcPieChart1.setProgress(progress1);
                    textView1.setText((int) (progress1 * 100) + "%");
                }
                if (rcPieChart2 != null) {
                    float progress2 = (float) Math.random();
                    rcPieChart2.setProgress(progress2);
                    textView2.setText((int) (progress2 * 100) + "%");
                }
                if (rcPieChart3 != null) {
                    float progress3 = (float) Math.random();
                    rcPieChart3.setProgress(progress3);
                    textView3.setText((int) (progress3 * 100) + "%");
                }
            }
        });
    }

}
