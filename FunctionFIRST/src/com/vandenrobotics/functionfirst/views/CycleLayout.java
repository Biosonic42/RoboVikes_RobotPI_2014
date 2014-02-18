package com.vandenrobotics.functionfirst.views;

import com.vandenrobotics.functionfirst.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class CycleLayout extends RelativeLayout {

	public CycleGrid cycleGrid;
	public RadioSwitch radioSwitch;
	
	public CycleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public CycleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CycleLayout(Context context) {
        super(context);
        initView();
    }
	
	private void initView(){
		View view = inflate(getContext(), R.layout.cycle_layout, null);
		addView(view);
		cycleGrid = (CycleGrid)view.findViewById(R.id.cycleGrid);
		radioSwitch = (RadioSwitch)view.findViewById(R.id.RadioSeekBars);
	}

}
