package com.mysample.customviewtest.custom.template;

/**
 * make : 2019-11-05 - Hinos
 * role : CustomView Super Class
 * */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class ScetchView
{
    public final static int SHAPE_NONE      = -1;
    public final static int SHAPE_LINE      = 1;
    public final static int SHAPE_RECT      = 2;
    public final static int SHAPE_OVAL      = 3;


    public final static int DRAW_MODE       = 5;
    public final static int MODIFY_MODE     = 6;
    public final static int MOVE_MODE       = 7;

    public final static int TOUCH_NONE        = -1;
    public final static int TOUCH_VERTEXT1    = 1;
    public final static int TOUCH_VERTEXT2    = 2;
    public final static int TOUCH_VERTEXT3    = 3;
    public final static int TOUCH_VERTEXT4    = 4;
    public final static int TOUCH_BODY        = 5;

    Context context;

    Paint paint;
    Paint seletPaint;

    public int startX               = -1;
    public int startY               = -1;

    public int lastX                = -1;
    public int lastY                = -1;

    public int clickX               = -1;
    public int clickY               = -1;

    protected int selectRadius      = 50;

    public int currentMode          = DRAW_MODE;
    public int shapeKinds           = SHAPE_NONE;

    public ScetchView(Context context)
    {
        this.context = context;
    }

    public ScetchView(Context context, @Nullable AttributeSet attrs)
    {
        this.context = context;
    }

    public void onDraw(Canvas canvas) {

    }

    protected void initView(Context context)
    {

    }

    public void setMode(int nMode)
    {

    }

    public void initStartPixel(int nStartX, int nStartY)
    {

    }

    public void initLastPixel(int nLastX, int nLastY)
    {

    }

    public void cmdOnDraw(int nX, int nY)
    {

    }

    public void cmdOnResize(int nType, int nX, int nY)
    {

    }

    public void shapeMove(int nX, int nY)
    {

    }

    public void shapeDraw()
    {

    }

    public boolean onTouchModify(int nType, int nX, int nY)
    {
        return false;
    }

    public int hitCheck(int nX, int nY)
    {
        return -1;
    }
}

