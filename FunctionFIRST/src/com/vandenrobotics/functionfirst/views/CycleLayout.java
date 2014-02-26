package com.vandenrobotics.functionfirst.views;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.model.CycleData;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CycleLayout extends RelativeLayout {

	public int currentCycle = 1;
	public CycleGrid cycleGrid;
	public RadioSwitch radioSwitch;
	public Button buttonLastCycle;
	public Button buttonNextCycle;
	
	public CycleData[] cycles = new CycleData[256];
	
	public CycleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        cycles[currentCycle-1] = new CycleData();
        initView();
    }

    public CycleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        cycles[currentCycle-1] = new CycleData();
        initView();
    }

    public CycleLayout(Context context) {
        super(context);
        cycles[currentCycle-1] = new CycleData();
        initView();
    }
	
	private void initView(){
		View view = inflate(getContext(), R.layout.cycle_layout, null);
		addView(view);
		cycleGrid = (CycleGrid)view.findViewById(R.id.cycleGrid);
		radioSwitch = (RadioSwitch)view.findViewById(R.id.RadioSeekBars);
		buttonLastCycle = (Button) view.findViewById(R.id.buttonLastCycle);
		buttonNextCycle = (Button) view.findViewById(R.id.buttonNextCycle);
		
		cycleGrid.title.setText(getResources().getString(R.string.title_cycleGrid)+"\n"+currentCycle);
		
		buttonLastCycle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				goToCycle(currentCycle-1);
			}
			
		});
		
		buttonNextCycle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToCycle(currentCycle+1);
			}
		});
	}

	protected void goToCycle(int i) {
		//save old cycle progress
		cycles[currentCycle-1].switchGoalsProgress=radioSwitch.switchGoals.getProgress();
		cycles[currentCycle-1].switchTCProgress=radioSwitch.switchTC.getProgress();
		
		//assign new cycle and update title
		if(i>0) currentCycle = i;
		cycleGrid.title.setText(getResources().getString(R.string.title_cycleGrid)+"\n"+currentCycle);
		
		//create new data if this cycle is new
		if(cycles[currentCycle-1]==null){
			cycles[currentCycle-1] = new CycleData();
		}
		
		//load data from the cycle, new or not
		radioSwitch.switchGoals.setProgress(cycles[currentCycle-1].switchGoalsProgress);
		radioSwitch.switchTC.setProgress(cycles[currentCycle-1].switchTCProgress);
	}

}
