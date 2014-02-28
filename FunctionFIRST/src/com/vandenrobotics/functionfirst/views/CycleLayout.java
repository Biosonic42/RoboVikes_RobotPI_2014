package com.vandenrobotics.functionfirst.views;

import java.util.ArrayList;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.model.CycleData;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CycleLayout extends RelativeLayout {

	public CycleGrid cycleGrid;
	public RadioSwitch radioSwitch;
	public Button buttonLastCycle;
	public Button buttonNextCycle;
	public Button buttonUndo;
	
	public int maxHighScores = 0, maxLowScores = 0;
	public int maxTrussScores = 0, maxCatchScores = 0;
	
	public ArrayList<CycleData> cycles = new ArrayList<CycleData>();
	
	public int currentCycle = 1;
	
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
		buttonLastCycle = (Button)view.findViewById(R.id.buttonLastCycle);
		buttonNextCycle = (Button)view.findViewById(R.id.buttonNextCycle);
		buttonUndo = (Button)view.findViewById(R.id.buttonUndo);
		cycleGrid.title.setText(getResources().getString(R.string.title_cycleGrid)+"\n"+currentCycle);
		
		cycles.add(new CycleData());
		
		// update new cycle
		loadData();
		
		buttonLastCycle.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				goToCycle(currentCycle-1);
			}
		});
		buttonNextCycle.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				goToCycle(currentCycle+1);
			}
		});
		
		buttonUndo.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				try{
				cycleGrid.gridBox.lines.remove(cycleGrid.gridBox.lines.size()-1);
				cycleGrid.gridBox.drawLines();
				} catch (IndexOutOfBoundsException e){
					e.printStackTrace();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
	private void goToCycle(int cycle){
		// save data for this cycle
		saveData();
		
		//change cycles
		currentCycle = cycle>0? cycle : currentCycle;
		if(currentCycle==cycles.size()+1)
			cycles.add(new CycleData());
		
		// update new cycle
		loadData();
	}
	
	public void saveData(){
		//saves data for current cycle
		cycles.get(currentCycle-1).gridData = cycleGrid.gridBox.calculateData();
		cycleGrid.gridBox.lines.clear();
		cycles.get(currentCycle-1).goalsProgress = radioSwitch.switchGoals.getProgress();
		cycles.get(currentCycle-1).tcProgress = radioSwitch.switchTC.getProgress();
		if(radioSwitch.switchGoals.getProgress()==0)
			maxLowScores+=1;
		else if(radioSwitch.switchGoals.getProgress()==2)
			maxHighScores+=1;
		if(radioSwitch.switchTC.getProgress()==0)
			maxTrussScores+=1;
		else if(radioSwitch.switchTC.getProgress()==2)
			maxCatchScores+=1;
	}
	
	public void loadData(){
		//load data for current cycle
		try{
			cycleGrid.gridBox.calculateLines(cycles.get(currentCycle-1).gridData);
			try{
			cycleGrid.gridBox.drawLines();
			} catch (Exception e){
				e.printStackTrace();
			}
			radioSwitch.switchGoals.setProgress(cycles.get(currentCycle-1).goalsProgress);
			radioSwitch.switchTC.setProgress(cycles.get(currentCycle-1).tcProgress);
			cycleGrid.title.setText(getResources().getString(R.string.title_cycleGrid)+"\n" + currentCycle);
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}

}
