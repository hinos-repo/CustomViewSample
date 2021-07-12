package com.mysample.customviewtest.custom.template;

/**
 * make : 2019-11-05 - Hinos
 * role : CustomView Rect
 * */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Toast;


public class ScetchRect extends ScetchView
{
    public ScetchRect(Context context)
    {
        super(context);
        initView(context);
    }

    public ScetchRect(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        initView(context);
    }

    @Override
    protected void initView(Context context)
    {
        this.context = context;
        this.shapeKinds = ScetchView.SHAPE_RECT;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10.0f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);

        seletPaint = new Paint();
        seletPaint.setColor(Color.RED);
    }

    @Override
    public void initStartPixel(int nStartX, int nStartY)
    {
        startX = nStartX;
        startY = nStartY;
    }

    @Override
    public void initLastPixel(int nLastX, int nLastY)
    {
        lastX = nLastX;
        lastY = nLastY;
    }

    @Override
    public void shapeMove(int nX, int nY)
    {
        int tmpX = clickX;
        int tmpY = clickY;

        int rr = nX - tmpX;
        clickX = nX;
        startX = startX + rr;
        lastX = lastX + rr;


        rr = nY - tmpY;
        clickY = nY;
        startY = startY + rr;
        lastY = lastY + rr;

        setMode(ScetchView.MOVE_MODE);
        shapeDraw();
    }

    @Override
    public void cmdOnResize(int nType, int nX, int nY)
    {
        switch (nType)
        {
            case TOUCH_NONE:
                break;
            case TOUCH_VERTEXT1:
                this.initLastPixel(nX, nY);
                this.shapeDraw();
            case TOUCH_VERTEXT2:
                this.initLastPixel(nX, nY);
                this.shapeDraw();
                break;
            case TOUCH_VERTEXT3:
            case TOUCH_VERTEXT4:
                this.initLastPixel(nX, nY);
                this.shapeDraw();
                break;
            case TOUCH_BODY:
                shapeMove(nX, nY);
                break;
        }
    }

    @Override
    public boolean onTouchModify(int nType, int nX, int nY)
    {
        try {
            switch (nType)
            {
                case TOUCH_NONE:
                    return false;
                case TOUCH_VERTEXT1:
                    int tmpX = startX;
                    int tmpY = startY;
                    this.initStartPixel(lastX, lastY);
                    this.initLastPixel(tmpX, tmpY);
                    Toast.makeText(context, "첫번째", Toast.LENGTH_SHORT).show();
                    break;
                case TOUCH_VERTEXT2:
                    int tmpX2 = lastX;
                    int tmpY2 = startY;
                    this.initStartPixel(startX, lastY);
                    this.initLastPixel(tmpX2, tmpY2);
                    Toast.makeText(context, "두번째", Toast.LENGTH_SHORT).show();
                    break;
                case TOUCH_VERTEXT3:
                    int tmpX3 = startX;
                    int tmpY3 = lastY;
                    this.initStartPixel(lastX, startY);
                    this.initLastPixel(tmpX3, tmpY3);
                    Toast.makeText(context, "세번째", Toast.LENGTH_SHORT).show();
                    break;
                case TOUCH_VERTEXT4:
                    this.initStartPixel(startX, startY);
                    Toast.makeText(context, "네번째", Toast.LENGTH_SHORT).show();
                    break;
                case TOUCH_BODY:
                    clickX = nX;
                    clickY = nY;
                    Toast.makeText(context, "몸통", Toast.LENGTH_SHORT).show();
                    break;
            }
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }

    @Override
    public void cmdOnDraw(int nX, int nY)
    {
        this.initLastPixel(nX, nY);
        this.shapeDraw();
    }

    @Override
    public void shapeDraw()
    {
//        this.invalidate();
    }

    @Override
    public int hitCheck(int nX, int nY)
    {
        if(((startX - selectRadius) <= nX && nX <=(startX + selectRadius)) && ((startY - selectRadius) <= nY && nY <=(startY + selectRadius)))
        {
            return TOUCH_VERTEXT1;
        }
        else if(((lastX - selectRadius) <= nX && nX <=(lastX + selectRadius)) && ((startY - selectRadius) <= nY && nY <=(startY + selectRadius)))
        {
            return TOUCH_VERTEXT2;
        }
        else if(((startX - selectRadius) <= nX && nX <=(startX + selectRadius)) && ((lastY - selectRadius) <= nY && nY <=(lastY + selectRadius)))
        {
            return TOUCH_VERTEXT3;
        }
        else if(((lastX - selectRadius) <= nX && nX <=(lastX + selectRadius)) && ((lastY - selectRadius) <= nY && nY <=(lastY + selectRadius)))
        {
            return TOUCH_VERTEXT4;
        }
        else if(((startX <= nX && nX <= lastX) || (lastX <= nX && nX <= startX)) && (((startY <= nY && nY <= lastY) || lastY <= nY && nY <= startY)))
        {
            return TOUCH_BODY;
        }
        else
        {
            return TOUCH_NONE;
        }
    }

    @Override
    public void setMode(int nMode)
    {
        currentMode = nMode;
        shapeDraw();
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(startX != -1 && lastX != -1)
        {
            canvas.drawRect(startX, startY, lastX, lastY, paint);
            if(currentMode != DRAW_MODE)
            {
                canvas.drawCircle(startX, startY, selectRadius, seletPaint);
                canvas.drawCircle(lastX, lastY, selectRadius, seletPaint);

                canvas.drawCircle(startX, lastY, selectRadius, seletPaint);
                canvas.drawCircle(lastX, startY, selectRadius, seletPaint);
            }
        }
    }
}
