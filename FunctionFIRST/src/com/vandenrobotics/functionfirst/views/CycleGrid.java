package com.vandenrobotics.functionfirst.views;

import com.vandenrobotics.functionfirst.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;

public class CycleGrid extends GridLayout {

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
	}

}
