package com.vandenrobotics.functionfirst;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.vandenrobotics.functionfirst.model.AutoData;

public class AutoFragment extends Fragment {

	private CheckBox autoHadAuto;
	private CheckBox autoMobilityBonus;
	private CheckBox autoGoalieZone;
	
	private EditText autoHighScore;
	private EditText autoLowScore;
	private EditText autoHotScore;
	
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
		autoHighScore.setText(""+autoData.highScore);
		autoLowScore.setText(""+autoData.lowScore);
		autoHotScore.setText(""+autoData.hotScore);
	}
	
	private void saveData(AutoData autoData){
		if(viewsAssigned){
			autoData.hadAuto = autoHadAuto.isChecked();
			autoData.mobilityBonus = autoMobilityBonus.isChecked();
			autoData.goalieZone = autoGoalieZone.isChecked();
			autoData.highScore = (isInt(autoHighScore.getText().toString()) ?
					Integer.parseInt(autoHighScore.getText().toString()) : 0);
			autoData.lowScore = (isInt(autoLowScore.getText().toString()) ?
					Integer.parseInt(autoLowScore.getText().toString()) : 0);
			autoData.hotScore = (isInt(autoHotScore.getText().toString()) ?
					Integer.parseInt(autoHotScore.getText().toString()) : 0);
		}
	}
	
	private boolean isInt(String s){
		try{
			Integer.parseInt(s);
		} catch(NumberFormatException e){
			return false;
		}
		
		return true;
	}
	
	private void assignViews(View view){
		autoHadAuto = (CheckBox)view.findViewById(R.id.autoHadAuto);
		autoMobilityBonus = (CheckBox)view.findViewById(R.id.autoMobilityBonus);
		autoGoalieZone = (CheckBox)view.findViewById(R.id.autoGoalieZone);
		
		autoHighScore = (EditText)view.findViewById(R.id.autoHighScore);
		autoLowScore = (EditText)view.findViewById(R.id.autoLowScore);
		autoHotScore = (EditText)view.findViewById(R.id.autoHotScore);
		viewsAssigned = true;
	}
}
