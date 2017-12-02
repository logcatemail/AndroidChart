package com.example.administrator.androidchart.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.androidchart.R;

/**
 * Created by sunYang on 2017/12/2.
 */

public abstract class BaseView extends View {

    //视图的宽和高
    public int width;
    public int height;

    //起点的X Y坐标 (UI设计一般会给出)
    public int oraginX = 100;
    public int oraginY = 800;

    // X Y轴等划分
    public int axisDivideSizeX = 9;
    public int axisDivideSizeY = 7;

    //第一个纬度为值， 第二个维度是颜色
    public int[][] columnInfo;
    public float maxAxisValueX = 900;
    public float maxAxisValueY = 700;

    public float xAxisScaleValue;
    public float YAxisScaleValue;

    private Context mContext;
    private Paint mPaint;

    private String mGraphTitle;
    private String mXAxisName;
    private String mYAxisName;

    private int mAxisTextColor;
    private float mAxisTextSize;

    //数据源
    public void setColumnInfo(int[][] columnInfo) {
        this.columnInfo = columnInfo;
    }

    //设置X轴的最大值以及等份数
    public void setXAxisScaleValue(float maxAxisValueX, int dividedSize) {
        this.maxAxisValueX = maxAxisValueX;
        this.axisDivideSizeX = dividedSize;
    }

    //设置Y轴的最大值以及等份数
    public void setYAxisScaleValue(float maxAxisValueY, int dividedSize) {
        this.maxAxisValueY = maxAxisValueY;
        this.axisDivideSizeY = dividedSize;
    }


    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        //获取自定义样式
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SunYangGraphStyles);

        mGraphTitle = typedArray.getString(R.styleable.SunYangGraphStyles_graphTitle);
        mXAxisName = typedArray.getString(R.styleable.SunYangGraphStyles_xAxisName);
        mYAxisName = typedArray.getString(R.styleable.SunYangGraphStyles_yAxisName);

        mAxisTextSize = typedArray.getDimension(R.styleable.SunYangGraphStyles_axisTextSize, 16);
        mAxisTextColor = typedArray.getColor(R.styleable.SunYangGraphStyles_axisTextColor, context.getResources().getColor(R.color.colorAccent));

        if (typedArray != null) {
            typedArray.recycle();
        }
        initPaint();
    }

    //初始化画笔
    private void initPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setDither(true);
            mPaint.setAntiAlias(true);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取视图的宽 高
        width = getWidth() - oraginX -100;
        height = (oraginY > getHeight() ? getHeight() : oraginY) - 400;

        //  X Y轴
        drawXAxis(canvas, mPaint);
        drawYAxis(canvas, mPaint);
        //标题
        drawTitle(canvas, mPaint);
        //X Y轴上的刻度和值
        drawXAxisScale(canvas, mPaint);
        drawXAxisValue(canvas, mPaint);
        drawYAxisScale(canvas, mPaint);
        drawYAxisValue(canvas, mPaint);
        //X Y轴上的箭头
        drawXAxisArrow(canvas, mPaint);
        drawYAxisArrow(canvas, mPaint);
        //中间的柱条
        drawColumn(canvas, mPaint);


    }

    /**
     * 柱条
     *
     * @param canvas
     * @param mPaint
     */

    protected abstract void drawColumn(Canvas canvas, Paint mPaint);

    /**
     * Y轴箭头
     *
     * @param canvas
     * @param mPaint
     */
    private void drawYAxisArrow(Canvas canvas, Paint mPaint) {
        Path mPathY = new Path();
        mPathY.moveTo(oraginX, oraginY - height - 30);
        mPathY.lineTo(oraginX - 10, oraginY - height);
        mPathY.lineTo(oraginX + 10, oraginY - height);
        mPathY.close();

        canvas.drawPath(mPathY, mPaint);
        canvas.drawText(mYAxisName, oraginX - 50, oraginY - height - 30, mPaint);

    }

    /**
     * X轴箭头
     *
     * @param canvas
     * @param mPaint
     */
    private void drawXAxisArrow(Canvas canvas, Paint mPaint) {

        Path mPathX = new Path();
        mPathX.moveTo(oraginX + width + 30, oraginY);
        mPathX.lineTo(oraginX + width, oraginY + 10);
        mPathX.lineTo(oraginX + width, oraginY - 10);

        mPathX.close();
        canvas.drawPath(mPathX, mPaint);
        canvas.drawText(mXAxisName, oraginX + width, oraginY + 30, mPaint);

    }

    /**
     * X Y轴的刻度 值
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYAxisScale(Canvas canvas, Paint mPaint);

    protected abstract void drawXAxisValue(Canvas canvas, Paint mPaint);

    protected abstract void drawXAxisScale(Canvas canvas, Paint mPaint);

    protected abstract void drawYAxisValue(Canvas canvas, Paint mPaint);

    /**
     * 标题
     *
     * @param canvas
     * @param mPaint
     */
    private void drawTitle(Canvas canvas, Paint mPaint) {


        if (mGraphTitle != null) {
            mPaint.setTextSize(mAxisTextSize);
            mPaint.setColor(mAxisTextColor);
            mPaint.setFakeBoldText(true);

            canvas.drawText(mGraphTitle, (getWidth() / 2) - (mPaint.measureText(mGraphTitle) / 2), oraginY + 100, mPaint);
        }

    }

    /**
     * 画Y轴
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYAxis(Canvas canvas, Paint mPaint);

    /**
     * 画X轴
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXAxis(Canvas canvas, Paint mPaint);

}
