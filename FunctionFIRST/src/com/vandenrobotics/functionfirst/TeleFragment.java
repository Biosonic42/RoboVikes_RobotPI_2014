package com.vandenrobotics.functionfirst;

import com.vandenrobotics.functionfirst.model.TeleData;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TeleFragment extends Fragment {
	
	private EditText teleHighScore;
	private EditText teleHighAttempt;
	private EditText teleLowScore;
	private EditText teleLowAttempt;
	private EditText teleTrussScore;
	private EditText teleCatchScore;
	private EditText teleAssistScore;
	
	private boolean viewsAssigned = false;
	
	private TeleData mTeleData;
	
	public static TeleFragment newInstance(TeleData teleData){
		TeleFragment tf = new TeleFragment();
		
		Bundle args = new Bundle();
		args.putParcelable("TeleData",teleData);
		
		tf.setArguments(args);
		
		return tf;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_scout_tele, container, false);
		
		Bundle args = getArguments();
		mTeleData = args.getParcelable("TeleData");
		
		if(viewsAssigned) loadData(mTeleData);
		
		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		assignViews(view);
		loadData(mTeleData);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if(!viewsAssigned);
		else if(isVisibleToUser)
		{
			assignViews(getView());
			loadData(mTeleData);
		}
		else if(!isVisibleToUser)
		{
			saveData(mTeleData);
		}
	}
	
	@Override
	public void onPause(){
		super.onPause();
		saveData(mTeleData);
		viewsAssigned=false;
	}
	
	private void loadData(final TeleData teleData){
		teleHighScore.setText(""+teleData.highScore);
		teleHighAttempt.setText(""+teleData.highAttempt);
		teleLowScore.setText(""+teleData.lowScore);
		teleLowAttempt.setText(""+teleData.lowAttempt);
		teleTrussScore.setText(""+teleData.trussScore);
		teleCatchScore.setText(""+teleData.catchScore);
		teleAssistScore.setText(""+teleData.assistScore);
	}
	
	private void saveData(TeleData teleData){
		if(viewsAssigned) {
			teleData.highScore = (isInt(teleHighScore.getText().toString()) ?
					Integer.parseInt(teleHighScore.getText().toString()) : 0);
			teleData.highAttempt = (isInt(teleHighAttempt.getText().toString()) ?
					Integer.parseInt(teleHighAttempt.getText().toString()) : 0);
			teleData.lowScore = (isInt(teleLowScore.getText().toString()) ?
					Integer.parseInt(teleLowScore.getText().toString()) : 0);
			teleData.lowAttempt = (isInt(teleLowAttempt.getText().toString()) ?
					Integer.parseInt(teleLowAttempt.getText().toString()) : 0);
			teleData.trussScore = (isInt(teleTrussScore.getText().toString()) ?
					Integer.parseInt(teleTrussScore.getText().toString()) : 0);
			teleData.catchScore = (isInt(teleCatchScore.getText().toString()) ?
					Integer.parseInt(teleCatchScore.getText().toString()) : 0);
			teleData.assistScore = (isInt(teleAssistScore.getText().toString()) ? 
					Integer.parseInt(teleAssistScore.getText().toString()) : 0);
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
		teleHighScore = (EditText)view.findViewById(R.id.teleHighScore);
		teleHighAttempt = (EditText)view.findViewById(R.id.teleHighAttempt);
		teleLowScore = (EditText)view.findViewById(R.id.teleLowScore);
		teleLowAttempt = (EditText)view.findViewById(R.id.teleLowAttempt);
		teleTrussScore = (EditText)view.findViewById(R.id.teleTrussScore);
		teleCatchScore = (EditText)view.findViewById(R.id.teleCatchScore);
		teleAssistScore = (EditText)view.findViewById(R.id.teleAssistScore);
		viewsAssigned=true;
	}
}