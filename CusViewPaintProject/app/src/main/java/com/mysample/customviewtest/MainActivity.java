package com.mysample.customviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mysample.customviewtest.custom.CanvasView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    CanvasView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewSetting();
    }

    void initViewSetting()
    {
        View v;
        v = findViewById(R.id.btnLine);
        v.setOnClickListener(this);
        v = findViewById(R.id.btnRect);
        v.setOnClickListener(this);
        v = findViewById(R.id.btnCircle);
        v.setOnClickListener(this);
        v = findViewById(R.id.btnDelAll);
        v.setOnClickListener(this);
        v = findViewById(R.id.btnModifyMode);
        v.setOnClickListener(this);
        v = findViewById(R.id.btnBoard);
        v.setOnClickListener(this);

        canvasView = findViewById(R.id.canvasView);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnLine:
                canvasView.selectShape(CanvasView.VIEW_MODE_LINE);
                break;

            case R.id.btnRect:
                canvasView.selectShape(CanvasView.VIEW_MODE_RECT);
                break;

            case R.id.btnCircle:
                canvasView.selectShape(CanvasView.VIEW_MODE_CIRCLE);
                break;

            case R.id.btnBoard:
                onBtnBoard();
                break;

            case R.id.btnModifyMode:
                onBtnModify();
                break;

            case R.id.btnDelAll:
                onBtnViewClear();
                break;
        }
    }

//    private void selectShape(int nShape)
//    {
//        nSelected = nShape;
//    }

    private void onBtnModify()
    {
        canvasView.changeModifyMode();
    }

    private void onBtnBoard()
    {
//        selectShape(SELECT_BOARD);
//        onBtnViewClear();
//        PaintBoard pb = new PaintBoard(this);
//        drawView.addView(pb);
    }

    private void onBtnViewClear()
    {
        canvasView.clearScetchView();
    }
}
