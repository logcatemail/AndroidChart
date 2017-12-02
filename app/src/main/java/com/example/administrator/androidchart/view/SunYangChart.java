package com.example.administrator.androidchart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.time.chrono.HijrahEra;

/**
 * Created by sunYang on 2017/12/2.
 */

public class SunYangChart extends BaseView {

    public SunYangChart(Context context) {
        super(context);
    }

    public SunYangChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SunYangChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void drawColumn(Canvas canvas, Paint mPaint) {

        if (columnInfo != null) {
            float cellWidth = width / axisDivideSizeX;
            for (int i = 0; i < columnInfo.length; i++) {
                mPaint.setColor(columnInfo[i][1]);
                float leftTopY = oraginY - height * (columnInfo[i][0]) / axisDivideSizeY;
                canvas.drawRect(oraginX + cellWidth * (i + 1), leftTopY, oraginX + cellWidth * (i + 2), oraginY, mPaint);
            }
        }

    }

    @Override
    protected void drawYAxisScale(Canvas canvas, Paint mPaint) {
        float cellWidth = height / axisDivideSizeY;
        for (int i = 0; i < axisDivideSizeY - 1; i++) {
            canvas.drawLine(oraginX, oraginY - cellWidth * (i + 1), oraginX + 10, oraginY - cellWidth * (i + 1), mPaint);
        }

    }

    @Override
    protected void drawYAxisValue(Canvas canvas, Paint mPaint) {

        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(16);
        mPaint.setFakeBoldText(true);
        float cellHeight = height / axisDivideSizeY;
        float cellValue = maxAxisValueY / axisDivideSizeY;
        for (int i = 1; i < axisDivideSizeY; i++) {
            canvas.drawText(i + "", oraginX - 30, oraginY - cellHeight * i + 10, mPaint);
        }

    }

    @Override
    protected void drawXAxisScale(Canvas canvas, Paint mPaint) {

        float cellHeight = width / axisDivideSizeX;
        for (int i = 0; i < axisDivideSizeX - 1; i++) {
            canvas.drawLine(cellHeight * (i + 1) + oraginX, oraginY, cellHeight * (i + 1) + oraginX, oraginY - 10, mPaint);
        }

    }

    @Override
    protected void drawXAxisValue(Canvas canvas, Paint mPaint) {

        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(16);
        mPaint.setFakeBoldText(true);
        float cellWidth = width / axisDivideSizeX;
        for (int i = 0; i < axisDivideSizeX; i++) {
            canvas.drawText(i + "", cellWidth * i + oraginX - 35, oraginY + 30, mPaint);
        }

    }

    @Override
    protected void drawYAxis(Canvas canvas, Paint mPaint) {

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        //设置画笔抗锯齿
        mPaint.setAntiAlias(true);
        canvas.drawLine(oraginX, oraginY, oraginX, oraginY - height, mPaint);
    }

    @Override
    protected void drawXAxis(Canvas canvas, Paint mPaint) {

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        //设置画笔抗锯齿
        mPaint.setAntiAlias(true);

        canvas.drawLine(oraginX, oraginY, oraginX + width, oraginY, mPaint);
    }


}
