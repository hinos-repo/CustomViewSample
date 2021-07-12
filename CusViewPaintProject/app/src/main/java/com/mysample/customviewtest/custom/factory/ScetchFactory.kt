package com.mysample.customviewtest.custom.factory

import android.content.Context
import com.mysample.customviewtest.custom.template.ScetchLine
import com.mysample.customviewtest.custom.template.ScetchOval
import com.mysample.customviewtest.custom.template.ScetchRect
import com.mysample.customviewtest.custom.template.ScetchView

object ScetchFactory
{
    fun createScetchView(context : Context, shapeType : Int) : ScetchView
    {
        val scetchView : ScetchView
        when(shapeType)
        {
            ScetchView.SHAPE_LINE ->
            {
                scetchView = ScetchLine(context)
            }
            ScetchView.SHAPE_RECT ->
            {
                scetchView = ScetchRect(context)
            }
            ScetchView.SHAPE_OVAL ->
            {
                scetchView = ScetchOval(context)
            }
            else -> {
                throw IllegalArgumentException("inappropriate shapeType")
            }
        }

        return scetchView
    }
}