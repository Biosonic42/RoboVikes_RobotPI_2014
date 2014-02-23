package com.vandenrobotics.functionfirst.views;

import com.vandenrobotics.functionfirst.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GridBox extends SurfaceView implements SurfaceHolder.Callback {
	
	Canvas canvas;
	Paint paint;
	float downx = 0, downy = 0, upx = 0, upy = 0;
	
	public GridBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initSelf();
    }

    public GridBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSelf();
    }

    public GridBox(Context context) {
        super(context);
        initSelf();
    }
    
    private void initSelf(){
    	
    	canvas = new Canvas();
    	paint = new Paint();
    	paint.setColor(getResources().getColor(R.color.Goldenrod));
    	
    	this.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					downx = event.getX();
					downy = event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_UP: 
                    upx = event.getX();
                    upy = event.getY();
                    canvas.drawLine(downx, downy, upx, upy, paint);
                    break;
				case MotionEvent.ACTION_CANCEL:
					break;
				default:
                    break;
				}
				
				return true;
			}
		});
    }

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
