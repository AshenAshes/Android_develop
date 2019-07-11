package com.example.helloworld.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Locale;

public class clock extends View {
    private static final int FULL_ANGLE=360;
    private static final int HOURS_STEP_ANGLE=30;
    private static final int DEPART_ANGLE=30;
    private static final int DEGREE_STEP_ANGLE=6;
    private static final int CUSTOM_ALPHA=100;
    private static final int FULL_ALPHA=255;

    private int mWidth,mHeight;
    private int mCenterX,mCenterY;
    private static boolean mShowClock=true;

    private int DEGREES_COLOR;
    private int HOURS_VALUES_COLOR;
    private int NEEDLES_COLOR;
    private int NUMBERS_COLOR;
    private int CENTER_INNER_COLOR;
    private int CENTER_INNER_LINE_COLOR;
    private int CENTER_OUTER_COLOR;
    private float DEGREES_WIDTH;
    private float HOUES_VALUES_SIZE;
    private float NEEDLES_WIDTH;
    private float NUMBERS_SIZE;
    private float NEEDLES_LENGTH;
    private float CENTER_INNER_WIDTH;
    private float CENTER_OUTER_WIDTH;

    public clock(Context context) {
        super(context);
        init(context, null);
    }

    public clock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, null);
    }

    public clock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, null);
    }

    private void init(Context context, AttributeSet attributeSet){
        this.DEGREES_COLOR= Color.WHITE;
        this.HOURS_VALUES_COLOR=Color.WHITE;
        this.NEEDLES_COLOR=Color.WHITE;
        this.NUMBERS_COLOR=Color.WHITE;
        this.CENTER_INNER_COLOR=Color.GRAY;
        this.CENTER_INNER_LINE_COLOR=Color.BLACK;
        this.CENTER_OUTER_COLOR=Color.WHITE;

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //竖屏
            this.DEGREES_WIDTH=10f;
            this.HOUES_VALUES_SIZE=70f;
            this.NEEDLES_WIDTH=10f;
            this.NUMBERS_SIZE=200f;
            this.CENTER_INNER_WIDTH=13f;
            this.CENTER_OUTER_WIDTH=20f;
            this.NEEDLES_LENGTH=320f;
        } else {
            //横屏
            this.DEGREES_WIDTH=10f;
            this.HOUES_VALUES_SIZE=70f;
            this.NEEDLES_WIDTH=10f;
            this.NUMBERS_SIZE=160f;
            this.CENTER_INNER_WIDTH=13f;
            this.CENTER_OUTER_WIDTH=20f;
            this.NEEDLES_LENGTH=200f;
        }
    }

    public boolean isShow(){
        Log.d("Clock:","isShow()");
        return mShowClock;
    }

    public void setmShowClock(boolean showState){
        Log.d("Clock:","setmShowClock()");
        mShowClock=showState;
        invalidate();
    }

    public void reDraw(){
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size;

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int widthWithoutPadding = width - getPaddingLeft() - getPaddingRight();
        int heightWithoutPadding = height - getPaddingTop() - getPaddingBottom();

        size = Math.min(widthWithoutPadding, heightWithoutPadding);
        setMeasuredDimension(size + getPaddingLeft() + getPaddingRight(), size + getPaddingTop() + getPaddingBottom());
    }

    @Override
    public void onDraw(final Canvas canvas){
        super.onDraw(canvas);

        mWidth = Math.min(getWidth(), getHeight());

        int halfWidth = mWidth / 2;
        mCenterX = halfWidth;
        mCenterY = halfWidth;

        if (mShowClock) {
            drawDegrees(canvas);
            drawHoursValues(canvas);
            drawNeedles(canvas);
            drawCenter(canvas);
        } else {
            drawNumbers(canvas);
        }
    }

    private void drawDegrees(Canvas canvas){
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(DEGREES_COLOR);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(DEGREES_WIDTH);

        float rPadded = mCenterX - mWidth * 0.01f;
        float rEnd = mCenterX - mWidth * 0.05f;

        for (int i = 0; i < FULL_ANGLE; i += DEGREE_STEP_ANGLE) {

            if ((i % DEPART_ANGLE) != 0 && (i % 15) != 0)
                paint.setAlpha(CUSTOM_ALPHA);
            else {
                paint.setAlpha(FULL_ALPHA);
            }

            float startX = (float) (mCenterX + rPadded * Math.cos(Math.toRadians(i)));
            float startY = (float) (mCenterX - rPadded * Math.sin(Math.toRadians(i)));

            float stopX = (float) (mCenterX + rEnd * Math.cos(Math.toRadians(i)));
            float stopY = (float) (mCenterX - rEnd * Math.sin(Math.toRadians(i)));

            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
    }

    private void  drawHoursValues(Canvas canvas){
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(HOURS_VALUES_COLOR);
        paint.setTextSize(HOUES_VALUES_SIZE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        String text;
        int angle;

        Paint.FontMetrics fontMetrics=paint.getFontMetrics();
        float top=fontMetrics.top;
        float bottom=fontMetrics.bottom;
        float textOrigin = mCenterX - mWidth*0.12f;
        float baselineY=(float)((bottom-top)/2-bottom);
        for(int i = 1 ; i <= 12 ; i += 1){
            angle=(i-3)*HOURS_STEP_ANGLE;
            if(i<=9)
                text="0"+ i;
            else
                text=String.valueOf(i);

            float textX=(float)(mCenterX+textOrigin*Math.cos(Math.toRadians(angle)));
            float textY=(float)(mCenterY+textOrigin*Math.sin(Math.toRadians(angle))+baselineY);
            canvas.drawText(text, textX,textY,paint);
        }
    }

    private void drawNeedles(Canvas canvas){
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(NEEDLES_COLOR);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(NEEDLES_WIDTH);

        Calendar calendar=Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        int secondLength= (int)NEEDLES_LENGTH;
        int minuteLength=(int)(NEEDLES_LENGTH*0.9);
        int hourLength=(int)(NEEDLES_LENGTH*0.6);

        paint.setAlpha(FULL_ALPHA);
        //hour
        float angle=(float)(hour*1.0/12*FULL_ANGLE+minute*1.0/60*DEPART_ANGLE);
        float stopX=(float)(mCenterX+hourLength*Math.sin(Math.toRadians(angle)));
        float stopY=(float)(mCenterY-hourLength*Math.cos(Math.toRadians(angle)));
        canvas.drawLine(mCenterX,mCenterY,stopX,stopY,paint);

        //minute
        angle=(float)(minute*1.0/60*FULL_ANGLE+second*1.0/60*DEGREE_STEP_ANGLE);
        stopX=(float)(mCenterX+minuteLength*Math.sin(Math.toRadians(angle)));
        stopY=(float)(mCenterY-minuteLength*Math.cos(Math.toRadians(angle)));
        canvas.drawLine(mCenterX,mCenterY,stopX,stopY,paint);

        //second
        paint.setAlpha(CUSTOM_ALPHA);
        paint.setStrokeWidth((float)(NEEDLES_WIDTH*0.6));
        angle=(float)(second*1.0/60*FULL_ANGLE);
        stopX=(float)(mCenterX+secondLength*Math.sin(Math.toRadians(angle)));
        stopY=(float)(mCenterY-secondLength*Math.cos(Math.toRadians(angle)));
        canvas.drawLine(mCenterX,mCenterY,stopX,stopY,paint);
    }

    private void drawCenter(Canvas canvas){
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(CENTER_OUTER_COLOR);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);

        canvas.drawCircle(mCenterX,mCenterY,CENTER_OUTER_WIDTH,paint);

        paint.setColor(CENTER_INNER_COLOR);
        canvas.drawCircle(mCenterX,mCenterY,CENTER_INNER_WIDTH,paint);

        paint.setColor(CENTER_INNER_LINE_COLOR);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mCenterX,mCenterY,CENTER_INNER_WIDTH,paint);
    }

    private void drawNumbers(Canvas canvas){
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(NUMBERS_COLOR);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setTextSize(NUMBERS_SIZE);
        paint.setTextAlign(Paint.Align.CENTER);

        Calendar calendar=Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        Paint.FontMetrics fontMetrics=paint.getFontMetrics();
        float top=fontMetrics.top;
        float bottom=fontMetrics.bottom;
        float baselineY=(bottom-top)/2-bottom + mCenterY;

        String time = String.format("%s:%s:%s",
                String.format(Locale.getDefault(), "%02d", hour),
                String.format(Locale.getDefault(), "%02d", minute),
                String.format(Locale.getDefault(), "%02d", second)
        );

        canvas.drawText(time, mCenterX, baselineY, paint);
    }
}
