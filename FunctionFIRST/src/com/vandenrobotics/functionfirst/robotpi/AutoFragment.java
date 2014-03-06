package com.vandenrobotics.functionfirst.robotpi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.NumberPicker;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.model.AutoData;

public class AutoFragment extends Fragment {

	private CheckBox autoHadAuto;
	private CheckBox autoMobilityBonus;
	private CheckBox autoGoalieZone;
	
	private NumberPicker autoHighScore;
	private NumberPicker autoLowScore;
	private NumberPicker autoHotScore;
	
	private boolean viewsAssigned = false;
	
	private AutoData mAutoData;
	
	public static AutoFragment newInstance(AutoData autoData){
		AutoFragment af = new AutoFragment();
		
		Bundle args = new Bundle();
		args.putParcelable("AutoData",autoData);
		
		af.setArguments(args);
		
		return af;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_scout_auto, container, false);
		
		Bundle args = getArguments();
		mAutoData = args.getParcelable("AutoData");
		
		if(viewsAssigned) loadData(mAutoData);
		
		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		assignViews(view);
		loadData(mAutoData);
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if(!viewsAssigned);
		else if(isVisibleToUser)
		{
			loadData(mAutoData);
		}
		else if(!isVisibleToUser)
		{
			saveData(mAutoData);
		}
	}
	
	@Override
	public void onPause(){
		super.onPause();
		saveData(mAutoData);
		viewsAssigned=false;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		assignViews(getView());
	}
	
	private void loadData(final AutoData autoData){
		autoHadAuto.setChecked(autoData.hadAuto);
		autoMobilityBonus.setChecked(autoData.mobilityBonus);
		autoGoalieZone.setChecked(autoData.goalieZone);
		autoHighScore.setValue(autoData.highScore);
		autoLowScore.setValue(autoData.lowScore);
		autoHotScore.setValue(autoData.hotScore);
	}
	
	private void saveData(AutoData autoData){
		if(viewsAssigned){
			autoData.hadAuto = autoHadAuto.isChecked();
			autoData.mobilityBonus = autoMobilityBonus.isChecked();
			autoData.goalieZone = autoGoalieZone.isChecked();
			autoData.highScore = autoHighScore.getValue();
			autoData.lowScore = autoLowScore.getValue();
			autoData.hotScore = autoHotScore.getValue();
		}
	}
	
	private void assignViews(View view){
		autoHadAuto = (CheckBox)view.findViewById(R.id.autoHadAuto);
		autoMobilityBonus = (CheckBox)view.findViewById(R.id.autoMobilityBonus);
		autoGoalieZone = (CheckBox)view.findViewById(R.id.autoGoalieZone);
		
		autoHighScore = (NumberPicker)view.findViewById(R.id.autoHighScore);
		autoHighScore.setMinValue(0);
		autoHighScore.setMaxValue(3);
		autoLowScore = (NumberPicker)view.findViewById(R.id.autoLowScore);
		autoLowScore.setMinValue(0);
		autoLowScore.setMaxValue(3);
		autoHotScore = (NumberPicker)view.findViewById(R.id.autoHotScore);
		autoHotScore.setMinValue(0);
		autoHotScore.setMaxValue(3);
		viewsAssigned = true;
	}
}
