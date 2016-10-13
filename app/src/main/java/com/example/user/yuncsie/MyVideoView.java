package com.example.user.yuncsie;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by user on 2015/12/18.
 */
public class MyVideoView extends VideoView {

    public MyVideoView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    //重點在此，override這個 function 才可以正常滿版!
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
