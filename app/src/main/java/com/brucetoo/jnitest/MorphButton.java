package com.brucetoo.jnitest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;

/**
 * Created by Bruce Too
 * On 3/22/16.
 * At 23:58
 */
public class MorphButton extends View {

    public MorphButton(Context context) {
        super(context);
        init();
    }

    public MorphButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MorphButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MorphButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private Paint mPaint;
    private Path mPath = new Path();
    private ArrayList<ValueAnimator> mAnimators;
    private int mHeight;
    private int mWidth;
    private float mRectangleRadius = 40;
    private RectF mRectangleRect;
    private float centerX;
    private float centerY;
    private float circleRadius;
    private float currentOffset;


    private void init() {

        mAnimators = new ArrayList<>();

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ff0000"));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        centerX = mWidth / 2;
        centerY = mHeight / 2;
        circleRadius = centerY - 10;
        currentOffset = circleRadius;
    }

    RectF rectF;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mPaint.setColor(Color.parseColor("#ff0000"));
        if(currentOffset != 0) {
            canvas.drawRoundRect(createRectangleRect(), mRectangleRadius, mRectangleRadius, mPaint);
        }
//        mPaint.setColor(Color.parseColor("#00ff00"));
        canvas.drawCircle(centerX, centerY, circleRadius, mPaint);

        if(currentOffset == 0){
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(3);
            canvas.drawArc(createRectF(),-90,90,false,mPaint);
        }else {
            mPaint.setStyle(Paint.Style.FILL);
        }

//        mPath.reset();
//        mPath.moveTo(centerX,0);
//        mPath.quadTo(centerX,0,centerX + circleRadius + currentOffset, centerY);
//        mPath.quadTo(centerX + circleRadius + currentOffset, centerY,centerX,mHeight);
//        mPath.quadTo(centerX,mHeight,centerX - circleRadius - currentOffset, centerY);
//        mPath.quadTo(centerX - circleRadius - currentOffset, centerY,centerX,0);
//
//        mPath.close();
//        Log.e("currentOffset",currentOffset+"");
//        canvas.drawPath(mPath,mPaint);
    }

    private RectF createRectF(){
        rectF = new RectF(centerX - circleRadius - 10,centerY - circleRadius - 10,
                centerX + circleRadius + 10,centerY + circleRadius + 10);
        return rectF;
    }

    private RectF createRectangleRect() {
        mRectangleRect = new RectF(centerX - circleRadius - currentOffset, 0, centerX + currentOffset + circleRadius, mHeight);
        return mRectangleRect;
    }

    ObjectAnimator animator;

    public void start(){
        if(animator == null) {
            animator = ObjectAnimator.ofFloat(this, "currentOffset", circleRadius, 0);
            animator.setDuration(500);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.start();
        }else {
            animator.cancel();
            animator.reverse();
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    animator = null;
                }
            });
        }
    }

    public float getCurrentOffset() {
        return currentOffset;
    }

    public void setCurrentOffset(float currentOffset) {
        this.currentOffset = currentOffset;
        invalidate();
    }
}
