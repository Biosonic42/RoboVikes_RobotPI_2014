package com.vandenrobotics.functionfirst.views;

import com.vandenrobotics.functionfirst.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CycleGrid extends RelativeLayout {

	public int currentCycle;
	public GridBox gridBox;
	public TextView title;
	public TextView partner1;
	public TextView team;
	public TextView partner2;
	
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
		gridBox = (GridBox) view.findViewById(R.id.gridBox);
		title = (TextView) view.findViewById(R.id.titleCycleGrid);
		partner1 = (TextView) view.findViewById(R.id.titlePartner1);
		team = (TextView) view.findViewById(R.id.titleTeam);
		partner2 = (TextView) view.findViewById(R.id.titlePartner2);
	}

}
