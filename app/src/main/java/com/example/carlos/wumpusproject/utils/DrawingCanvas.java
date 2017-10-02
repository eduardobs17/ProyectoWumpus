package com.example.carlos.wumpusproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by carlos on 8/23/17.
 */

public class DrawingCanvas extends View {

    private Path drawPath; // Path used to draw lines.
    private static Paint drawPaint, canvasPaint; // Paint objects to draw and paint the canvas
    private static int paintColor = 0xFF660000; // Default color
    private Canvas canvas; // This is the drawing and painting area
    private Bitmap canvasBitmap; // Saving canvas

    private static float dotSize;
    private static boolean erased = false;


    public DrawingCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setupDrawing();
    }

    private void setupDrawing(){
        // Initialize workspace
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);

    }

    // Size assigned to the view
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(canvasBitmap);
    }

    // Registers users finger touch actions
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

/*        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:*/
                drawPath.lineTo(touchX, touchY);
/*                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(touchX, touchY);
                canvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }*/

        // Repaint
        invalidate();
        return true;

    }

    //Actualiza color
    public void setColor(String newColor){
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

    //Poner tamaño del punto
    public static void setDotSize(float nuevoTamanyo){


        //float pixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        //        nuevoTamanyo, getResources().getDisplayMetrics());

        //TamanyoPunto=pixel;
        drawPaint.setStrokeWidth(nuevoTamanyo);
    }


    //set borrado true or false
    public static void setErasen(boolean estaborrado){
        erased = estaborrado;
        if(erased) {

            drawPaint.setColor(Color.WHITE);
            //drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        }
        else {
            drawPaint.setColor(paintColor);
            //drawPaint.setXfermode(null);
        }
    }

    public void newDrawing(){
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();

    }
}
