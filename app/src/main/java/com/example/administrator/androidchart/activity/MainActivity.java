package com.example.administrator.androidchart.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.androidchart.R;
import com.example.administrator.androidchart.view.SunYangChart;

public class MainActivity extends AppCompatActivity {

    private SunYangChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChart = findViewById(R.id.columnSun);

        int[][] columnInfo = new int[][]{
                {7, Color.RED},
                {3, Color.GRAY},
                {9, Color.GREEN},
                {6, Color.BLUE},
                {4, Color.YELLOW},
                {2, Color.DKGRAY},
                {8, Color.CYAN}
        };

        mChart.setColumnInfo(columnInfo);
        mChart.setXAxisScaleValue(10, 9);
        mChart.setYAxisScaleValue(10,7);

    }
}
