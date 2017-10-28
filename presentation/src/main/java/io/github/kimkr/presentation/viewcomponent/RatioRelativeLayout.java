package io.github.kimkr.presentation.viewcomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import io.github.kimkr.presentation.R;

public class RatioRelativeLayout extends RelativeLayout {

    int ratioWidth, ratioHeight;

    public RatioRelativeLayout(Context context) {
        super(context);
        setRatioWidth(1);
        setRatioHeight(1);
    }

    public RatioRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }

    public RatioRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) Math.ceil(width * ratioHeight / (float) ratioWidth);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.Commons);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.Commons, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        setRatioWidth(typedArray.getInt(R.styleable.Commons_ratioWidth, 1));
        setRatioHeight(typedArray.getInt(R.styleable.Commons_ratioHeight, 1));
        typedArray.recycle();
    }

    private void setRatioWidth(int ratioWidth) {
        this.ratioWidth = ratioWidth;
    }

    private void setRatioHeight(int ratioHeight) {
        this.ratioHeight = ratioHeight;
    }
}
