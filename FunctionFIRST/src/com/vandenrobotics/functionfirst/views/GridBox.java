package com.vandenrobotics.functionfirst.views;

import java.util.ArrayList;

import com.vandenrobotics.functionfirst.R.color;
import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.model.GridData;

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
		paint.setColor(getResources().getColor(color.Gold));
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
		drawLines();
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {

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
						drawLines();
					}
					return true;
				}
				else
					return false;
			}
		});
	}
	public void drawLines(){
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
	
	public void calculateLines(GridData[] gridData){
		for (int i = 0; i<gridData.length; i++){
			if(gridData[i]!=null){
				if(gridData[i].passed){
					float startX = 0, startY = 0, stopX = 0, stopY = 0;
					if(i>=0 && i<=2)
						startY = 30;
					else if(i>=3&&i<=5)
						startY = 90;
					else if(i>=6&&i<=8)
						startY = 150;
					if(i==0 || i == 3 || i == 6)
						startX = 50;
					else if (i == 1 || i == 4 || i == 7)
						startX = 150;
					else if (i == 2 || i == 5 || i == 8)
						startX = 250;
				
					
					if(gridData[i].where>=0 && gridData[i].where<=2)
						stopY = 30;
					else if(gridData[i].where>=3&&gridData[i].where<=5)
						stopY = 90;
					else if(gridData[i].where>=6&&gridData[i].where<=8)
						stopY = 150;
					if(gridData[i].where == 0 || gridData[i].where == 3 || gridData[i].where == 6)
						stopX = 50;
					else if (gridData[i].where == 1 || gridData[i].where == 4 || gridData[i].where == 7)
						stopX = 150;
					else if (gridData[i].where == 2 || gridData[i].where == 5 || gridData[i].where == 8)
						stopX = 250;
					
					lines.add(new Line(startX,startY,stopX,stopY));					
				}
			}
		}
	}
	
	public GridData[] calculateData(){
		GridData[] gridData = new GridData[9];
		for (int i = 0; i <= 8; i++)
			gridData[i] = new GridData();
		for (Line l : lines){
			int startBox = 0, stopBox = 0;
			int column = 0, row = 0;
			
			if(l.startX >= 0 && l.startX<=99)
				column = 1;
			else if(l.startX >= 100 && l.startX<=199)
				column = 2;
			else if(l.startX >= 200 && l.startX<=299)
				column = 3;
			
			if(l.startY >= 0 && l.startY <=57)
				row = 1;
			if(l.startY>=58 && l.startY <=113)
				row = 2;
			if(l.startY>=114 && l.startY <=174)
				row = 3;
			
			switch(column){
			case 1:
				if(row==1) startBox = 0;
				else if(row==2) startBox = 3;
				else if(row==3) startBox = 6;
				break;
			case 2:
				if(row==1) startBox = 1;
				else if(row==2) startBox = 4;
				else if(row==3) startBox = 7;
				break;
			case 3:
				if(row==1) startBox = 2;
				else if(row==2) startBox = 5;
				else if(row==3) startBox = 8;
				break;
			default:
				startBox = 0;
				break;
			}
			column = 0; row = 0;
			if(l.stopX >= 0 && l.stopX<=99)
				column = 1;
			else if(l.stopX >= 100 && l.stopX<=199)
				column = 2;
			else if(l.stopX >= 200 && l.stopX<=299)
				column = 3;
			
			if(l.stopY >= 0 && l.stopY <=57)
				row = 1;
			if(l.stopY>=58 && l.stopY <=113)
				row = 2;
			if(l.stopY>=114 && l.stopY <=174)
				row = 3;
			
			switch(column){
			case 1:
				if(row==1) stopBox = 0;
				else if(row==2) stopBox = 3;
				else if(row==3) stopBox = 6;
				break;
			case 2:
				if(row==1) stopBox = 1;
				else if(row==2) stopBox = 4;
				else if(row==3) stopBox = 7;
				break;
			case 3:
				if(row==1) stopBox = 2;
				else if(row==2) stopBox = 5;
				else if(row==3) stopBox = 8;
				break;
			default:
				stopBox = 0;
				break;
			}
			gridData[startBox].passed = true;
			gridData[startBox].where = stopBox;
		}
		return gridData;
	}
}