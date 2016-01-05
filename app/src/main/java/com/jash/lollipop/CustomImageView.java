package com.jash.lollipop;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by jash
 * Date: 14-12-12
 * Time: 上午10:17
 */
public class CustomImageView extends ImageView {
    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (getDrawable() != null){
            if (heightMode == MeasureSpec.UNSPECIFIED && widthMode == MeasureSpec.EXACTLY){
                heightSize = widthSize * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
            }
        }
        setMeasuredDimension(widthSize, heightSize);
    }

}
