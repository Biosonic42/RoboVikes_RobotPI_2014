package com.vandenrobotics.functionfirst.views;

import com.vandenrobotics.functionfirst.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CycleGrid extends RelativeLayout {

	public TextView title;
	public GridBox gridBox;
	
	public CycleGrid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public CycleGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CycleGrid(Context context) {
        super(context);
        initView();
    }
	
	private void initView(){
		View view = inflate(getContext(), R.layout.cycle_grid, null);
		addView(view);
		title = (TextView)view.findViewById(R.id.titleCycleGrid);
		gridBox = (GridBox)view.findViewById(R.id.gridBox);
	}

}
