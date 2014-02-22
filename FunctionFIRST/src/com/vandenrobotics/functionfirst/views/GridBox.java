package com.vandenrobotics.functionfirst.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class GridBox extends SurfaceView {
	
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
    	this.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					Log.d("X",String.valueOf(event.getX()));
					Log.d("Y",String.valueOf(event.getY()));
					break;
				case MotionEvent.ACTION_UP: 
                    Log.d("X",String.valueOf(event.getX()));
                    Log.d("Y",String.valueOf(event.getY()));
                    break;
				}
				
				return true;
			}
		});
    }
	
}
