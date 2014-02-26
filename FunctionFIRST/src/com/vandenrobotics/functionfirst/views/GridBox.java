package com.vandenrobotics.functionfirst.views;

import com.vandenrobotics.functionfirst.R.color;
import com.vandenrobotics.functionfirst.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GridBox extends SurfaceView implements SurfaceHolder.Callback {
	
	private SurfaceHolder sh;
	private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	public GridBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        sh = getHolder();
		sh.addCallback(this);
		paint.setColor(getResources().getColor(color.Goldenrod));
		paint.setStyle(Style.FILL);
    }

    public GridBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        sh = getHolder();
		sh.addCallback(this);
		paint.setColor(getResources().getColor(color.Goldenrod));
		paint.setStyle(Style.FILL);
    }

    public GridBox(Context context) {
        super(context);
        sh = getHolder();
		sh.addCallback(this);
		paint.setColor(getResources().getColor(color.Goldenrod));
		paint.setStyle(Style.FILL);
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
		canvas.drawCircle(50, 50, 40, paint);
		sh.unlockCanvasAndPost(canvas);
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		// save the data interpretations from the view
	}
	
}