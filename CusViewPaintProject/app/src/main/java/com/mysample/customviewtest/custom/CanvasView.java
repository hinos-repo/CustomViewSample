package com.mysample.customviewtest.custom;

/**
 * make : 2019-11-04 - Hinos
 * role : Canvas View
 * */

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mysample.customviewtest.custom.factory.ScetchFactory;
import com.mysample.customviewtest.custom.template.ScetchView;

import java.util.ArrayList;

public class CanvasView extends View
{
    final public static int VIEW_MODE_NONE = 0;
    final public static int VIEW_MODE_LINE = 1; // 선 그리기
    final public static int VIEW_MODE_RECT = 2; // 사각형 그리기
    final public static int VIEW_MODE_CIRCLE = 3; // 원 그리기
    final public static int VIEW_MODE_MODIFY = 4; // 도형 수정 모드

    private int nPrevSelected = VIEW_MODE_NONE; // 이전 도형 모드, 수정 모드에서 그리기 모드로 변경할 때 사용
    private int nSelected = VIEW_MODE_NONE; // 현재 도형 모드
    private int nPosition = -1; // 포커싱 되어 있는 뷰 포지션
    private int nModifyPosition = -1; // 수정 되고 있는 뷰 포지션


    Context context;
    ArrayList<ScetchView> arrScetch = new ArrayList<>();

    public CanvasView(Context context)
    {
        super(context);
        this.context = context;
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        int i;
        ScetchView v;

        for(i=0; i<arrScetch.size(); i++)
        {
            v = arrScetch.get(i);
            v.onDraw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int X = (int) event.getX();
        int Y = (int) event.getY();

        int action = event.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:

                if(nSelected == VIEW_MODE_MODIFY)
                {
                    for(int i = arrScetch.size()-1; i>=0; i--)
                    {
                        int vertex = arrScetch.get(i).hitCheck(X, Y);

                        boolean bResult = arrScetch.get(i).onTouchModify(vertex, X, Y);
                        if(bResult)
                        {
                            nModifyPosition = i;
                            break;
                        }
                    }
                    return false;
                }

                ScetchView view;
                if(nSelected == VIEW_MODE_LINE)
                {
                    view = ScetchFactory.INSTANCE.createScetchView(context, ScetchView.SHAPE_LINE);
                }
                else if(nSelected == VIEW_MODE_RECT)
                {
                    view = ScetchFactory.INSTANCE.createScetchView(context, ScetchView.SHAPE_RECT);
                }
                else if(nSelected == VIEW_MODE_CIRCLE)
                {
                    view = ScetchFactory.INSTANCE.createScetchView(context, ScetchView.SHAPE_OVAL);
                }
                else {
                    view = ScetchFactory.INSTANCE.createScetchView(context, ScetchView.SHAPE_NONE);
                }
                view.initStartPixel(X, Y);
                arrScetch.add(view);
                nPosition = arrScetch.size()-1;
                break;

            case MotionEvent.ACTION_UP :
            case MotionEvent.ACTION_MOVE :
                if(nPosition >= 0)
                {
                    if(nSelected != VIEW_MODE_MODIFY)
                    {
                        arrScetch.get(nPosition).cmdOnDraw(X, Y);
                        this.invalidate();
                    }
                    else if(nSelected == VIEW_MODE_MODIFY)
                    {
                        if(nModifyPosition != -1)
                        {
                            int vertex = arrScetch.get(nModifyPosition).hitCheck(X, Y);
                            arrScetch.get(nModifyPosition).cmdOnResize(vertex, X, Y);
                            this.invalidate();
                        }
                    }
                }
                break;
        }
        return true;
    }


    public void selectShape(int nSelected)
    {
        this.nSelected = nSelected;
    }

    public void changeModifyMode()
    {
        if(nSelected != CanvasView.VIEW_MODE_MODIFY)
        {
            nPrevSelected = nSelected;

            selectShape(CanvasView.VIEW_MODE_MODIFY);

            for(int i = 0; i<arrScetch.size(); i++)
            {
                arrScetch.get(i).setMode(ScetchView.MODIFY_MODE);
            }
        }
        else
        {
            for (int i = 0; i<arrScetch.size(); i++)
            {
                arrScetch.get(i).setMode(ScetchView.DRAW_MODE);
            }
            nModifyPosition = -1;
            selectShape(nPrevSelected);
        }
        this.invalidate();
    }

    public void clearScetchView()
    {
        nModifyPosition = -1;
        arrScetch.clear();
        invalidate();
    }
}
