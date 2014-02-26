package com.vandenrobotics.functionfirst.views;

import java.util.ArrayList;

import com.vandenrobotics.functionfirst.R.color;
import com.vandenrobotics.functionfirst.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

class Line {
	float startX, startY, stopX, stopY;
	public Line(float startX, float startY, float stopX, float stopY) {
		this.startX = startX;
	    this.startY = startY;
	    this.stopX = stopX;
	    this.stopY = stopY;
	}
	public Line(float startX, float startY) { // for convenience
	    this(startX, startY, startX, startY);
	}
}

public class GridBox extends SurfaceView implements SurfaceHolder.Callback {
	
	private SurfaceHolder sh;
	private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	ArrayList<Line> lines = new ArrayList<Line>();
	
	public GridBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        sh = getHolder();
		sh.addCallback(this);
		paint.setColor(getResources().getColor(color.Goldenrod));
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(6f);
		paint.setAntiAlias(true);
		initTouch();
    }

    public GridBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        sh = getHolder();
		sh.addCallback(this);
		paint.setColor(getResources().getColor(color.Goldenrod));
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(6f);
		paint.setAntiAlias(true);
		initTouch();
    }

    public GridBox(Context context) {
        super(context);
        sh = getHolder();
		sh.addCallback(this);
		paint.setColor(getResources().getColor(color.Goldenrod));
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(6f);
		paint.setAntiAlias(true);
		initTouch();
    }
	
	public void surfaceCreated(SurfaceHolder holder) {
		Bitmap background = BitmapFactory.decodeResource(getResources(),R.drawable.grid_box);
		float scaleHeight = (float)background.getHeight()/(float)getHeight();
		float scaleWidth = (float)background.getWidth()/(float)getWidth();
		int newWidth = Math.round(background.getWidth()/scaleWidth);
		int newHeight = Math.round(background.getHeight()/scaleHeight);
		Bitmap scaled = Bitmap.createScaledBitmap(background, newWidth, newHeight, true);
		Canvas canvas = sh.lockCanvas();
		canvas.drawBitmap(scaled, 0, 0, null);
		sh.unlockCanvasAndPost(canvas);
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		// save the data interpretations from the view
	}
	
	private void initTouch(){
		this.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event){
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					lines.add(new Line(event.getX(), event.getY()));
					return true;
				}
				else if ((event.getAction() == MotionEvent.ACTION_MOVE || 
						event.getAction() == MotionEvent.ACTION_UP) &&
						lines.size()>0) {
					Line current = lines.get(lines.size()-1);
					current.stopX = event.getX();
					current.stopY = event.getY();
					if(event.getAction()==MotionEvent.ACTION_UP)
					{
						Canvas canvas = sh.lockCanvas();
						Bitmap background = BitmapFactory.decodeResource(getResources(),R.drawable.grid_box);
						float scaleHeight = (float)background.getHeight()/(float)getHeight();
						float scaleWidth = (float)background.getWidth()/(float)getWidth();
						int newWidth = Math.round(background.getWidth()/scaleWidth);
						int newHeight = Math.round(background.getHeight()/scaleHeight);
						Bitmap scaled = Bitmap.createScaledBitmap(background, newWidth, newHeight, true);
						canvas.drawBitmap(scaled, 0, 0, null);
						for (Line l : lines) {
							canvas.drawLine(l.startX, l.startY, l.stopX, l.stopY, paint);
							canvas.drawCircle(l.stopX, l.stopY, 10, paint);
						}
						sh.unlockCanvasAndPost(canvas);
					}
					return true;
				}
				else
					return false;
			}
		});
	}
}